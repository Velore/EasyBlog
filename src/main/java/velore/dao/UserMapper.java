package velore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import velore.po.User;

/**
 * @author Velore
 * @date 2022/3/2
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    int getCount();
}
