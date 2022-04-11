package velore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import velore.po.Tag;

import java.util.List;

/**
 * @author Velore
 * @date 2022/3/17
 **/
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    /**
     * 获取数据库的全部id
     * @return id list
     */
    List<Integer> getTotal();
}
