package velore.vo.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Velore
 * @date 2022/3/2
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultType {

    /**
     *
     */

    SUCCESS(200, "OK"),

    NOT_FOUND(404, "资源不存在"),

    SYSTEM_ERROR(500, "服务器内部异常"),

    PARAM_ERROR(500, "参数异常"),

    /**
     *
     */

    TOKEN_REQUIRE(1000, "未登录"),

    LOGIN_ERROR(1001, "密码错误"),

    TOKEN_EXPIRE(1002, "token过期"),

    NO_PERMIT(1003, "权限不足"),

    /**
     *
     */

    USER_NOT_EXISTS(2000, "用户不存在"),

    USERID_EXISTS(2001, "用户名已存在"),

    PHONE_EXISTS(2002, "手机号已存在"),

    EMAIL_EXISTS(2003, "邮箱已存在"),

    ;

    private Integer code;

    private String info;
}
