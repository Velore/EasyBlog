package velore.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import velore.vo.result.Result;
import velore.vo.result.ResultType;
import velore.utils.ResultUtil;

/**
 * 全局异常处理器
 * @author Velore
 * @date 2022/3/2
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        String brief = e.getMessage();
        String details = e.toString();
        if (brief == null || "".equals(brief)) {
            return ResultUtil.fail(ResultType.SYSTEM_ERROR, details);
        }
        return ResultUtil.fail(ResultType.SYSTEM_ERROR, brief);
    }

    /**
     * token过期
     * @param e e
     * @return result
     */
    @ResponseBody
    @ExceptionHandler(JWTVerificationException.class)
    public Result<String> handleVerifyException(JWTVerificationException e){
        return ResultUtil.fail(ResultType.TOKEN_EXPIRE, e.getMessage());
    }
}
