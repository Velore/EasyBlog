package velore.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import velore.po.User;
import velore.po.UserType;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Velore
 * @date 2022/4/2
 **/
public class TokenUtil {

    /**
     * 单位：天
     */
    private static final int CALENDAR_FIELD = Calendar.DATE;

    /**
     * token 过期时间: 7天
     */
    private static final int CALENDAR_INTERVAL = 7;

    /**
     * token密钥
     */
    private static final String SECRET = "token secret service";

    /**
     * 生成token
     * @param user user
     * @return String token
     */
    public static String generate(User user){
        // 获取过期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(CALENDAR_FIELD, CALENDAR_INTERVAL);
        Date expiresDate = nowTime.getTime();

        // 头信息
        Map<String, Object> map = new HashMap<>(2);
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        return JWT.create().withHeader(map)
                //token的签发证明人
                .withIssuer("SERVICE")
                //token的有效持有者
                .withAudience(String.valueOf(user.getId()))
                //token的用户权限
                .withClaim("auth", user.getUserType())
                //token签发的时间
                .withIssuedAt(Calendar.getInstance().getTime())
                //token过期的时间
                .withExpiresAt(expiresDate)
                .sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 刷新token的过期时间
     * @param token old token
     * @return new token
     */
    public static String refresh(String token) {
        DecodedJWT jwt = JWT.decode(token);
        User user = new User();
        user.setId(Integer.parseInt(jwt.getAudience().get(0)));
        user.setUserType(jwt.getClaims().get("auth").asInt());
        return generate(user);
    }

    /**
     * 验证Token
     * 验证成功什么也不做
     * 验证失败抛出异常
     * @param token token
     */
    public static void verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("token过期");
        }
    }

    /**
     * 不验证token,直接获取token中的id
     * 数据库id而非userId
     * 通常用于验证完token后验证用户是否存在
     * @param token token
     * @return uid
     */
    public static int getTokenId(String token){
        if(StringUtils.isEmpty(token)) {
            return -1;
        }
        DecodedJWT jwt = JWT.decode(token);
        return Integer.parseInt(jwt.getAudience().get(0));
    }

    /**
     * 检查登录用户是否是普通用户
     * @param token token
     * @return boolean
     */
    public static boolean isOrdinary(String token){
        return getTokenAuth(token) == UserType.ORDINARY.getValue();
    }

    /**
     * 检查登录用户是否是管理员
     * @param token token
     * @return boolean
     */
    public static boolean isAdmin(String token){
        return getTokenAuth(token) == UserType.ADMIN.getValue();
    }

    /**
     * 检查登录用户是否是封禁用户
     * @param token token
     * @return boolean
     */
    public static boolean isForbid(String token){
        return getTokenAuth(token) == UserType.FORBID.getValue();
    }

    /**
     * 检查用户账号是否可用
     * 可能已被删除
     * @param token token
     * @return boolean
     */
    public static boolean isAbandoned(String token){
        return getTokenAuth(token) == UserType.DELETE.getValue();
    }

    /**
     * token是否为授权用户或者是管理员
     * @param token token
     * @param userId 授权用户
     * @return boolean
     */
    public static boolean isPermitted(String token, Integer userId){
        if(isAbandoned(token)) {
            return false;
        }
        return isAdmin(token) || getTokenId(token) == userId;
    }

    /**
     * 用于获取登录用户的类型
     * 不验证token,直接获取token中的userType
     * @param token token
     * @return po.UserType.value
     */
    public static int getTokenAuth(String token){
        if(StringUtils.isEmpty(token)) {
            return 0;
        }
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaims().get("auth").asInt();
    }
}
