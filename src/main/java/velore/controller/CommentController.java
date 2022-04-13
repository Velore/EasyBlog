package velore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import result.Result;
import velore.constants.ReqConstant;
import velore.service.base.CommentService;
import velore.vo.request.CommentRequest;

import javax.annotation.Resource;

/**
 * @author Velore
 * @date 2022/4/1
 **/
@CrossOrigin
@RestController
@RequestMapping("/comment")
@Api("评论")
public class CommentController {

    @Resource
    private CommentService commentService;

    @ApiOperation("发布评论")
    @PutMapping("/publish")
    public Result<String> publish(
            @RequestHeader(ReqConstant.TOKEN_KEY)String token,
            @RequestBody CommentRequest request){
        commentService.add(request.getComment(token));
        return Result.success("评论发布成功");
    }

    @ApiOperation("删除评论")
    @DeleteMapping("/delete")
    public Result<String> deleteComment(
            @RequestHeader(ReqConstant.TOKEN_KEY)String token,
            @RequestBody Integer id){
        commentService.delete(token, id);
        return Result.success("评论删除成功");
    }
//
//    @ApiOperation("根据id查询评论")
//    @GetMapping("/queryById/{id}")
//    public Result<Comment> queryById(@PathVariable Integer id){
//        return Result.success(commentService.queryById(id));
//    }
//
//    @ApiOperation("根据QueryBo查询评论")
//    @PostMapping("/queryByQueryBo")
//    public Result<PageResponse<Comment>> queryByQueryBo(
//            @RequestBody CommentQueryBo queryBo){
//        IPage<Comment> page = commentService.queryByQueryBo((CommentQueryBo)queryBo.validate(queryBo));
//        return Result.success(new PageResponse<>(page));
//    }
//
//    @ApiOperation("根据QueryBo查询评论,使用GetMapping")
//    @GetMapping("/queryAll/{currentPage}/{pageSize}")
//    public Result<PageResponse<Comment>> queryAll(
//            @PathVariable Integer currentPage,
//            @PathVariable Integer pageSize,
//            @RequestParam("articleId") Integer articleId){
//        CommentQueryBo queryBo = new CommentQueryBo(currentPage, pageSize, null);
//        queryBo.setArticleId(articleId);
//        IPage<Comment> page = commentService.queryByQueryBo((CommentQueryBo)queryBo.validate(queryBo));
//        return Result.success(new PageResponse<>(page));
//    }
}
