package velore.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velore.dao.ArticleTagMapper;
import velore.po.ArticleTag;
import velore.service.ArticleTagService;
import velore.service.TagService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Velore
 * @date 2022/4/1
 **/
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
        implements ArticleTagService{

    @Resource
    private ArticleTagService articleTagService;

    @Resource
    private TagService tagService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(String token, ArticleTag articleTag) {
        tagService.increase(articleTag.getTagId());
        return baseMapper.insert(articleTag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(String token, Integer id) {
        tagService.decrease(articleTagService.queryById(id).getTagId());
        return baseMapper.deleteById(id);
    }

    @Override
    public ArticleTag queryById(Integer id) {
        return articleTagService.getById(id);
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
