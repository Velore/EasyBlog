package velore.exception;

/**
 * 参数无效异常
 * @author Velore
 * @date 2022/3/16
 **/
public class InvalidParamException extends RuntimeException{

    public InvalidParamException(String message){
        super(message);
    }
}
