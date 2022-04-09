package velore.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import velore.po.ArticleType;
import velore.service.ext.Countable;

import java.util.List;

/**
 * @author Velore
 * @date 2022/3/26
 **/
public interface ArticleTypeService extends IService<ArticleType>, Countable {

    /**
     * 添加
     * @param type type
     * @return int
     */
    int add(ArticleType type);

    /**
     * 更新
     * @param type type
     * @return int
     */
    int update(ArticleType type);

    /**
     * 删除
     * @param id id
     * @return int
     */
    int delete(int id);

    /**
     * 通过id查询
     * @param id id
     * @return ArticleType
     */
    ArticleType queryById(int id);

    /**
     * 查询全部
     * @return ArticleType list
     */
    List<ArticleType> queryAll();

    /**
     * 随机获取指定数量
     * @param num num
     * @return ArticleType list
     */
    List<ArticleType> queryRandom(int num);

    /**
     * 通过名字模糊查询
     * @param name name
     * @return ArticleType list
     */
    List<ArticleType> queryLikeName(String name);
}
