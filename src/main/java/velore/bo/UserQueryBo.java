package velore.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Velore
 * @date 2022/3/2
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryBo extends PageQueryBo{

    private String userRegId;

    private String phone;

    private String email;

    private String name;

    private Integer userType;

    /**
     * 用于注册
     * @param userRegId regId
     */
    public UserQueryBo(String userRegId){
        this.userRegId = userRegId;
    }

    /**
     * 用于登录
     * @param userRegId regId
     * @param phone phone
     * @param email email
     */
    public UserQueryBo(String userRegId, String phone, String email){
        this.userRegId = userRegId;
        this.phone = phone;
        this.email = email;
    }
}
