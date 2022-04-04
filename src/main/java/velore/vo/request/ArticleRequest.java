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
@ApiModel("文章Request")
public class ArticleRequest {

    private Integer id;

    private Integer articleType;

    private String title;

    private String content;

    private Boolean visible;

    private Boolean commentable;
}
