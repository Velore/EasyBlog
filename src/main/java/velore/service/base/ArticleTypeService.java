package velore.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import velore.bo.PageQueryBo;
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
     * @param queryBo queryBo
     * @return ArticleType list
     */
    IPage<ArticleType> queryAll(PageQueryBo queryBo);

    /**
     * 随机获取指定数量
     * @param num num
     * @return ArticleType list
     */
    List<ArticleType> queryRandom(int num);

    /**
     * 通过名字模糊查询
     * @param name name
     * @param queryBo queryBo
     * @return ArticleType list
     */
    IPage<ArticleType> queryLikeName(String name, PageQueryBo queryBo);
}
