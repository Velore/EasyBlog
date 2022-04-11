package velore.vo.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import velore.po.Article;
import velore.po.Comment;
import velore.po.Tag;
import velore.po.User;

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

    private Integer authorId;
    /**
     * username
     */
    private String authorName;

    private List<Tag> tags;

    private List<Comment> comments;

    private void setAuthorId(Integer authorId){
        this.authorId = authorId;
    }

    private void setAuthorName(String authorName){
        this.authorName = authorName;
    }

    public void setAuthor(User user){
        this.authorId = user.getId();
        this.authorName = user.getUsername();
    }
}
