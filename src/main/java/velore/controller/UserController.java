package velore.controller;

import org.springframework.web.bind.annotation.*;
import velore.constants.Constant;
import velore.po.User;
import velore.security.TokenService;
import velore.service.UserService;
import velore.utils.ResultUtil;
import velore.vo.request.UserLoginRequest;
import velore.vo.response.UserInfoResponse;
import velore.vo.result.Result;
import velore.vo.result.ResultType;

import javax.annotation.Resource;

/**
 * @author Velore
 * @date 2022/3/2
 **/
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    @PostMapping("login")
    public Result<String> login(@RequestBody UserLoginRequest loginRequest) {
        String token = userService.login(loginRequest);
        return ResultUtil.success(token);
    }

    @PostMapping("register")
    public Result<String> register(@RequestBody UserLoginRequest loginRequest){
        return userService.register(loginRequest) == 1 ?
                ResultUtil.success() : ResultUtil.fail(ResultType.SYSTEM_ERROR) ;
    }

//    /**
//     * 验证请求头中的token的接口
//     * @param token token
//     * @return string result
//     */
//    @GetMapping("verify")
//    public Result<String> verify(@RequestHeader(Constant.TOKEN_HEADER_KEY) String token){
//        try{
//            tokenService.verify(token);
//        }catch (Exception e){
//            return ResultUtil.fail(ResultType.TOKEN_EXPIRE);
//        }
//        return ResultUtil.success();
//    }

    @GetMapping("refresh")
    public Result<String> refresh(@RequestHeader(Constant.TOKEN_HEADER_KEY) String token){
        return ResultUtil.success(tokenService.refresh(token));
    }

    /**
     * 根据请求头中的token返回用户信息
     * @param token token
     * @return user info
     */
    @GetMapping("getUserInfo")
    public Result<UserInfoResponse> getUserInfo(@RequestHeader(Constant.TOKEN_HEADER_KEY) String token){
        User user = userService.queryUserById(Integer.parseInt(tokenService.getTokenId(token)));
        if(user == null){
            return ResultUtil.fail(ResultType.USER_NOT_EXISTS);
        }
        return ResultUtil.success(new UserInfoResponse(user));
    }

}
