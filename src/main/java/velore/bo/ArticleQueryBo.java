package velore.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Velore
 * @date 2022/3/16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleQueryBo {

    private Integer articleTypeId;

    private Integer userId;

    private String title;

    /**
     * 最早的发布时间
     */
    private LocalDateTime publishAfter;

    /**
     * 最晚的发布时间
     */
    private LocalDateTime publishBefore;
}
