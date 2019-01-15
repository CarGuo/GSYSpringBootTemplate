package com.shuyu.spring.template.core.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.shuyu.spring.template.config.GlobalConfig;
import com.shuyu.spring.template.utils.CacheUtil;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * JWT 工具类
 */
public class JWTUtil {
    // 过期时间 24 小时
    private static final int EXPIRE_TIME = 24 * 60 *  60;
    // 密钥
    private static final String SECRET = "SHIRO+JWT";

    /**
     * 生成 token, 24 小时后过期
     *
     * @param username 用户名
     * @return 加密的token
     */
    public static String createToken(String username) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME * 1000);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            String token = JWT.create()
                    .withClaim("username", username)
                    //到期时间
                    .withExpiresAt(date)
                    //创建一个新的JWT，并使用给定的算法进行标记
                    .sign(algorithm);
            CacheUtil.put(GlobalConfig.TOKEN_CACHE, username, token, EXPIRE_TIME, 0);
            return token;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 校验 token 是否正确
     *
     * @param token    密钥
     * @param username 用户名
     * @return 是否正确
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //验证 token
            verifier.verify(token);
            String cacheToke = CacheUtil.get(GlobalConfig.TOKEN_CACHE, username);
            return token.equals(cacheToke);
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 清除token
     */
    public static void disableToken(String token) {
        String user = getUsername(token);
        if (user != null && !user.isEmpty()) {
            CacheUtil.remove(GlobalConfig.TOKEN_CACHE, user);
        }
    }

    /**
     * 获得token中的信息，无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
