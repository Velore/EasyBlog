package velore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import result.Result;
import velore.bo.ArticleQueryBo;
import velore.constants.Constant;
import velore.po.Article;
import velore.service.ArticleService;
import velore.service.Impl.ArticleServiceImpl;
import velore.vo.request.ArticleRequest;

import javax.annotation.Resource;
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
    public Result<Article> queryArticleById(@PathVariable Integer id){
        return Result.success(articleService.queryById(id));
    }

    @ApiOperation("随机查询指定数量的文章")
    @GetMapping("/queryRandom/{num}")
    public Result<List<Article>> queryRandom(@PathVariable Integer num){
        return Result.success(articleService.queryRandom(num));
    }

    @ApiOperation("条件查询文章")
    @PostMapping("/queryArticleByQueryBo")
    public Result<List<Article>> queryArticleByQueryBo(@RequestBody ArticleQueryBo queryBo){
        return Result.success(articleService.queryByQueryBo(queryBo));
    }
}
