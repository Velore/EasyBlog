package velore.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import velore.constants.ArticleConstant;
import velore.po.Article;
import velore.po.User;

import java.time.LocalDateTime;

/**
 * 用于首页或其他页面展示文章列表,
 * 以list形式作为PageResponse的属性
 * @author Velore
 * @date 2022/4/4
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("文章简要")
public class ArticleBrief {

    /**
     * articleId
     */
    private Integer id;

    private String title;

    /**
     * userId
     */
    private Integer authorId;

    /**
     * username
     */
    private String authorName;

    private Integer articleType;

    /**
     * brief article content
     */
    private String brief;

    private Integer views;

    private Integer likeNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime publishTime;

    /**
     * 设置依赖信息,即原对象和展示对象都存在的信息
     * @param article article
     * @return ArticleBrief
     */
    public ArticleBrief setDependAttributes(Article article){
        setId(article.getId());
        setTitle(article.getTitle());
        setArticleType(article.getArticleType());
        String content = article.getContent();
        //若文章内容超过Brief的最大长度,则取前面的一部分内容
        //若文章内容少于Brief的最大长度,则以文章内容作为Brief
        setBrief((content.length()> ArticleConstant.ARTICLE_CONTENT_BRIEF_MAX_LENGTH)?
                content.substring(0, ArticleConstant.ARTICLE_CONTENT_BRIEF_MAX_LENGTH-1):content);
        setViews(article.getViews());
        setLikeNum(article.getLikeNum());
        setPublishTime(article.getPublishTime());
        return this;
    }

    public void setAuthor(User user) {
        this.authorName = user.getUsername();
        this.authorId = user.getId();
    }
}
