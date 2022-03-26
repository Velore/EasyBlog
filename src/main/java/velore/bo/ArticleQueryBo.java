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

    private Integer views;

    private Integer likeNum;

    private Boolean visible;

    private Boolean commentable;

    private Boolean recommend;

    private Integer status;

    /**
     * 发布时间之前
     */
    private LocalDateTime publishAfter;

    /**
     * 发布时间之后
     */
    private LocalDateTime publishBefore;
}
