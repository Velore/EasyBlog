package velore.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;
import velore.vo.request.UserLoginRequest;

import java.time.LocalDateTime;

/**
 * @author Velore
 * @date 2022/3/2
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Component
@TableName("user")
@ApiModel("用户")
@Alias("User")
public class User extends ModifiedRecord{

    /**
     * 数据库id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户自定义的id
     */
    private String userRegId;

    /**
     * 用户名
     */
    private String username;
    private String password;
    private String phone;
    private String email;
    private String avatar;

    private Integer userType;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    public User(UserLoginRequest request){
        userRegId = request.getIdentifier();
        password = request.getPassword();
    }
}
