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
        return userService.register(loginRequest) == 1 ?
                Result.success() : Result.fail(ResultType.SYSTEM_ERROR) ;
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserLoginRequest loginRequest) {
        String token = userService.login(loginRequest);
        return Result.success(token);
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
        if(user == null){
            return Result.fail(ResultType.USER_NOT_EXISTS);
        }
        return Result.success(new UserInfoResponse(user));
    }

    @ApiOperation("更新用户信息")
    @PostMapping("updateUser")
    public Result<UserInfoResponse> updateUser(
            @RequestHeader(Constant.TOKEN_HEADER_KEY) String token,
            @RequestBody UserUpdateRequest updateRequest){
        return userService.update(token, updateRequest) == 1 ?
                Result.success() : Result.fail(ResultType.SYSTEM_ERROR) ;
    }

}
