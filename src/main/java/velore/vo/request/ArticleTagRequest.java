package velore.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import velore.po.ArticleTag;

/**
 * @author Velore
 * @date 2022/4/12
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTagRequest {

    private Integer articleId;

    private Integer tagId;

    public ArticleTag getArticleTag(){
        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticleId(articleId);
        articleTag.setTagId(tagId);
        return articleTag;
    }
}
