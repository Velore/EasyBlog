package velore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import result.Result;
import velore.po.Tag;
import velore.service.TagService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Velore
 * @date 2022/3/17
 **/
@CrossOrigin
@RestController
@RequestMapping("tag")
@Api("标签")
public class TagController {

    @Resource
    TagService tagService;

    @ApiOperation("新增标签")
    @PostMapping("/addTag")
    public Result<String> addTag(@RequestBody Tag tag){
        tagService.add(tag);
        return Result.success();
    }

    @ApiOperation("更新标签")
    @PostMapping("/updateTag")
    public Result<String> updateTag(@RequestBody Tag tag){
        tagService.update(tag);
        return Result.success();
    }

    @ApiOperation("删除标签")
    @GetMapping("/deleteTag/{id}")
    public Result<String> deleteTag(@PathVariable Integer id){
        tagService.delete(id);
        return Result.success();
    }

    @ApiOperation("查询全部标签")
    @GetMapping("/queryAllTag")
    public Result<List<Tag>> queryAllTag(){
        return Result.success(tagService.queryAll());
    }

    @ApiOperation("根据id查询标签")
    @GetMapping("/queryTagById/{id}")
    public Result<Tag> queryTagById(@PathVariable Integer id){
        return Result.success(tagService.queryById(id));
    }

    @ApiOperation("查询指定数量的随机标签")
    @GetMapping("/queryRandomTag/{num}")
    public Result<List<Tag>> queryRandomTag(@PathVariable Integer num){
        return Result.success(tagService.queryRandom(num));
    }

    @ApiOperation("根据名字模糊查询标签")
    @GetMapping("/queryTagLikeName/{name}")
    public Result<List<Tag>> queryTagLikeName(@PathVariable String name){
        return Result.success(tagService.queryLikeName(name));
    }
}
