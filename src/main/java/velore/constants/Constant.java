package velore.constants;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Velore
 * @date 2022/3/2
 **/
public class Constant {

    public static final Set<Integer> ADMIN_ID_SET = new HashSet<>();

    static {
        ADMIN_ID_SET.add(1);
        ADMIN_ID_SET.add(2);
    }
}
