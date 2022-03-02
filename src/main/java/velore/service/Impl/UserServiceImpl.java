package velore.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velore.bo.UserQueryBo;
import velore.constants.UserConstant;
import velore.dao.UserMapper;
import velore.po.User;
import velore.security.TokenService;
import velore.service.UserService;
import velore.utils.Md5Util;
import velore.vo.request.UserLoginRequest;

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
            throw new RuntimeException("帐号不存在");
        }
        if(!Md5Util.verify(loginRequest.getPassword(), user.getPassword())){
            throw new RuntimeException("密码错误");
        }
        return tokenService.generate(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //更新密码可以选择另外多写一个方法，但是这里为了方便，设置所有属性都可以一起更新
        wrapper.eq("id", user.getId());
        return baseMapper.update(user, wrapper);
    }

    @Override
    public int ban(int id) {
        User user = userService.queryUserById(id);
        if(!UserConstant.USER_TYPE_ORDINARY.equals(user.getUserType())){
            return 1;
        }
        user.setUserType(3);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return baseMapper.updateById(user);
    }

    @Override
    public int permit(int id) {
        User user = userService.queryUserById(id);
        if(!UserConstant.USER_TYPE_FORBID.equals(user.getUserType())){
            return 1;
        }
        user.setUserType(1);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return baseMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUser(int id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        User user = baseMapper.selectOne(wrapper);
        user.setUserType(-1);
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
        if(queryBo.getUserId()!=null){
            wrapper.eq("user_id", queryBo.getUserId());
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
        wrapper.like("user_name", name);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<User> queryUserByUserType(int type) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_type", type);
        return baseMapper.selectList(wrapper);
    }
}