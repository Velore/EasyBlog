package velore.service.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import velore.bo.ArticleQueryBo;
import velore.po.Article;
import velore.service.ext.ArticleOps;
import velore.service.ext.Countable;
import velore.service.ext.SeparateDisplay;

import java.util.List;

/**
 * @author Velore
 * @date 2022/3/16
 **/
public interface ArticleService extends IService<Article>, Countable, ArticleOps, SeparateDisplay<Article> {

    /**
     * 该方法作为(draft,publish底层方法)不暴露接口
     * add Article
     * @param article article
     * @return boolean
     */
    boolean add(Article article);

    /**
     * 更新article
     * @param article article
     * @return boolean
     */
    boolean update(Article article);

    /**
     * 设置文章状态为已删除
     * @param token token
     * @param id articleId
     * @return boolean
     */
    boolean delete(String token, Integer id);

    /**
     * 通过id查询
     * @param id articleId
     * @return Article
     */
    Article queryById(Integer id);

    /**
     * 随机查询指定数量
     * @param num num
     * @return list
     */
    List<Article> queryRandom(Integer num);

    /**
     * 条件查询
     * @param queryBo queryBo
     * @return list
     */
    IPage<Article> queryByQueryBo(ArticleQueryBo queryBo);
}
