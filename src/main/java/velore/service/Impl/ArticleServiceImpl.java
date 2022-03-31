package velore.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import velore.bo.ArticleQueryBo;
import velore.constants.ArticleConstant;
import velore.dao.ArticleMapper;
import velore.po.Article;
import velore.service.ArticleService;

import java.util.List;

/**
 * @author Velore
 * @date 2022/3/26
 **/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public int getCount() {
        return baseMapper.getCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addArticle(Article article) {
        return baseMapper.insert(article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateArticle(Article article) {
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
    public int deleteArticle(Integer articleId) {
        Article article = baseMapper.selectById(articleId);
        if(ArticleConstant.ARTICLE_STATUS_DELETED.equals(article.getStatus())){
            return 1;
        }
        article.setStatus(ArticleConstant.ARTICLE_STATUS_DELETED);
        return baseMapper.updateById(article);
    }

    @Override
    public Article queryArticleById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<Article> queryRandomArticle(Integer num) {
        return null;
    }

    @Override
    public List<Article> queryArticleByArticleQueryBo(ArticleQueryBo queryBo) {
        return null;
    }
}
