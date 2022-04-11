package velore.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import velore.po.Comment;

/**
 * @author Velore
 * @date 2022/3/17
 **/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    int getTotal(@Param("id") Integer articleId);
}
