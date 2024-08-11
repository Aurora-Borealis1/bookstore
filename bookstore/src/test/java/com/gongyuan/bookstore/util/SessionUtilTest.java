package com.gongyuan.bookstore.util;

import com.gongyuan.bookstore.model.bo.UserBO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author: gongyuan
 * @date: 2024/8/8 22:09
 */
public class SessionUtilTest {

    @Test
    public void testSessionUtil() {
        SessionUtil.recordCurrentUser(UserBO.builder().build(), new Date());
        Assertions.assertNotNull(SessionUtil.currentUser());
        Assertions.assertNotNull(SessionUtil.expireTime());
        SessionUtil.cleanCache();
        Assertions.assertNull(SessionUtil.currentUser());
        Assertions.assertNull(SessionUtil.expireTime());
    }
}
