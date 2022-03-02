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
public class UserUpdateRequest {

    private String userId;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String avatar;
}
