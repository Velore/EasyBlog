package velore.security;

import velore.po.User;

/**
 * token相关的接口
 * 允许实现自定义token
 * @author Velore
 * @date 2022/3/2
 */
public interface TokenService{

    /**
     * 生成token
     * @param user user
     * @return String token
     */
    String generate(User user);

    /**
     * 刷新token的过期时间
     * @param token old token
     * @return new token
     */
    String refresh(String token);
    /**
     * 验证Token
     * 验证成功什么也不做
     * 验证失败抛出异常
     * @param token token
     */
    void verify(String token);

    /**
     * 不验证token,直接获取token中的id
     * 数据库id而非userId
     * 通常用于验证完token后验证用户是否存在
     * @param token token
     * @return uid
     */
    String getTokenId(String token);
}

