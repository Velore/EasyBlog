package velore.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import result.Result;
import velore.bo.PageQueryBo;
import velore.constants.ReqConstant;
import velore.po.ArticleType;
import velore.service.base.ArticleTypeService;
import velore.vo.PageResponse;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Velore
 * @date 2022/4/2
 **/

@CrossOrigin
@RestController
@RequestMapping("/articleType")
@Api("文章类型")
public class ArticleTypeController {

    @Resource
    private ArticleTypeService articleTypeService;

    @ApiOperation("添加文章类型")
    @PostMapping("/add")
    public Result<String> addArticleType(@RequestBody ArticleType type){
        articleTypeService.add(type);
        return Result.success("新增成功");
    }

    @ApiOperation("更新文章类型")
    @PostMapping("/update")
    public Result<String> updateArticleType(@RequestBody ArticleType type){
        articleTypeService.update(type);
        return Result.success("更新成功");
    }

    @ApiOperation("删除文章类型")
    @DeleteMapping("/delete")
    public Result<String> deleteArticleType(@RequestBody Integer id){
        articleTypeService.delete(id);
        return Result.success("删除成功");
    }

    @ApiOperation("根据id查询文章类型")
    @GetMapping("/queryById")
    public Result<ArticleType> queryById(@RequestParam(ReqConstant.ARTICLE_TYPE_ID_KEY) Integer id){
        return Result.success(articleTypeService.queryById(id));
    }

    @ApiOperation("查询全部文章类型")
    @GetMapping("/queryAll/{currentPage}")
    public Result<PageResponse<ArticleType>> queryAll(
            @RequestParam(value = ReqConstant.TOTAL_RECORD_KEY, defaultValue = "0") Integer total,
            @RequestParam(value = ReqConstant.PAGE_SIZE_KEY, defaultValue = "0") Integer pageSize,
            @PathVariable Integer currentPage){
        PageQueryBo queryBo = new PageQueryBo(currentPage, pageSize, total);
        IPage<ArticleType> page = articleTypeService.queryAll(queryBo.validate());
        return Result.success(new PageResponse<>(page));
    }

    @ApiOperation("查询指定数量的随机文章类型")
    @GetMapping("/queryRandom/{num}")
    public Result<List<ArticleType>> queryRandom(@PathVariable Integer num){
        return Result.success(articleTypeService.queryRandom(num));
    }

    @ApiOperation("根据名字模糊查询文章类型")
    @GetMapping("/queryLikeName/{currentPage}")
    public Result<PageResponse<ArticleType>> queryLikeName(
            @RequestParam("articleName") String name,
            @RequestParam(value = ReqConstant.TOTAL_RECORD_KEY, defaultValue = "0") Integer totalRecord,
            @RequestParam(value = ReqConstant.PAGE_SIZE_KEY, defaultValue = "0") Integer pageSize,
            @PathVariable Integer currentPage){
        PageQueryBo queryBo = new PageQueryBo(currentPage, pageSize, totalRecord);
        return Result.success(new PageResponse<>(articleTypeService.queryLikeName(name, queryBo.validate())));
    }
}
