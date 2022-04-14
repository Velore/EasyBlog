package velore.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import result.Result;
import result.ResultType;
import velore.bo.PageQueryBo;
import velore.bo.TagQueryBo;
import velore.constants.ReqConstant;
import velore.po.Tag;
import velore.service.base.TagService;
import velore.vo.PageResponse;
import velore.vo.request.TagRequest;

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
    private TagService tagService;

    @ApiOperation("新增标签")
    @PutMapping("/add")
    public Result<String> addTag(@RequestBody TagRequest request){
        tagService.add(request.getTag());
        return Result.success("新增标签");
    }

    @ApiOperation("更新标签")
    @PostMapping("/update")
    public Result<String> updateTag(@RequestBody Tag tag){
        tagService.update(tag);
        return Result.success("更新成功");
    }

    @ApiOperation("删除标签")
    @DeleteMapping("/delete")
    public Result<String> deleteTag(@RequestBody Integer id){
        tagService.delete(id);
        return Result.success("删除成功");
    }

    @ApiOperation("查询全部标签")
    @GetMapping("/queryAll/{currentPage}")
    public Result<PageResponse<Tag>> queryAll(
            @RequestParam(value = ReqConstant.TOTAL_RECORD_KEY, defaultValue = "0")Integer total,
            @RequestParam(value = ReqConstant.PAGE_SIZE_KEY, defaultValue = "0") Integer size,
            @PathVariable Integer currentPage){
        TagQueryBo queryBo = new TagQueryBo(new PageQueryBo(currentPage, size ,total));
        IPage<Tag> page = tagService.queryLikeName((TagQueryBo)queryBo.validate());
        return Result.success(new PageResponse<>(page));
    }

    @ApiOperation("根据id查询标签")
    @GetMapping("/queryById")
    public Result<Tag> queryById(@RequestParam(ReqConstant.TAG_ID_KEY) Integer id){
        return Result.success(tagService.queryById(id));
    }

    @ApiOperation("查询指定数量的随机标签")
    @GetMapping("/queryRandom/{num}")
    public Result<List<Tag>> queryRandom(@PathVariable Integer num){
        if(num <= 0){
            return Result.fail(ResultType.INVALID_PARAM);
        }
        return Result.success(tagService.queryRandom(num));
    }

    @ApiOperation("根据名字模糊查询标签")
    @GetMapping("/queryLikeName/{currentPage}")
    public Result<PageResponse<Tag>> queryLikeName(
            @RequestParam(ReqConstant.TAG_NAME_KEY) String name,
            @RequestParam(value = ReqConstant.TOTAL_RECORD_KEY, defaultValue = "0")Integer total,
            @RequestParam(value = ReqConstant.PAGE_SIZE_KEY, defaultValue = "0") Integer size,
            @PathVariable Integer currentPage){
        TagQueryBo queryBo = new TagQueryBo(name, new PageQueryBo(currentPage, size, total));
        IPage<Tag> page = tagService.queryLikeName((TagQueryBo)queryBo.validate());
        return Result.success(new PageResponse<>(page));
    }
}
