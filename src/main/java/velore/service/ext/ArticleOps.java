package velore.service.ext;

import velore.po.Article;

/**
 * 文章操作
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
     * 浏览文章数+num
     * views+num
     * @param id id
     * @param num num
     * @return boolean
     */
    boolean view(Integer id, Integer num);

    /**
     * num = 1
     * @param id id
     * @return boolean
     */
    boolean view(Integer id);

    /**
     * 文章点赞数+num
     * likeNum+num
     * @param id id
     * @param num num
     * @return boolean
     */
    boolean like(Integer id, Integer num);

    /**
     * num = 1
     * @param id id
     * @return boolean
     */
    boolean like(Integer id);

    /**
     * 设置文章为推荐文章
     * @param token token
     * @param id id
     * @return boolean
     */
    boolean recommend(String token, Integer id);
}
