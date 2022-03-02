package velore.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Velore
 * @date 2022/3/2
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    /**
     * 账号：邮箱/手机号
     */
    private String identifier;

    /**
     * 密码
     */
    private String password;
}
