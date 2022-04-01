package velore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import result.Result;
import result.ResultType;
import velore.constants.Constant;
import velore.po.User;
import velore.security.TokenService;
import velore.service.UserService;
import velore.vo.request.UserLoginRequest;
import velore.vo.request.UserUpdateRequest;
import velore.vo.response.UserInfoResponse;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private TokenService tokenService;

    @ApiOperation("hello")
    @GetMapping("/hello")
    public Result<String> hello(){
        return Result.success("hello");
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserLoginRequest loginRequest){
        userService.register(loginRequest);
        return Result.success();
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserLoginRequest loginRequest) {
        return Result.success(userService.login(loginRequest));
    }

    @ApiOperation("刷新token")
    @GetMapping("/refresh")
    public Result<String> refresh(@RequestHeader(Constant.TOKEN_HEADER_KEY) String token){
        return Result.success(tokenService.refresh(token));
    }

    /**
     * 根据请求头中的token返回用户信息
     * @param token token
     * @return user info
     */
    @ApiOperation("根据请求头中的token返回用户信息")
    @GetMapping("/getUserInfo")
    public Result<UserInfoResponse> getUserInfo(@RequestHeader(Constant.TOKEN_HEADER_KEY) String token){
        User user = userService.queryById(tokenService.getTokenId(token));
        return Result.success(new UserInfoResponse(user));
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/updateUser")
    public Result<String> updateUser(
            @RequestHeader(Constant.TOKEN_HEADER_KEY) String token,
            @RequestBody UserUpdateRequest updateRequest){
        userService.update(token, updateRequest);
        return Result.success();
    }

    @ApiOperation("封禁用户")
    @GetMapping("/ban/{userId}")
    public Result<String> ban(@RequestHeader(Constant.TOKEN_HEADER_KEY) String token, @PathVariable String userId){
        userService.ban(token, Integer.parseInt(userId));
        return Result.success();
    }

    @ApiOperation("解封用户")
    @GetMapping("/permit/{userId}")
    public Result<String> permit(@RequestHeader(Constant.TOKEN_HEADER_KEY) String token, @PathVariable String userId){
        userService.permit(token, Integer.parseInt(userId));
        return Result.success();
    }

    @ApiOperation("根据名字模糊查询用户")
    @GetMapping("/queryUserLikeName/{name}")
    public Result<List<User>> queryUserLikeName(
            @RequestHeader(Constant.TOKEN_HEADER_KEY) String token, @PathVariable String name){
        if(!Constant.ADMIN_ID_SET.contains(tokenService.getTokenId(token))){
            return Result.fail(ResultType.NO_PERMIT);
        }
        return Result.success(userService.queryLikeName(name));
    }

    @ApiOperation("根据用户权限查询所有相同权限的用户")
    @GetMapping("/queryUserByUserType/{userType}")
    public Result<List<User>> queryUserByUserType(
            @RequestHeader(Constant.TOKEN_HEADER_KEY) String token, @PathVariable Integer userType){
        if(!Constant.ADMIN_ID_SET.contains(tokenService.getTokenId(token))){
            return Result.fail(ResultType.NO_PERMIT);
        }
        return Result.success(userService.queryByUserType(userType));
    }

}
