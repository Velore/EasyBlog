package velore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import velore.po.User;

/**
 * @author Velore
 * @date 2022/3/2
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户类型
     * @param userType type
     * @return int
     */
    int getTotalByUserType(@Param("type") Integer userType);

    /**
     * 根据用户名
     * @param name name
     * @return int
     */
    int getTotalByName(@Param("name") String name);
}
