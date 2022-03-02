package velore.utils;

import velore.vo.result.Result;
import velore.vo.result.ResultType;

import java.io.Serializable;

/**
 * @author Velore
 * @date 2022/3/2
 **/
public class ResultUtil implements Serializable {

    public static final long serialVersionUID = 1111;

    /**
     * 返回操作成功的结果;
     * @return Result
     */
    public static <E> Result<E> success(){
        return new Result<>(ResultType.SUCCESS.getCode(), ResultType.SUCCESS.getInfo(), null);
    }

    /**
     * 返回操作成功的结果;
     * @param e 附带的对象element
     * @return Result
     */
    public static <E> Result<E> success(E e){
        return new Result<>(ResultType.SUCCESS.getCode(), ResultType.SUCCESS.getInfo(), e);
    }

    /**
     * 返回操作失败的结果;
     * @return Result
     */
    public static <E> Result<E> fail(ResultType type){
        return new Result<>(type.getCode(), type.getInfo(), null);
    }

    /**
     * 返回操作失败的结果;
     * @param e 附带的对象element
     * @return Result
     */
    public static <E> Result<E> fail(ResultType type, E e){
        return new Result<>(type.getCode(), type.getInfo(), e);
    }
}
