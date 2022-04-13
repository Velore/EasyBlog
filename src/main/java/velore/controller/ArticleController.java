package velore.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import result.Result;
import result.ResultType;
import velore.bo.ArticleQueryBo;
import velore.bo.PageQueryBo;
import velore.constants.ReqConstant;
import velore.po.Article;
import velore.service.base.ArticleService;
import velore.service.Impl.ArticleServiceImpl;
import velore.utils.CastUtil;
import velore.utils.TokenUtil;
import velore.vo.PageResponse;
import velore.vo.ArticleBrief;
import velore.vo.request.ArticleRequest;
import velore.vo.response.ArticleInfoResponse;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Velore
 * @date 2022/4/2
 **/

@CrossOrigin
@RestController
@RequestMapping("/article")
@Api("文章")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @ApiOperation("保存文章为草稿")
    @PutMapping("/draft")
    public Result<String> draft(
            @RequestHeader(ReqConstant.TOKEN_KEY) String token,
            @RequestBody ArticleRequest request){
        ArticleServiceImpl service = (ArticleServiceImpl)articleService;
        service.draft(request.getArticle(token));
        return Result.success("已保存至草稿");
    }

    @ApiOperation("发布文章")
    @PutMapping("/publish")
    public Result<String> publish(
            @RequestHeader(ReqConstant.TOKEN_KEY) String token,
            @RequestBody ArticleRequest request){
        ArticleServiceImpl service = (ArticleServiceImpl)articleService;
        service.publish(request.getArticle(token));
        return Result.success("发布成功");
    }

    @ApiOperation("删除文章")
    @DeleteMapping("/delete")
    public Result<String> delete(
            @RequestHeader(ReqConstant.TOKEN_KEY) String token,
            @RequestBody Integer id){
        articleService.delete(token, id);
        return Result.success("删除成功");
    }

    /**
     * @param id id
     * @return result
     */
    @ApiOperation("文章浏览量+1")
    @GetMapping("/view/{id}")
    public Result<String> view(@PathVariable Integer id){
        ArticleServiceImpl service = (ArticleServiceImpl) articleService;
        service.view(id);
        return Result.success("浏览量+1");
    }

    /**
     * 需要token来保证点赞用户已登录
     * @param token token
     * @param id id
     * @return result
     */
    @ApiOperation("文章点赞量+1")
    @GetMapping("/like/{id}")
    public Result<String> like(
            @RequestHeader(ReqConstant.TOKEN_KEY)String token,
            @PathVariable Integer id){
        if(TokenUtil.isAbandoned(token)){
            return Result.fail(ResultType.NO_PERMIT, "用户已删除,请联系管理员");
        }
        ArticleServiceImpl service = (ArticleServiceImpl) articleService;
        service.like(id);
        return Result.success("点赞成功");
    }

    @PutMapping("/recommend/{id}")
    public Result<String> recommend(
            @RequestHeader(ReqConstant.TOKEN_KEY)String token,
            @PathVariable Integer id){
        if(!TokenUtil.isAdmin(token)){
            return Result.fail(ResultType.ILLEGAL_REQUEST, "只有管理员可以设置推荐");
        }
        articleService.recommend(id);
        return Result.success("已设置为推荐文章");
    }

    @ApiOperation("根据id查询文章")
    @GetMapping("/queryById/{currentPage}")
    public Result<ArticleInfoResponse> queryArticleById(
            @RequestParam(ReqConstant.ARTICLE_ID_KEY) Integer id,
            @RequestParam(value = ReqConstant.TOTAL_RECORD_KEY, defaultValue = "0")Integer total,
            @RequestParam(value = ReqConstant.PAGE_SIZE_KEY, defaultValue = "0") Integer pageSize,
            @PathVariable Integer currentPage){
        PageQueryBo queryBo = new PageQueryBo(currentPage, pageSize, total);
        ArticleInfoResponse response = (ArticleInfoResponse) articleService.displayInfo(
                articleService.queryById(id), queryBo.validate());
        return Result.success(response);
    }

    @ApiOperation("随机查询指定数量的文章")
    @GetMapping("/queryRandom")
    public Result<List<ArticleBrief>> queryRandom(@RequestParam("num") Integer num){
        List<Article> articles = articleService.queryRandom(num);
        List<ArticleBrief> briefResponse = new ArrayList<>(CastUtil.cast(
                articleService.displayBrief(articles), ArticleBrief.class));
//        for(Article article : articles){
//            briefResponses.add((ArticleBrief) articleService.displayBrief(article));
//        }
        return Result.success(briefResponse);
    }

    @ApiOperation("条件查询文章")
    @PostMapping("/queryByQueryBo")
    public Result<PageResponse<ArticleBrief>> queryArticleByQueryBo(
            @RequestBody ArticleQueryBo queryBo){
        //分页查询的条件page
        IPage<Article> articles = articleService.queryByQueryBo((ArticleQueryBo) queryBo.validate());
        PageResponse<ArticleBrief> response = new PageResponse<>(queryBo);
        //添加分页数据
        response.setData(CastUtil.cast(
                articleService.displayBrief(articles.getRecords()), ArticleBrief.class));
        return Result.success(response);
    }
}
