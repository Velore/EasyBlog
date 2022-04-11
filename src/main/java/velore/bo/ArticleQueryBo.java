package velore.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Velore
 * @date 2022/3/16
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ArticleQueryBo extends PageQueryBo{

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

    @Override
    public ArticleQueryBo validate(){
        PageQueryBo.validate(this);
        assert articleTypeId != null && articleTypeId <= 0 : "id 不能为负数";
        assert userId != null && userId < 0 : "id 不能为负数";
        return this;
    }
}
