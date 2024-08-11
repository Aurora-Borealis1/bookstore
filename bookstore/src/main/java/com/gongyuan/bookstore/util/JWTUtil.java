package com.gongyuan.bookstore.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gongyuan.bookstore.util.constants.JWTConstants;
import com.google.common.collect.ImmutableMap;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWTUtil
 *
 * @author: gongyuan
 * @date: 2024/8/8 21:36
 */
public class JWTUtil {
    /**
     * header
     */
    private static final Map<String, Object> HEADER = ImmutableMap.<String, Object>builder()
            .put("alg", "HS256")
            .put("typ", "JWT")
            .build();

    /**
     * generateToken
     *
     * @param userId
     * @param secret
     * @param expireDuration
     * @return
     */
    public static String generateToken(long userId, String secret, int expireDuration) {
        LocalDateTime now = LocalDateTime.now();
        Date expireDateTime = LocalDateTimeUtil.toDate(now.plus(expireDuration, ChronoUnit.MINUTES));

        return JWT.create().withHeader(HEADER)
                .withClaim(JWTConstants.USER_ID, userId)
                .withClaim(JWTConstants.EXPIRE_DATETIME, expireDateTime)
                .withIssuedAt(LocalDateTimeUtil.toDate(now))
                .withExpiresAt(expireDateTime)
                .sign(Algorithm.HMAC256(secret));

    }

    /**
     * decodeToken
     *
     * @param token
     * @param secretKey
     * @return
     */
    public static Map<String, Object> decodeToken(String token, String secretKey) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Object> map = new HashMap<>(2);
        map.put(JWTConstants.USER_ID, jwt.getClaim(JWTConstants.USER_ID).asLong());
        map.put(JWTConstants.EXPIRE_DATETIME, jwt.getClaim(JWTConstants.EXPIRE_DATETIME).asDate());
        return map;
    }


}
