package velore.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import velore.bo.UserQueryBo;
import velore.po.User;
import velore.vo.request.UserLoginRequest;
import velore.vo.request.UserUpdateRequest;

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
     * @param token 用户登录token
     * @param updateRequest 更新信息的用户
     * @return 是否更新成功
     */
    int update(String token, UserUpdateRequest updateRequest);

    /**
     * 封禁用户
     * 修改userType为3
     * @param token 管理员token
     * @param banId id
     * @return 是否封禁成功
     */
    int ban(String token, int banId);

    /**
     * 解除封禁
     * 修改userType为1
     * @param token 管理员token
     * @param banId id
     * @return 是否解除成功
     */
    int permit(String token, int banId);

    /**
     * 删除用户
     * 将userType设为-1
     *
     * @param id id
     * @return 是否删除成功
     */
    int delete(int id);

    /**
     * 通过id获取用户
     * @param id id
     * @return user
     */
    User queryById(int id);

    /**
     * 通过QueryBo查询用户
     * @param queryBo queryBo
     * @return user
     */
    IPage<User> queryByQueryBo(UserQueryBo queryBo);

    /**
     * 用于注册或者登录
     * @param queryBo queryBo
     * @return user
     */
    User querySingletonByQueryBo(UserQueryBo queryBo);
}