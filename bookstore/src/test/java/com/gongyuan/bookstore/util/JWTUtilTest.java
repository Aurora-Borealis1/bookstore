package com.gongyuan.bookstore.util;

import com.gongyuan.bookstore.util.constants.JWTConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;

/**
 * @author: gongyuan
 * @date: 2024/8/8 22:09
 */
public class JWTUtilTest {

    @Test
    public void testJwtUtil() {
        String secret = "123";
        long userId = 11111;
        String s = JWTUtil.generateToken(userId, secret, 10);
        Map<String, Object> map = JWTUtil.decodeToken(s, secret);
        Assertions.assertEquals(userId, (long) map.get(JWTConstants.USER_ID));
        Date date = (Date) map.get(JWTConstants.EXPIRE_DATETIME);
        Assertions.assertTrue(date.after(new Date()));
    }
}
