package velore.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velore.bo.CommentQueryBo;
import velore.dao.CommentMapper;
import velore.po.Comment;
import velore.service.CommentService;

import java.util.List;

/**
 * @author Velore
 * @date 2022/4/1
 **/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(Comment comment) {
        return baseMapper.insert(comment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Integer id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public Comment queryById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<Comment> queryAllByQueryBo(CommentQueryBo queryBo) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        if(queryBo.getArticleId()!=null){
            wrapper.eq("article_id", queryBo.getArticleId());
        }
        if(queryBo.getUserId()!=null){
            wrapper.eq("user_id", queryBo.getUserId());
        }
        return baseMapper.selectList(wrapper);
    }
}
