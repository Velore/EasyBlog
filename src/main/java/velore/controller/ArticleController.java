package velore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import result.Result;
import velore.bo.ArticleQueryBo;
import velore.constants.Constant;
import velore.po.Article;
import velore.service.base.ArticleService;
import velore.service.Impl.ArticleServiceImpl;
import velore.vo.request.ArticleRequest;
import velore.vo.response.ArticleBriefResponse;
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
    @PostMapping("/draft")
    public Result<String> draft(
            @RequestHeader(Constant.TOKEN_HEADER_KEY) String token,
            @RequestBody ArticleRequest request){
        ArticleServiceImpl service = (ArticleServiceImpl)articleService;
        service.draft(new Article(token, request));
        return Result.success();
    }

    @ApiOperation("发布文章")
    @PostMapping("/publish")
    public Result<String> publish(
            @RequestHeader(Constant.TOKEN_HEADER_KEY) String token,
            @RequestBody ArticleRequest request){
        ArticleServiceImpl service = (ArticleServiceImpl)articleService;
        service.publish(new Article(token, request));
        return Result.success();
    }

    @ApiOperation("删除文章")
    @GetMapping("/delete/{id}")
    public Result<String> delete(
            @RequestHeader(Constant.TOKEN_HEADER_KEY) String token,
            @PathVariable Integer id){
        articleService.delete(token, id);
        return Result.success();
    }

    @ApiOperation("文章浏览量+1")
    @GetMapping("/view/{id}")
    public Result<String> view(@PathVariable Integer id){
        ArticleServiceImpl service = (ArticleServiceImpl) articleService;
        service.view(id);
        return Result.success();
    }

    @ApiOperation("文章点赞量+1")
    @GetMapping("/like/{id}")
    public Result<String> like(@PathVariable Integer id){
        ArticleServiceImpl service = (ArticleServiceImpl) articleService;
        service.like(id);
        return Result.success();
    }

    @ApiOperation("根据id查询文章")
    @GetMapping("/queryArticleById/{id}")
    public Result<ArticleInfoResponse> queryArticleById(@PathVariable Integer id){
        return Result.success((ArticleInfoResponse) articleService.displayInfo(articleService.queryById(id)));
    }

    @ApiOperation("随机查询指定数量的文章")
    @GetMapping("/queryRandom/{num}")
    public Result<List<ArticleBriefResponse>> queryRandom(@PathVariable Integer num){
        List<Article> articles = articleService.queryRandom(num);
        List<ArticleBriefResponse> briefResponses = new ArrayList<>();
        for(Article article : articles){
            briefResponses.add((ArticleBriefResponse) articleService.displayBrief(article));
        }
        return Result.success(briefResponses);
    }

    @ApiOperation("条件查询文章")
    @PostMapping("/queryArticleByQueryBo")
    public Result<List<ArticleBriefResponse>> queryArticleByQueryBo(@RequestBody ArticleQueryBo queryBo){
        List<Article> articles = articleService.queryByQueryBo(queryBo);
        List<ArticleBriefResponse> briefResponses = new ArrayList<>();
        for(Article article : articles){
            briefResponses.add((ArticleBriefResponse) articleService.displayBrief(article));
        }
        return Result.success(briefResponses);
    }
}
