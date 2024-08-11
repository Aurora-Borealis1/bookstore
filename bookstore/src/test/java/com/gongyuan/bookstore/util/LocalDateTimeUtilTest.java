package com.gongyuan.bookstore.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author: gongyuan
 * @date: 2024/8/8 22:09
 */
public class LocalDateTimeUtilTest {

    @Test
    public void test() {
        long time = System.currentTimeMillis();
        LocalDateTime localDateTime = LocalDateTimeUtil.longToLocalDateTime(time);
        long l = LocalDateTimeUtil.localDateTimeToLong(localDateTime);
        Assertions.assertEquals(time, l);
        LocalDate localDate = LocalDateTimeUtil.longToLocalDate(time);
        l = LocalDateTimeUtil.localDateToLong(localDate);
        Assertions.assertTrue(time > l);
    }
}
