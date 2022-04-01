package velore.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Velore
 * @date 2022/4/1
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentQueryBo {

    private Integer articleId;

    private Integer userId;

    private LocalDateTime createTime;
}
