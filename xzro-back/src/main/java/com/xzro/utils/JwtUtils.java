package com.xzro.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Calendar;
import java.util.Map;

/**
 * ClassName: JwtUtils
 * Package: com.xzro.utils
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/2 11:17
 * @Version 1.0
 */
public class JwtUtils {
    private static String secret = "@@92hjd8SSA9@93";

    //生成JWT
    public static String generateJwt(Map<String, Object> map) {
        //超时时间半小时
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 30*60); //token超时时间半小时

        //设置payload
        String token = JWT.create()
                .withClaim("payload", map)
                .withExpiresAt(calendar.getTime()) //设置超时时间
                .sign(Algorithm.HMAC256(secret));
        return token;
    }

    //解析Jwt
    public static Map<String, Object> parseJwtToMap(String token) throws SignatureVerificationException, TokenExpiredException, AlgorithmMismatchException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();

        DecodedJWT verify = jwtVerifier.verify(token);
        return verify.getClaim("payload").asMap();
    }
}
