package velore.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Velore
 * @date 2022/4/10
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TagQueryBo extends PageQueryBo{

    private String tagName;

    public TagQueryBo(PageQueryBo queryBo){
        super(queryBo.currentPage, queryBo.pageSize, queryBo.totalRecord);
    }

    public TagQueryBo(String tagName, PageQueryBo queryBo){
        super(queryBo.currentPage, queryBo.pageSize, queryBo.totalRecord);
        this.tagName = tagName;
    }
}
