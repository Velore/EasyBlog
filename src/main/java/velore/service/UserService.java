package velore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import velore.bo.UserQueryBo;
import velore.po.User;
import velore.vo.request.UserLoginRequest;

import java.util.List;

/**
 * @author Velore
 * @date 2022/3/2
 **/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * 注册时只能使用userId
     * @param loginRequest login
     * @return 是否注册成功
     */
    int register(UserLoginRequest loginRequest);

    /**
     * 用户登录
     *
     * @param loginRequest login
     * @return String token
     */
    String login(UserLoginRequest loginRequest);

    /**
     * 用户信息更新
     *
     * @param user 更新信息的用户
     * @return 是否更新成功
     */
    int updateUser(User user);

    /**
     * 封禁用户
     * 修改userType: 1 -> 3
     * @param id id
     * @return 是否封禁成功
     */
    int ban(int id);

    /**
     * 解除封禁
     * 修改userType: 3 -> 1
     * @param id id
     * @return 是否解除成功
     */
    int permit(int id);

    /**
     * 删除用户
     * 将userType设为-1
     *
     * @param id id
     * @return 是否删除成功
     */
    int deleteUser(int id);

    /**
     * 通过id获取用户
     * @param id id
     * @return user
     */
    User queryUserById(int id);

    /**
     * 通过QueryBo查询用户
     * @param queryBo queryBo
     * @return user
     */
    User queryUserByUserQueryBo(UserQueryBo queryBo);

    /**
     * 通过名字模糊查询用户
     *
     * @param name name
     * @return user list
     */
    List<User> queryUserLikeName(String name);

    /**
     * 根据用户权限查询所有相同权限的用户
     *
     * @param type type
     * @return user list
     */
    List<User> queryUserByUserType(int type);

}