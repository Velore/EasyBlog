package velore.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.Md5Util;
import velore.bo.UserQueryBo;
import velore.constants.ReqConstant;
import velore.dao.UserMapper;
import velore.exception.InvalidParamException;
import velore.po.User;
import velore.po.UserType;
import velore.service.base.UserService;
import velore.utils.TokenUtil;
import velore.vo.request.UserLoginRequest;
import velore.vo.request.UserUpdateRequest;

import javax.annotation.Resource;

/**
 * @author Velore
 * @date 2022/3/2
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    private UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int register(UserLoginRequest loginRequest) {
        if(userService.querySingletonByQueryBo(
                new UserQueryBo(loginRequest.getIdentifier())) != null){
            throw new InvalidParamException("当前userId已被使用");
        }
        return baseMapper.insert(new User(loginRequest));
    }

    @Override
    public String login(UserLoginRequest loginRequest) {
        UserQueryBo queryBo = new UserQueryBo(
                loginRequest.getIdentifier(),
                loginRequest.getIdentifier(),
                loginRequest.getIdentifier());
        User user = userService.querySingletonByQueryBo(queryBo);
        if(user == null){
            throw new InvalidParamException("帐号不存在");
        }
        if(!Md5Util.verify(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidParamException("密码错误");
        }
        return TokenUtil.generate(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(String token, UserUpdateRequest updateRequest) {
        int tokenId = TokenUtil.getTokenId(token);
        User user;
        //token不为空
        if(token != null){
            user = userService.queryById(tokenId);
        }else {
            user = userService.queryById(updateRequest.getId());
        }
        if(user == null){
            throw new InvalidParamException("用户不存在");
        }
        updateAttributes(user, updateRequest);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //更新密码可以选择另外多写一个方法，但是这里为了方便，设置所有属性都可以一起更新
        wrapper.eq("id", TokenUtil.getTokenId(token));
        return baseMapper.update(user, wrapper);
    }

    /**
     * 设置需要更新的用户属性
     * @param user user
     * @param updateRequest updateRequest
     */
    private void updateAttributes(User user, UserUpdateRequest updateRequest){
        user.setPhone(updateRequest.getPhone());
        user.setEmail(updateRequest.getEmail());
        user.setUsername(updateRequest.getUsername());
        user.setPassword(Md5Util.encrypt(updateRequest.getPassword()));
        user.setAvatar(updateRequest.getAvatar());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int ban(String token, int banId) {
        User user = userService.queryById(banId);
        if(!UserType.ORDINARY.getValue().equals(user.getUserType())){
            return 1;
        }
        user.setUserType(UserType.FORBID.getValue());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", banId);
        return baseMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int permit(String token, int banId) {
        User user = userService.queryById(banId);
        if(!UserType.FORBID.getValue().equals(user.getUserType())){
            return 1;
        }
        user.setUserType(UserType.ORDINARY.getValue());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", banId);
        return baseMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(int id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        User user = baseMapper.selectOne(wrapper);
        user.setUserType(UserType.DELETE.getValue());
        return baseMapper.update(user, wrapper);
    }

    @Override
    public User queryById(int id){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public IPage<User> queryByQueryBo(UserQueryBo queryBo) {
        Integer size = queryBo.getPageSize();
        if(size == -1){
            size = ReqConstant.PAGE_SIZE;
        }
        boolean hasTotal = (queryBo.getTotalRecord() == -1);
        IPage<User> userPage = new Page<>(queryBo.getCurrentPage(), size, queryBo.getTotalRecord(), hasTotal);
        LambdaQueryChainWrapper<User> wrapper = new LambdaQueryChainWrapper<>(this.baseMapper);
        if(queryBo.getName()!=null){
            wrapper.like(User::getUsername, queryBo.getName());
        }
        if(queryBo.getUserType()!=null){
            wrapper.eq(User::getUserType, queryBo.getUserType());
        }else{
            wrapper.eq(false, User::getUserType, -1);
        }
        //此处不能直接使用LambdaQueryChainWrapper,需要获取其内部的wrapper
//        return baseMapper.selectPage(userPage, wrapper);
        return baseMapper.selectPage(userPage, wrapper.getWrapper());
    }

    @Override
    public User querySingletonByQueryBo(UserQueryBo queryBo){
        User user;
        if(queryBo.getUserRegId()!=null){
            user = queryByUserRegId(queryBo.getUserRegId());
            if(user!=null){
                return user;
            }
        }
        if(queryBo.getPhone()!=null){
            user = queryByPhone(queryBo.getPhone());
            if(user!=null){
                return user;
            }
        }
        if(queryBo.getEmail()!=null){
            user = queryByEmail(queryBo.getEmail());
            return user;
        }
        return null;
    }

    private User queryByUserRegId(String regId){
        return baseMapper.selectOne(new QueryWrapper<User>().eq("user_reg_id", regId));
    }

    private User queryByPhone(String phone){
        return baseMapper.selectOne(new QueryWrapper<User>().eq("phone", phone));
    }

    private User queryByEmail(String email){
        return baseMapper.selectOne(new QueryWrapper<User>().eq("email", email));
    }
}
