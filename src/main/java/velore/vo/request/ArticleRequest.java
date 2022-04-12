package velore.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import velore.po.Article;
import velore.utils.TokenUtil;

/**
 * @author Velore
 * @date 2022/4/12
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {

    private Integer id;

    private Integer articleType;

    private String title;

    private String content;

    private Boolean visible;

    private Boolean commentable;

    public Article getArticle(String token){
        Article article = new Article();
        article.setArticleType(articleType);
        article.setUserId(TokenUtil.getTokenId(token));
        article.setTitle(title);
        article.setContent(content);
        article.setVisible(visible);
        article.setCommentable(commentable);
        return article;
    }
}
