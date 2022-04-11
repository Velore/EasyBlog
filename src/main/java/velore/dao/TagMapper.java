package velore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import velore.po.Tag;

/**
 * @author Velore
 * @date 2022/3/17
 **/
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    int getTotal();
}
