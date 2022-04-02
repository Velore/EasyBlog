package velore.vo.request;

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
public class CommentRequest {

    private Integer articleId;

    private String content;
}
