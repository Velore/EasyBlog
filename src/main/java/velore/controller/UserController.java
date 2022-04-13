package velore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import result.Result;
import result.ResultType;
import velore.bo.UserQueryBo;
import velore.constants.ReqConstant;
import velore.po.User;
import velore.service.base.UserService;
import velore.utils.TokenUtil;
import velore.vo.PageResponse;
import velore.vo.request.UserLoginRequest;
import velore.vo.request.UserUpdateRequest;
import velore.vo.response.UserInfoResponse;

import javax.annotation.Resource;

/**
 * @author Velore
 * @date 2022/3/2
 **/
@CrossOrigin
@RestController
@RequestMapping("/user")
@Api("用户")
public class UserController {

    @Resource
    private UserService userService;
//
//    @ApiOperation("hello")
//    @GetMapping("/hello")
//    public Result<String> hello(){
//        return Result.success("hello");
//    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserLoginRequest loginRequest){
        userService.register(loginRequest);
        return Result.success("注册成功");
    }

    @ApiOperation("登录")
    @PutMapping("/login")
    public Result<String> login(@RequestBody UserLoginRequest loginRequest) {
        return Result.success(userService.login(loginRequest));
    }

    /**
     * 刷新token
     * @param token token
     * @return token
     */
    @ApiOperation("刷新token")
    @GetMapping("/refresh")
    public Result<String> refresh(@RequestHeader(ReqConstant.TOKEN_KEY) String token){
        return Result.success(TokenUtil.refresh(token));
    }

    /**
     * 根据请求头中的token返回用户信息
     * @param token token
     * @return user info
     */
    @ApiOperation("根据请求头中的token返回用户信息")
    @GetMapping("/getUserInfo")
    public Result<UserInfoResponse> getUserInfo(@RequestHeader(ReqConstant.TOKEN_KEY) String token){
        User user = userService.queryById(TokenUtil.getTokenId(token));
        return Result.success(new UserInfoResponse(user));
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/update")
    public Result<String> updateUser(
            @RequestHeader(ReqConstant.TOKEN_KEY) String token,
            @RequestBody UserUpdateRequest updateRequest){
        userService.update(token, updateRequest);
        return Result.success("更新成功");
    }

    @ApiOperation("封禁用户")
    @GetMapping("/ban")
    public Result<String> ban(
            @RequestHeader(ReqConstant.TOKEN_KEY) String token,
            @RequestParam(ReqConstant.USER_ID_KEY) Integer userId){
        userService.ban(token, userId);
        return Result.success("封禁成功");
    }

    @ApiOperation("解封用户")
    @GetMapping("/permit")
    public Result<String> permit(
            @RequestHeader(ReqConstant.TOKEN_KEY) String token,
            @RequestParam(ReqConstant.USER_ID_KEY) Integer userId){
        userService.permit(token, userId);
        return Result.success("解封成功");
    }

    @ApiOperation("根据限定条件查询用户")
    @PostMapping("/queryByQueryBo")
    public Result<PageResponse<User>> queryByQueryBo(
            @RequestHeader(ReqConstant.TOKEN_KEY) String token,
            @RequestBody UserQueryBo queryBo){
        if(!TokenUtil.isAdmin(token)){
            return Result.fail(ResultType.ILLEGAL_REQUEST);
        }
        return Result.success(new PageResponse<>(userService.queryByQueryBo((UserQueryBo)queryBo.validate())));
    }

//    @ApiOperation("根据名字模糊查询用户")
//    @GetMapping("/queryUserLikeName/{name}")
//    public Result<List<UserInfoResponse>> queryUserLikeName(
//            @RequestHeader(ReqConstant.TOKEN) String token, @PathVariable String name){
//        if(!TokenUtil.isAdmin(token)){
//            return Result.fail(ResultType.NO_PERMIT);
//        }
//        List<User> users = userService.queryLikeName(name);
//        List<UserInfoResponse> userInfoResponses = new ArrayList<>();
//        for(User u : users){
//            userInfoResponses.add(new UserInfoResponse(u));
//        }
//        return Result.success(userInfoResponses);
//    }
//
//    @ApiOperation("根据用户权限查询所有相同权限的用户")
//    @GetMapping("/queryUserByUserType/{userType}")
//    public Result<List<UserInfoResponse>> queryUserByUserType(
//            @RequestHeader(ReqConstant.TOKEN) String token, @PathVariable Integer userType){
//        if(!TokenUtil.isAdmin(token)){
//            return Result.fail(ResultType.NO_PERMIT);
//        }
//        List<User> users = userService.queryByUserType(userType);
//        List<UserInfoResponse> userInfoResponses = new ArrayList<>();
//        for(User u : users){
//            userInfoResponses.add(new UserInfoResponse(u));
//        }
//        return Result.success(userInfoResponses);
//    }

}
