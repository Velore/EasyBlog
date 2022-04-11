package velore.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import velore.bo.PageQueryBo;
import velore.constants.ReqConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询的Response
 * @author Velore
 * @date 2022/4/10
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页查询的Response")
public class PageResponse<T> {

    @ApiModelProperty("总记录数")
    private Integer totalRecord;

    @ApiModelProperty("当前页数")
    private Integer currentPage;

    @ApiModelProperty("每页大小")
    private Integer pageSize;

    @ApiModelProperty("总页数")
    private Integer totalPage;

    @ApiModelProperty("当前页面的数据")
    private List<T> data;

    public PageResponse(IPage<T> page){
        this.totalRecord = (int)page.getTotal();
        this.currentPage = (int) page.getCurrent();
        this.pageSize = (int)page.getSize();
        this.totalPage = (int) page.getPages();
        this.data = page.getRecords();
    }

    public PageResponse(PageQueryBo queryBo){
        currentPage = queryBo.getCurrentPage();
        pageSize = queryBo.getPageSize();
        totalRecord = queryBo.getTotalRecord();
        this.totalPage = Math.min(totalRecord / pageSize,1);
    }

    /**
     * @param currentPage    当前页数
     * @param pageSize    每页记录数
     * @param totalRecord  总记录数
     */
    public PageResponse(int currentPage, int pageSize, int totalRecord){
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.totalPage = Math.min(totalRecord / pageSize, 1);
    }

    /**
     * @param data        列表数据
     * @param currentPage    当前页数
     * @param pageSize    每页记录数
     * @param totalRecord  总记录数
     */
    public PageResponse(List<T> data, int currentPage, int pageSize, int totalRecord) {
        this.data = data;
        this.totalRecord = totalRecord;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalPage = Math.min(totalRecord / pageSize, 1);
    }

    /**
     * 向记录中插入本页数据
     * @param t 数据
     */
    public void addRecord(T t){
        if(data == null){
            //减少数组扩容的概率
            data = new ArrayList<>((pageSize == null)? ReqConstant.PAGE_SIZE+1 : pageSize+1 );
        }
        data.add(t);
    }
}
