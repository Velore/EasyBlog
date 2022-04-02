package velore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import result.Result;
import velore.constants.Constant;
import velore.po.ArticleTag;
import velore.service.ArticleTagService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Velore
 * @date 2022/4/2
 **/
@CrossOrigin
@RestController
@RequestMapping("/articleTag")
@Api("文章标签对应关系")
public class ArticleTagController {

    @Resource
    private ArticleTagService articleTagService;

    @ApiOperation("添加文章标签对应关系")
    @PostMapping("/addArticleTag")
    public Result<String> addArticleTag(
            @RequestHeader(Constant.TOKEN_HEADER_KEY) String token,
            @RequestBody ArticleTag tag){
        articleTagService.add(token, tag);
        return Result.success();
    }

    @ApiOperation("删除文章标签对应关系")
    @PostMapping("/deleteArticleTag/{id}")
    public Result<String> deleteArticleTag(
            @RequestHeader(Constant.TOKEN_HEADER_KEY) String token,
            @PathVariable Integer id){
        articleTagService.delete(token, id);
        return Result.success();
    }

    @ApiOperation("根据文章id查询文章携带的标签")
    @GetMapping("/queryArticleTagByArticleId/{articleId}")
    public Result<List<ArticleTag>> queryArticleTagByArticleId(@PathVariable Integer articleId){
        return Result.success(articleTagService.queryByArticleId(articleId));
    }

    @ApiOperation("根据标签id查询携带该标签的文章")
    @GetMapping("/queryLikeName/{tagId}")
    public Result<List<ArticleTag>> queryArticleTagByTagId(@PathVariable Integer tagId){
        return Result.success(articleTagService.queryByTagId(tagId));
    }
}
