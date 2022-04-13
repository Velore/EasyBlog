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
        //保证pageSize为有效大小
        pageSize = (pageSize == null || pageSize <= 0) ? ReqConstant.PAGE_SIZE : pageSize;
        // totalRecord == null 表示 需要查询数据库获取总记录数,设置为0
        totalRecord = (totalRecord == null) ? 0 : totalRecord;
        currentPage = (currentPage == null || currentPage <= 0) ? 1 : currentPage;
        //若传入的currentPage太大,则返回最后一页
        //如果需要查询数据库获取总记录数或者总记录数小于一页,则返回第一页
        int lastPage = (totalRecord/pageSize == 0) ? 1 : (totalRecord/pageSize)+1;
        currentPage = (currentPage > lastPage)? lastPage : currentPage;
        return this;
    }

    /**
     * 第二种整理数据的方式
     * 处理逻辑与第一种相同
     * @param t t
     * @param <T> T
     */
//    public static <T extends PageQueryBo> void validate(T t){
//        t.setPageSize((t.getPageSize() == null) ? ReqConstant.PAGE_SIZE : t.getPageSize());
//        t.setTotalRecord((t.getTotalRecord() == null) ? 0 : t.getTotalRecord());
//        t.setCurrentPage((t.getCurrentPage() == null) ? 0 : (t.getCurrentPage() <= 0) ? 1 : t.getCurrentPage());
//        int lastPage = (t.getTotalRecord()/t.getPageSize() == 0) ? 1 : (t.getTotalRecord()/t.getPageSize())+1;
//        t.setCurrentPage((t.getCurrentPage() > lastPage) ? lastPage : t.getCurrentPage();
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
