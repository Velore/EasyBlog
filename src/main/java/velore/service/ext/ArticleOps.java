package velore.service.ext;

import velore.po.Article;

import java.util.Map;

/**
 * 文章业务操作
 * 每次操作都会更新文章
 * @author Velore
 * @date 2022/4/2
 **/
public interface ArticleOps {

    /**
     * 保存为草稿
     * @param article article
     * @return boolean
     */
    boolean draft(Article article);

    /**
     * 发布
     * @param article article
     * @return boolean
     */
    boolean publish(Article article);

    /**
     * Redis中的map
     * @param viewList key:ArticleId   value:views
     * @return boolean
     */
    boolean viewAll(Map<Integer, Integer> viewList);

    /**
     * 浏览文章数+num
     * views+num
     * @param id id
     * @param num num
     * @return boolean
     */
    boolean view(Integer id, Integer num);

    /**
     * 浏览文章数+num,num = 1
     * @param id id
     * @return boolean
     */
    boolean view(Integer id);

    /**
     * Redis中的map
     * @param likeList key:ArticleId   value:likeNum
     * @return boolean
     */
    boolean likeAll(Map<Integer, Integer> likeList);

    /**
     * 文章点赞数+num
     * likeNum+num
     * @param id id
     * @param num num
     * @return boolean
     */
    boolean like(Integer id, Integer num);

    /**
     * 文章点赞数+num,num = 1
     * @param id id
     * @return boolean
     */
    boolean like(Integer id);

    /**
     * 设置文章为推荐文章
     * @param id id
     * @return boolean
     */
    boolean recommend(Integer id);
}
