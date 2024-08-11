package com.gongyuan.bookstore.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author: gongyuan
 * @date: 2024/8/8 22:09
 */
public class MD5UtilTest {

    @Test
    public void testGenerateJwt() {
        String s = MD5Util.generateMD5("123");
        Assertions.assertNotNull(s);
    }
}
