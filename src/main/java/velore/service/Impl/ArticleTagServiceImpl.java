package velore.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velore.dao.ArticleTagMapper;
import velore.po.ArticleTag;
import velore.service.ArticleTagService;
import velore.service.ext.Countable;

import java.util.List;

/**
 * @author Velore
 * @date 2022/4/1
 **/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
        implements ArticleTagService, Countable {

    @Override
    public int getCount() {
        return baseMapper.getCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(ArticleTag articleTag) {
        return baseMapper.insert(articleTag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Integer id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public ArticleTag queryById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public ArticleTag queryByArticleIdAndTagId(Integer articleId, Integer tagId) {
        QueryWrapper<ArticleTag> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", articleId).eq("tag_id", tagId);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public List<ArticleTag> queryByArticleId(Integer id) {
        QueryWrapper<ArticleTag> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", id);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<ArticleTag> queryByTagId(Integer id) {
        QueryWrapper<ArticleTag> wrapper = new QueryWrapper<>();
        wrapper.eq("tag_id", id);
        return baseMapper.selectList(wrapper);
    }
}
