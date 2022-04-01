package velore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import velore.bo.CommentQueryBo;
import velore.po.Comment;

import java.util.List;

/**
 * @author Velore
 * @date 2022/4/1
 **/
public interface CommentService extends IService<Comment> {

    int add(Comment comment);

    int delete(Integer id);

    Comment queryById(Integer id);

    List<Comment> queryAllByQueryBo(CommentQueryBo queryBo);
}
