package velore.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import velore.bo.CommentQueryBo;
import velore.po.Comment;

import java.util.List;

/**
 * @author Velore
 * @date 2022/4/1
 **/
public interface CommentService extends IService<Comment> {

    /**
     * 添加评论
     * @param comment comment
     * @return int
     */
    int add(Comment comment);

    /**
     * 删除评论
     * @param token token
     * @param id id
     * @return int
     */
    int delete(String token, Integer id);

    /**
     * 根据id查询评论
     * @param id id
     * @return Comment
     */
    Comment queryById(Integer id);

    /**
     * 根据QueryBo查询评论
     * @param queryBo CommentQueryBo
     * @return Comment List
     */
    List<Comment> queryAllByQueryBo(CommentQueryBo queryBo);
}
