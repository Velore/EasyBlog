package velore.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Velore
 * @date 2022/4/12
 **/
public class CastUtil {

    public static <T> List<T> cast(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if(!(obj instanceof List<?>)){
            throw new IllegalArgumentException("类型异常");
        }
        for (Object o : (List<?>) obj) {
            result.add(clazz.cast(o));
        }
        return result;
    }
}
