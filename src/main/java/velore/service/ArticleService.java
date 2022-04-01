package velore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import velore.bo.ArticleQueryBo;
import velore.po.Article;

import java.util.List;

/**
 * @author Velore
 * @date 2022/3/16
 **/
public interface ArticleService extends IService<Article> {

    int getCount();

    /**
     * 新增Article
     * @param article article
     * @return int
     */
    int add(Article article);

    /**
     * 更新article
     * @param article article
     * @return int
     */
    int update(Article article);

    /**
     * 保存为草稿
     * @param id articleId
     * @return int
     */
    int save(Integer id);

    /**
     * 发布article
     * @param id articleId
     * @return int
     */
    int publish(Integer id);

    /**
     * 设置文章状态为已删除
     * @param id articleId
     * @return int
     */
    int delete(Integer id);

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
    List<Article> queryByQueryBo(ArticleQueryBo queryBo);
}