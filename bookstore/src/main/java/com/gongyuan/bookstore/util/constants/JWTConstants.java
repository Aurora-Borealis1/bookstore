package com.gongyuan.bookstore.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author: gongyuan
 * @date: 2024/8/10 12:52
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JWTConstants {

    /**
     * userId key in jwt header
     */
    public static final String USER_ID = "userId";

    /**
     * expireDateTime key in jwt header
     */
    public static final String EXPIRE_DATETIME = "expireDateTime";
}
