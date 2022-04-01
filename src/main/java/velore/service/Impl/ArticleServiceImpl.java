package velore.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.RandomUtil;
import velore.bo.ArticleQueryBo;
import velore.constants.ArticleConstant;
import velore.dao.ArticleMapper;
import velore.po.Article;
import velore.service.ArticleService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Velore
 * @date 2022/3/26
 **/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleService articleService;

    @Override
    public int getCount() {
        return baseMapper.getCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(Article article) {
        return baseMapper.insert(article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Article article) {
        return baseMapper.updateById(article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(Integer articleId) {
        Article article = baseMapper.selectById(articleId);
        if(ArticleConstant.ARTICLE_STATUS_DRAFT.equals(article.getStatus())){
            return 1;
        }
        article.setStatus(ArticleConstant.ARTICLE_STATUS_DRAFT);
        return baseMapper.updateById(article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int publish(Integer articleId) {
        Article article = baseMapper.selectById(articleId);
        if(ArticleConstant.ARTICLE_STATUS_PUBLISHED.equals(article.getStatus())){
            return 1;
        }
        article.setStatus(ArticleConstant.ARTICLE_STATUS_PUBLISHED);
        return baseMapper.updateById(article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Integer articleId) {
        Article article = baseMapper.selectById(articleId);
        if(ArticleConstant.ARTICLE_STATUS_DELETED.equals(article.getStatus())){
            return 1;
        }
        article.setStatus(ArticleConstant.ARTICLE_STATUS_DELETED);
        return baseMapper.updateById(article);
    }

    @Override
    public Article queryById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<Article> queryRandom(Integer num) {
        int bound = articleService.getCount();
        if(bound < num){
            num = bound;
        }
        Set<Integer> idSet = new TreeSet<>();
        List<Article> articleList = new ArrayList<>();
        while(idSet.size() < num){
            int randomId = RandomUtil.randomInt(bound);
            //若id不在set中
            if(idSet.add(randomId)){
                Article article = articleService.queryById(randomId);
                //若文章隐藏,则不添加
                if(article.getVisible()){
                    articleList.add(article);
                }
            }
        }
        return articleList;
    }

    @Override
    public List<Article> queryByQueryBo(ArticleQueryBo queryBo) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        if(queryBo.getArticleTypeId()!=null){
            wrapper.eq("article_type", queryBo.getArticleTypeId());
        }
        if(queryBo.getUserId()!=null){
            wrapper.eq("user_id", queryBo.getUserId());
        }
        if(queryBo.getTitle()!=null){
            wrapper.like("title", queryBo.getTitle());
        }
        if(queryBo.getPublishAfter()!=null){
            wrapper.ge("publish_time", queryBo.getPublishAfter());
        }
        if(queryBo.getPublishBefore()!=null){
            wrapper.le("publish_time", queryBo.getPublishAfter());
        }
        return baseMapper.selectList(wrapper);
    }
}
