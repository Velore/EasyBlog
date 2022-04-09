package velore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import result.Result;
import velore.po.ArticleType;
import velore.service.base.ArticleTypeService;

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
    @PostMapping("/addArticleType")
    public Result<String> addArticleType(@RequestBody ArticleType type){
        articleTypeService.add(type);
        return Result.success();
    }

    @ApiOperation("更新文章类型")
    @PostMapping("/updateArticleType")
    public Result<String> updateArticleType(@RequestBody ArticleType type){
        articleTypeService.update(type);
        return Result.success();
    }

    @ApiOperation("删除文章类型")
    @PostMapping("/deleteArticleType/{id}")
    public Result<String> deleteArticleType(@PathVariable Integer id){
        articleTypeService.delete(id);
        return Result.success();
    }

    @ApiOperation("根据id查询文章类型")
    @GetMapping("/queryArticleTypeById/{id}")
    public Result<ArticleType> queryArticleTypeById(@PathVariable Integer id){
        return Result.success(articleTypeService.queryById(id));
    }

    @ApiOperation("查询全部文章类型")
    @GetMapping("/query")
    public Result<List<ArticleType>> queryAll(){
        return Result.success(articleTypeService.queryAll());
    }

    @ApiOperation("查询指定数量的随机文章类型")
    @GetMapping("/queryRandom/{num}")
    public Result<List<ArticleType>> queryRandom(@PathVariable Integer num){
        return Result.success(articleTypeService.queryRandom(num));
    }

    @ApiOperation("根据名字模糊查询文章类型")
    @GetMapping("/queryLikeName/{name}")
    public Result<List<ArticleType>> queryLikeName(@PathVariable String name){
        return Result.success(articleTypeService.queryLikeName(name));
    }
}
