package velore.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import velore.po.User;

import java.time.LocalDateTime;

/**
 * @author Velore
 * @date 2022/3/2
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {

    private String userRegId;

    private String username;

    private String phone;

    private String email;

    private String avatar;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public UserInfoResponse(User user){
        userRegId = user.getUserRegId();
        username = user.getUsername();
        phone = user.getPhone();
        email = user.getEmail();
        avatar = user.getAvatar();
        createTime = user.getCreateTime();
        updateTime = user.getUpdateTime();
    }
}
