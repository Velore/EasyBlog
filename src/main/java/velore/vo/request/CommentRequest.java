package velore.vo.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Velore
 * @date 2022/4/2
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("评论Request")
public class CommentRequest {

    private Integer articleId;

    private String content;
}
