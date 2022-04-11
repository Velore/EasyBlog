package velore.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Velore
 * @date 2022/4/1
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentQueryBo extends PageQueryBo{

    private Integer articleId;

    private Integer userId;

    public CommentQueryBo(Integer articleId, PageQueryBo queryBo){
        super(queryBo.currentPage, queryBo.pageSize, queryBo.totalRecord);
        this.articleId = articleId;
    }
}
