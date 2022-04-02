package velore.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import velore.po.User;

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
     * 用于获取登录用户的类型
     * 不验证token,直接获取token中的userType
     * @param token token
     * @return user type ( 0 , 1 )
     */
    public static int getTokenAuth(String token){
        if(StringUtils.isEmpty(token)) {
            return 0;
        }
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaims().get("auth").asInt();
    }
}
