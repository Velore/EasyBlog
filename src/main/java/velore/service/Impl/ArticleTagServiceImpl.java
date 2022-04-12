package velore.service.Impl;

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
import velore.utils.CastUtil;
import velore.vo.ArticleBrief;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public int add(ArticleTag articleTag) {
        tagService.increase(articleTag.getTagId());
        return baseMapper.insert(articleTag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Integer id) {
        tagService.decrease(articleTagService.queryById(id).getTagId());
        return baseMapper.deleteById(id);
    }

    @Override
    public ArticleTag queryById(Integer id) {
        return articleTagService.getById(id);
    }

    @Override
    public List<Tag> queryByArticleId(Integer id) {
        LambdaQueryChainWrapper<ArticleTag> wrapper = new LambdaQueryChainWrapper<>(this.baseMapper);
        List<Integer> tagIds = baseMapper.selectList(
                wrapper.eq(ArticleTag::getArticleId, id).getWrapper()).stream()
                .map(ArticleTag::getTagId).collect(Collectors.toList());
        if(tagIds.isEmpty()){
            return new ArrayList<>();
        }
        return tagService.getBaseMapper().selectBatchIds(tagIds);
    }

    @SuppressWarnings("")
    @Override
    public IPage<ArticleBrief> queryByTagId(Integer id, PageQueryBo queryBo) {
        IPage<ArticleTag> page = queryBo.getPage();
        page = baseMapper.selectPage(page, new LambdaQueryChainWrapper<>(this.baseMapper)
                .eq(ArticleTag::getTagId, id).getWrapper());
        List<Integer> articleIds = page.getRecords().stream()
                .map(ArticleTag::getArticleId).collect(Collectors.toList());
        //类型转换
        List<ArticleBrief> briefs = CastUtil.cast(
                articleService.displayBrief(articleService.getBaseMapper().selectBatchIds(articleIds))
                , ArticleBrief.class);
//            //获取每一篇文章的Brief
//        for(Integer i : articleIds){
//            ArticleBrief article = (ArticleBrief) articleService.displayBrief(
//                    articleService.queryById(i)
//            );
//            if(article != null){
//                briefs.add(article);
//            }
//        }
        return queryBo.getPage(briefs);
    }
}
