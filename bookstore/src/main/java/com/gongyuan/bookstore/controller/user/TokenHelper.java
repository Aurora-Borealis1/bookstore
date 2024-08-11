package com.gongyuan.bookstore.controller.user;

import com.gongyuan.bookstore.util.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author: gongyuan
 * @date: 2024/8/10 21:01
 */
@Component
public class TokenHelper {

    /**
     * secret
     */
    private final String secret;

    /**
     * unit:minute
     */
    private final int expireDuration;

    public TokenHelper(@Value("${jwt.secret}") String secret,
                       @Value("${jwt.expire.duration:600}") int expireDuration) {
        this.secret = secret;
        this.expireDuration = expireDuration;
    }

    /**
     * generateToken
     *
     * @param userId
     * @return
     */
    public String generateToken(long userId) {
        checkArgument(userId > 0, "userId cannot be negative");
        return JWTUtil.generateToken(userId, secret, expireDuration);
    }


    public Map<String, Object> decode(String token) {
        checkArgument(StringUtils.isNotBlank(token), "token cannot be blank");
        return JWTUtil.decodeToken(token, secret);
    }


}
