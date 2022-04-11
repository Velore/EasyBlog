package velore.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.RandomUtil;
import velore.bo.ArticleQueryBo;
import velore.bo.CommentQueryBo;
import velore.bo.PageQueryBo;
import velore.constants.ArticleConstant;
import velore.dao.ArticleMapper;
import velore.exception.IllegalRequestException;
import velore.exception.InvalidParamException;
import velore.po.Article;
import velore.service.base.ArticleService;
import velore.service.base.ArticleTagService;
import velore.service.base.CommentService;
import velore.service.base.UserService;
import velore.utils.TokenUtil;
import velore.vo.ArticleBrief;
import velore.vo.response.ArticleInfoResponse;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Velore
 * @date 2022/3/26
 **/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService{

    @Resource
    private UserService userService;

    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleTagService articleTagService;

    @Resource
    private CommentService commentService;

    @Override
    public Object displayBrief(Article article) {
        ArticleBrief briefResponse = new ArticleBrief();
        briefResponse.setDependAttributes(article)
                .setAuthor(userService.queryById(article.getUserId()));
        return briefResponse;
    }

    @Override
    public Object displayInfo(Article article) {
        return null;
    }

    @Override
    public Object displayInfo(Article article, PageQueryBo queryBo){
        ArticleInfoResponse infoResponse = new ArticleInfoResponse();
        infoResponse.setArticle(article);
        infoResponse.setAuthor(userService.queryById(article.getUserId()));
        infoResponse.setTags(articleTagService.queryByArticleId(article.getId()));
        CommentQueryBo commentQueryBo = new CommentQueryBo(article.getId(), queryBo);
        infoResponse.setComments(commentService.queryByQueryBo(commentQueryBo).getRecords());
        return infoResponse;
    }

    @Override
    public boolean draft(Article article) {
        article.setStatus(ArticleConstant.ARTICLE_STATUS_DRAFT);
        //文章不存在
        if(articleService.queryById(article.getId())==null){
            return articleService.add(article);
        }
        //文章存在
        if(!ArticleConstant.ARTICLE_STATUS_DRAFT.equals(article.getStatus())){
            return articleService.update(article);
        }
        //文章存在且不需要更新
        return true;
    }

    @Override
    public boolean publish(Article article) {
        article.setStatus(ArticleConstant.ARTICLE_STATUS_PUBLISHED);
        article.setPublishTime(LocalDateTime.now());
        //文章不存在
        if(articleService.queryById(article.getId())==null){
            return articleService.add(article);
        }
        //文章存在
        if(!ArticleConstant.ARTICLE_STATUS_PUBLISHED.equals(article.getStatus())){
            return articleService.update(article);
        }
        //文章存在且不需要更新
        return true;
    }

    @Override
    public boolean viewAll(Map<Integer, Integer> viewList){
        if(viewList == null || viewList.isEmpty()){
            throw new InvalidParamException("viewList为空");
        }
        Article article;
        List<Article> list = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: viewList.entrySet()){
            article = articleService.queryById(entry.getKey());
            if(article != null){
                article.setViews(article.getViews()+entry.getValue());
                list.add(article);
            }
        }
        return articleService.updateBatchById(list);
    }

    @Override
    public boolean view(Integer articleId, Integer num) {
        Article article = articleService.queryById(articleId);
        article.setViews(article.getViews()+num);
        return articleService.update(article);
    }

    /**
     * 该方法用于不使用缓存的情况
     * @param articleId articleId
     * @return int
     */
    @Override
    public boolean view(Integer articleId){
        ArticleServiceImpl service = (ArticleServiceImpl) articleService;
        return service.view(articleId, 1);
    }

    @Override
    public boolean likeAll(Map<Integer, Integer> likeList){
        if(likeList == null || likeList.isEmpty()){
            throw new InvalidParamException("likeList为空");
        }
        Article article;
        List<Article> list = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: likeList.entrySet()){
            article = articleService.queryById(entry.getKey());
            if(article != null){
                article.setLikeNum(article.getLikeNum()+entry.getValue());
                list.add(article);
            }
        }
        return articleService.updateBatchById(list);
    }

    @Override
    public boolean like(Integer articleId, Integer num) {
        Article article = articleService.queryById(articleId);
        article.setLikeNum(article.getLikeNum()+num);
        return articleService.update(article);
    }

    /**
     * 该方法用于不使用缓存的情况
     * @param articleId articleId
     * @return int
     */
    @Override
    public boolean like(Integer articleId){
        ArticleServiceImpl service = (ArticleServiceImpl) articleService;
        return service.like(articleId, 1);
    }

    @Override
    public boolean recommend(String token, Integer articleId) {
        Article article = articleService.queryById(articleId);
        article.setRecommend(true);
        return articleService.update(article);
    }

    @Override
    public int getTotal() {
        return baseMapper.getTotal();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(Article article) {
        return baseMapper.insert(article) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Article article) {
        return articleService.updateById(article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String token, Integer articleId) {
        Article article = articleService.queryById(articleId);
        if(article == null){
            throw new InvalidParamException("文章不存在");
        }
        if(TokenUtil.getTokenId(token)!= article.getUserId()){
            throw new IllegalRequestException("只能删除自己的文章");
        }
        if(ArticleConstant.ARTICLE_STATUS_DELETED.equals(article.getStatus())){
            return true;
        }
        article.setStatus(ArticleConstant.ARTICLE_STATUS_DELETED);
        return articleService.updateById(article);
    }

    @Override
    public Article queryById(Integer id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<Article> queryRandom(Integer num) {
        int bound = articleService.getTotal();
        if(bound < num){
            num = bound;
        }
        Set<Integer> idSet = new TreeSet<>();
        List<Article> articleList = new ArrayList<>();
        //breakLoopNum = 总循环次数 - 有效循环的次数
        //loop == idSet.size()+breakLoopNum 表示循环已经无法获取更多,可以退出循环了
        for(int loop = 0, breakLoopNum = 3;idSet.size() <= num && loop < idSet.size()+breakLoopNum;loop++){
            int randomId = RandomUtil.randomInt(1, bound);
            //若id不在set中
            if(idSet.add(randomId)){
                Article article = articleService.queryById(randomId);
                //若文章存在且没有设置隐藏,则添加
                if(article!=null && article.getVisible()){
                    articleList.add(article);
                    continue;
                }
                //文章没有被添加时删除本次id
                idSet.remove(randomId);
            }
        }
        return articleList;
    }

    @Override
    public IPage<Article> queryByQueryBo(ArticleQueryBo queryBo) {
        IPage<Article> page = queryBo.getPage();
        LambdaQueryChainWrapper<Article> wrapper = new LambdaQueryChainWrapper<>(this.baseMapper);
        //默认不显示隐藏文章
        wrapper.eq(Article::getVisible, false);
        if(queryBo.getArticleTypeId()!=null){
            wrapper.eq(Article::getArticleType, queryBo.getArticleTypeId());
        }
        if(queryBo.getUserId()!=null){
            //用户可查看自己的隐藏文章
            wrapper.eq(Article::getVisible, true);
            wrapper.eq(Article::getUserId, queryBo.getUserId());
        }else{
            //否则只查询已发布文章
            wrapper.eq(Article::getStatus, ArticleConstant.ARTICLE_STATUS_PUBLISHED);
        }
        if(queryBo.getTitle()!=null){
            wrapper.like(Article::getTitle, queryBo.getTitle());
        }
        if(queryBo.getPublishAfter()!=null){
            wrapper.ge(Article::getPublishTime, queryBo.getPublishAfter());
        }
        if(queryBo.getPublishBefore()!=null){
            wrapper.le(Article::getPublishTime, queryBo.getPublishAfter());
        }
        return baseMapper.selectPage(page, wrapper.getWrapper());
    }
}
