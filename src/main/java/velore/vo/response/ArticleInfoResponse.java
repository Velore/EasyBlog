package velore.vo.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import velore.po.Article;
import velore.po.Comment;
import velore.po.Tag;

import java.util.List;

/**
 * @author Velore
 * @date 2022/4/4
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("文章具体信息Response")
public class ArticleInfoResponse {

    private Article article;

    /**
     * username
     */
    private String author;

    private List<Tag> tags;

    private List<Comment> comments;
}
