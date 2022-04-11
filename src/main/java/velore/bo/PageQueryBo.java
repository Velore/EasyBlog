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

    public Integer currentPage;

    public Integer pageSize;

    public Integer totalRecord;

    /**
     * 整理分页的数据
     * @return queryBo
     */
    public PageQueryBo validate(){
        pageSize = (pageSize == null)? ReqConstant.PAGE_SIZE : pageSize;
        // totalRecord == null 表示 需要查询数据库获取总记录数,设置为0
        totalRecord = (totalRecord == null)? 0 : totalRecord;
        //
        currentPage = (currentPage == null)? 1 : (currentPage <= 0 )? 1: currentPage;
        if(totalRecord != 0){
            currentPage %= (totalRecord/pageSize);
        }
        return this;
    }

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
