package velore.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import velore.po.Comment;
import velore.utils.TokenUtil;

/**
 * @author Velore
 * @date 2022/4/2
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {

    private Integer articleId;

    private String content;

    public Comment getComment(String token){
        Comment comment = new Comment();
        comment.setId(null);
        comment.setArticleId(articleId);
        comment.setUserId(TokenUtil.getTokenId(token));
        comment.setContent(content);
        return comment;
    }
}
