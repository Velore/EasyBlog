package velore.vo.request;

import io.swagger.annotations.ApiModel;
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
@ApiModel("用户更新Request")
public class UserUpdateRequest {

    private Integer id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String avatar;
}
