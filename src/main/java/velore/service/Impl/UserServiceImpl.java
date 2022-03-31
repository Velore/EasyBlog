package velore.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velore.bo.UserQueryBo;
import velore.dao.UserMapper;
import velore.exception.InvalidParamException;
import velore.po.User;
import velore.po.UserType;
import velore.security.TokenService;
import velore.service.UserService;
import utils.Md5Util;
import velore.vo.request.UserLoginRequest;
import velore.vo.request.UserUpdateRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Velore
 * @date 2022/3/2
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    @Override
    public int getCount() {
        return baseMapper.getCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int register(UserLoginRequest loginRequest) {
        if(userService.queryUserByUserQueryBo(
                new UserQueryBo(loginRequest.getIdentifier(), null, null)) != null){
            throw new RuntimeException("当前userId已被使用");
        }
        User user = new User();
        user.setUserRegId(loginRequest.getIdentifier());
        user.setPassword(loginRequest.getPassword());
        return baseMapper.insert(user);
    }

    @Override
    public String login(UserLoginRequest loginRequest) {
        UserQueryBo queryBo = new UserQueryBo(
                loginRequest.getIdentifier(),
                loginRequest.getIdentifier(),
                loginRequest.getIdentifier());
        User user = userService.queryUserByUserQueryBo(queryBo);
        if(user == null){
            throw new InvalidParamException("帐号不存在");
        }
        if(!Md5Util.verify(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidParamException("密码错误");
        }
        return tokenService.generate(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(String token, UserUpdateRequest updateRequest) {
        User user = userService.queryUserById(tokenService.getTokenId(token));
        if(user == null){
            throw new InvalidParamException("用户不存在");
        }
        user.setPhone(updateRequest.getPhone());
        user.setEmail(updateRequest.getEmail());
        user.setUsername(updateRequest.getUsername());
        user.setPassword(updateRequest.getPassword());
        user.setAvatar(updateRequest.getAvatar());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //更新密码可以选择另外多写一个方法，但是这里为了方便，设置所有属性都可以一起更新
        wrapper.eq("id", tokenService.getTokenId(token));
        return baseMapper.update(user, wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int ban(String token, int banId) {
        User user = userService.queryUserById(banId);
        if(!UserType.ORDINARY.equals(user.getUserType())){
            return 1;
        }
        user.setUserType(UserType.FORBID);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", banId);
        return baseMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int permit(String token, int banId) {
        User user = userService.queryUserById(banId);
        if(!UserType.FORBID.equals(user.getUserType())){
            return 1;
        }
        user.setUserType(UserType.ORDINARY);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", banId);
        return baseMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUser(int id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        User user = baseMapper.selectOne(wrapper);
        user.setUserType(UserType.DELETE);
        return baseMapper.update(user, wrapper);
    }

    @Override
    public User queryUserById(int id){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public User queryUserByUserQueryBo(UserQueryBo queryBo) {
        User user;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(queryBo.getUserRegId()!=null){
            wrapper.eq("user_reg_id", queryBo.getUserRegId());
            user = baseMapper.selectOne(wrapper);
            if(user!=null){
                return user;
            }
        }
        if(queryBo.getPhone()!=null){
            wrapper.eq("phone", queryBo.getPhone());
            user = baseMapper.selectOne(wrapper);
            if(user!=null){
                return user;
            }
        }
        if(queryBo.getEmail()!=null){
            wrapper.eq("email", queryBo.getEmail());
            user = baseMapper.selectOne(wrapper);
            return user;
        }
        return null;
    }

    @Override
    public List<User> queryUserLikeName(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("user_name", name).eq(false, "user_type", -1);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<User> queryUserByUserType(int type) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_type", type);
        return baseMapper.selectList(wrapper);
    }
}
