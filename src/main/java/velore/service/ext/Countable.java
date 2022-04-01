package velore.service.ext;

/**
 * @author Velore
 * @date 2022/4/1
 **/
public interface Countable {

    /**
     * 计算数据库表的行数
     * @return 有多少行数据
     */
    int getCount();
}
