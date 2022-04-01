package velore.exception;

/**
 * 请求不合法异常
 * @author Velore
 * @date 2022/4/1
 **/
public class IllegalRequestException extends RuntimeException{

    public IllegalRequestException(String msg){
        super(msg);
    }
}
