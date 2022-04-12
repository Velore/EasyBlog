package velore.vo.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.Md5Util;
import velore.po.User;

/**
 * @author Velore
 * @date 2022/3/2
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户登录Request")
public class UserLoginRequest {

    /**
     * 账号：邮箱/手机号
     */
    private String identifier;

    /**
     * 密码
     */
    private String password;

    /**
     * 用于用户注册
     * @return user
     */
    public User getUser(){
        User user = new User();
        user.setUserRegId(identifier);
        user.setPassword(Md5Util.encrypt(password));
        return user;
    }
}
