package velore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import result.Result;
import velore.bo.PageQueryBo;
import velore.constants.ReqConstant;
import velore.po.Tag;
import velore.service.base.ArticleTagService;
import velore.utils.TokenUtil;
import velore.vo.ArticleBrief;
import velore.vo.PageResponse;
import velore.vo.request.ArticleTagRequest;

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
    @PostMapping("/add")
    public Result<String> addArticleTag(
            @RequestHeader(ReqConstant.TOKEN_KEY) String token,
            @RequestBody ArticleTagRequest request){
        Assert.isTrue(TokenUtil.isAdmin(token) || TokenUtil.isOrdinary(token), "权限不足");
        articleTagService.add(request.getArticleTag());
        return Result.success("标签添加成功");
    }

    @ApiOperation("删除文章标签对应关系")
    @DeleteMapping("/delete")
    public Result<String> deleteArticleTag(
            @RequestHeader(ReqConstant.TOKEN_KEY) String token,
            @RequestBody Integer id){
        Assert.isTrue(TokenUtil.isAdmin(token), "权限不足");
        articleTagService.delete(id);
        return Result.success("标签删除成功");
    }

    @ApiOperation("根据文章id查询文章携带的标签")
    @GetMapping("/queryByArticleId")
    public Result<List<Tag>> queryArticleTagByArticleId(
            @RequestParam(ReqConstant.ARTICLE_ID_KEY) Integer id){
        return Result.success(articleTagService.queryByArticleId(id));
    }

    @ApiOperation("根据标签id查询携带该标签的文章")
    @GetMapping("/queryLikeName/{currentPage}")
    public Result<PageResponse<ArticleBrief>> queryArticleTagByTagId(
            @RequestParam(ReqConstant.TAG_ID_KEY) Integer tagId,
            @RequestParam(value = ReqConstant.TOTAL_RECORD_KEY, defaultValue = "0") Integer totalRecord,
            @RequestParam(value = ReqConstant.PAGE_SIZE_KEY, defaultValue = "0") Integer pageSize,
            @PathVariable Integer currentPage){
        PageQueryBo queryBo = new PageQueryBo(currentPage, pageSize, totalRecord);
        return Result.success(new PageResponse<>(articleTagService.queryByTagId(tagId, queryBo.validate())));
    }
}
