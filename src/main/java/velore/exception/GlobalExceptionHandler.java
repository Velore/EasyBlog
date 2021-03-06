package velore.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import result.Result;
import result.ResultType;

/**
 * 全局异常处理器
 * @author Velore
 * @date 2022/3/2
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Result<String> handleThrowable(Throwable e) {
        String brief = e.toString();
        String details = e.fillInStackTrace().toString();
        if (brief == null || "".equals(brief)) {
            return Result.fail(ResultType.SYSTEM_ERROR, details);
        }
        return Result.fail(ResultType.SYSTEM_ERROR, brief);
    }

    /**
     * token过期
     * @param e e
     * @return result
     */
    @ResponseBody
    @ExceptionHandler(JWTVerificationException.class)
    public Result<String> handleVerifyException(JWTVerificationException e){
        return Result.fail(ResultType.TOKEN_EXPIRE, e.getMessage());
    }
}
