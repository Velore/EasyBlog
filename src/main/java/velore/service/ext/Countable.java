package velore.service.ext;

import java.util.List;

/**
 * 计算数据库表的行数
 * @author Velore
 * @date 2022/4/1
 **/
public interface Countable {

    /**
     * 计算数据库表的全部id
     * @return id list
     */
    List<Integer> getTotal();
}
