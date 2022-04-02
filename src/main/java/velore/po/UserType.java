package velore.po;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Velore
 * @date 2022/3/16
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserType {

    /**
     * 管理员
     */
    ADMIN(1),

    /**
     * 普通用户
     */
    ORDINARY(2),

    /**
     * 封禁用户
     */
    FORBID(3),

    /**
     * 已删除用户
     */
    DELETE(-1),

    ;
    private Integer value;

    public static UserType generateByValue(int value){
        switch (value){
            case 1: return ADMIN;
            case 3: return FORBID;
            default:
        }
        return ORDINARY;
    }
}
