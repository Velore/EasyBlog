package velore.vo.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import velore.po.Article;

import java.time.LocalDateTime;

/**
 * 用于首页或其他页面展示文章列表
 * @author Velore
 * @date 2022/4/4
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("文章简要Response")
public class ArticleBriefResponse {

    /**
     * 展示的content最大值
     */
    private static int BRIEF_MAX_LENGTH = 64;

    /**
     * articleId
     */
    private Integer id;

    private String title;

    /**
     * username
     */
    private String author;

    /**
     * brief article content
     */
    private String brief;

    private Integer views;

    private Integer likeNum;

    private LocalDateTime publishTime;

    /**
     * 设置依赖信息,原对象和展示对象都存在的信息
     * @param article article
     * @return ArticleBriefResponse
     */
    public ArticleBriefResponse setDependAttributes(Article article){
        setId(article.getId());
        setTitle(article.getTitle());
        setBrief(article.getContent().substring(0, BRIEF_MAX_LENGTH-1));
        setViews(article.getViews());
        setLikeNum(article.getLikeNum());
        setPublishTime(article.getPublishTime());
        return this;
    }
}
