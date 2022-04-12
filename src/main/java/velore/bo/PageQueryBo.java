package velore.bo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import velore.constants.ReqConstant;

import java.util.List;

/**
 * 分页查询相关字段
 * @author Velore
 * @date 2022/4/9
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageQueryBo {

    private Integer currentPage;

    private Integer pageSize;

    private Integer totalRecord;

    /**
     * 整理分页的数据
     * @return queryBo
     */
    public PageQueryBo validate(){
        pageSize = (pageSize == null || pageSize <= 0) ? ReqConstant.PAGE_SIZE : pageSize;
        // totalRecord == null 表示 需要查询数据库获取总记录数,设置为0
        currentPage = (currentPage == null || currentPage <= 0) ? 1 : currentPage;
        totalRecord = (totalRecord == null) ? 0 : totalRecord;
        if(totalRecord != 0){
            currentPage %= Math.min(totalRecord/pageSize, currentPage + 1);
        }
        return this;
    }

    /**
     * 第二种整理数据的方式
     * @param t t
     * @param <T> T
     */
//    public static <T extends PageQueryBo> void validate(T t){
//        t.setPageSize((t.getPageSize() == null) ? ReqConstant.PAGE_SIZE : t.getPageSize());
//        t.setCurrentPage((t.getCurrentPage() == null) ? 0 : (t.getCurrentPage() <= 0) ? 1 : t.getCurrentPage());
//        t.setTotalRecord((t.getTotalRecord() == null) ? 0 : t.getTotalRecord());
//        if(t.getTotalRecord() != 0){
//            t.setCurrentPage(t.getCurrentPage() & Math.min(t.getTotalRecord()/t.getPageSize(), t.getCurrentPage()+1));
//        }
//    }


    /**
     * 根据分页查询PageQueryBo获取IPage
     * @param <T> 分页的数据类型
     * @return IPage
     */
    public <T> IPage<T> getPage(){
        return Page.of(currentPage, pageSize, totalRecord, totalRecord == 0);
    }

    /**
     * 获取完整可用的IPage
     * @param records 分页查询的数据
     * @param <T> 分页的数据类型
     * @return IPage
     */
    public <T> IPage<T> getPage(List<T> records){
        Page<T> page = (Page<T>) getPage();
        page.setRecords(records);
        return page;
    }
}
