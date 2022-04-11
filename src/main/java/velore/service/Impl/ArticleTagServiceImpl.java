package velore.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velore.bo.PageQueryBo;
import velore.dao.ArticleTagMapper;
import velore.po.ArticleTag;
import velore.po.Tag;
import velore.service.base.ArticleService;
import velore.service.base.ArticleTagService;
import velore.service.base.TagService;
import velore.vo.ArticleBrief;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private ArticleService articleService;

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
    public List<Tag> queryByArticleId(Integer id) {
        QueryWrapper<ArticleTag> wrapper = new QueryWrapper<>();
        wrapper.eq("article_id", id);
        List<Tag> tags = new ArrayList<>();
        List<ArticleTag> articleTags = baseMapper.selectList(wrapper);
        for(ArticleTag articleTag : articleTags){
            Tag tag = tagService.queryById(articleTag.getTagId());
            if(tag != null){
                tags.add(tag);
            }
        }
        return tags;
    }

    @Override
    public IPage<ArticleBrief> queryByTagId(Integer id, PageQueryBo queryBo) {
        IPage<ArticleTag> page = queryBo.getPage();
        page = baseMapper.selectPage(page, new LambdaQueryChainWrapper<>(this.baseMapper)
                .eq(ArticleTag::getTagId, id).getWrapper());
        List<ArticleBrief> articles = new ArrayList<>();
        for(ArticleTag articleTag : page.getRecords()){
            //获取每一篇文章的Brief
            ArticleBrief article = (ArticleBrief) articleService.displayBrief(
                    articleService.queryById(articleTag.getArticleId())
            );
            if(article != null){
                articles.add(article);
            }
        }
        return queryBo.getPage(articles);
    }
}
