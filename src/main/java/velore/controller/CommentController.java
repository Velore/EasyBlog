package velore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import result.Result;
import velore.bo.CommentQueryBo;
import velore.constants.Constant;
import velore.po.Comment;
import velore.service.CommentService;
import velore.vo.request.CommentRequest;

import javax.annotation.Resource;
import java.util.List;

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
    @PostMapping("/publish")
    public Result<String> publish(
            @RequestHeader(Constant.TOKEN_HEADER_KEY)String token,
            @RequestBody CommentRequest request){
        commentService.add(new Comment(token, request));
        return Result.success();
    }

    @ApiOperation("删除评论")
    @DeleteMapping("/deleteComment/{id}")
    public Result<String> deleteComment(
            @RequestHeader(Constant.TOKEN_HEADER_KEY)String token,
            @PathVariable Integer id){
        commentService.delete(token, id);
        return Result.success();
    }

    @ApiOperation("根据id查询评论")
    @GetMapping("/queryCommentById/{id}")
    public Result<Comment> queryCommentById(@PathVariable Integer id){
        return Result.success(commentService.queryById(id));
    }

    @ApiOperation("根据QueryBo查询评论")
    @PostMapping("/queryAllCommentByQueryBo")
    public Result<List<Comment>> queryAllCommentByQueryBo(@RequestBody CommentQueryBo queryBo){
        return Result.success(commentService.queryAllByQueryBo(queryBo));
    }
}