package velore.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Velore
 * @date 2022/4/10
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class TagQueryBo extends PageQueryBo{

    private String tagName;

    /**
     * 禁用空构造器
     */
    private TagQueryBo(){}

    public TagQueryBo(PageQueryBo queryBo){
        setCurrentPage(queryBo.getCurrentPage());
        setPageSize(queryBo.getPageSize());
        setTotalRecord(queryBo.getTotalRecord());
    }

    public TagQueryBo(String tagName, PageQueryBo queryBo){
        setCurrentPage(queryBo.getCurrentPage());
        setPageSize(queryBo.getPageSize());
        setTotalRecord(queryBo.getTotalRecord());
        this.tagName = tagName;
    }
}
