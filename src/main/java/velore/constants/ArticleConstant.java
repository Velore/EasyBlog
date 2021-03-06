package velore.constants;

/**
 * @author Velore
 * @date 2022/3/2
 **/
public class ArticleConstant {

    /**
     * 未发布
     */
    public static final Integer ARTICLE_STATUS_UNPUBLISHED = 0;

    /**
     * 已保存为草稿
     */
    public static final Integer ARTICLE_STATUS_DRAFT = 1;

    /**
     * 已发布
     */
    public static final Integer ARTICLE_STATUS_PUBLISHED = 2;

    /**
     * 已删除
     */
    public static final Integer ARTICLE_STATUS_DELETED = -1;

    /**
     * 文章展示的简要内容的最大值
     */
    public static int ARTICLE_CONTENT_BRIEF_MAX_LENGTH = 64;
}
