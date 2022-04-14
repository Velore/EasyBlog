package velore.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velore.bo.CommentQueryBo;
import velore.dao.CommentMapper;
import velore.exception.IllegalRequestException;
import velore.po.Comment;
import velore.service.base.ArticleService;
import velore.service.base.CommentService;
import velore.utils.TokenUtil;

import javax.annotation.Resource;

/**
 * @author Velore
 * @date 2022/4/1
 **/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService{

    @Resource
    private ArticleService articleService;

    @Resource
    private CommentService commentService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(Comment comment) {
        if(!articleService.queryById(comment.getArticleId()).getCommentable()){
            throw new IllegalRequestException("当前文章不允许评论");
        }
        comment.setId(null);
        return baseMapper.insert(comment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(String token, Integer id) {
        if(TokenUtil.getTokenId(token) == commentService.queryById(id).getUserId()){
            return baseMapper.deleteById(id);
        }
        throw new IllegalRequestException("只能删除自己的评论");
    }

    @Override
    public Comment queryById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public IPage<Comment> queryByQueryBo(CommentQueryBo queryBo) {
        IPage<Comment> page = queryBo.getPage();
        LambdaQueryChainWrapper<Comment> wrapper = new LambdaQueryChainWrapper<>(this.baseMapper);
        if(queryBo.getArticleId() != null){
            wrapper.eq(Comment::getArticleId, queryBo.getArticleId());
        }
        if(queryBo.getUserId() != null){
            wrapper.eq(Comment::getUserId, queryBo.getUserId());
        }
        return baseMapper.selectPage(page, wrapper.getWrapper());
    }
}
