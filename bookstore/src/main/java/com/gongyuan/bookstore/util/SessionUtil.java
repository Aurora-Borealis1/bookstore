package com.gongyuan.bookstore.util;

import com.gongyuan.bookstore.model.bo.UserBO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * SessionUtil
 *
 * @author: gongyuan
 * @date: 2024/8/10 14:12
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SessionUtil {

    /**
     * hold current user
     */
    private static final ThreadLocal<UserBO> USER_INFO_THREAD_LOCAL = new ThreadLocal<>();


    /**
     * hold expire time
     */
    private static final ThreadLocal<Date> TOKEN_EXPIRE_TIME_THREAD_LOCAL = new ThreadLocal<>();


    /**
     * record current user
     *
     * @param user
     * @param expireTime
     */
    public static void recordCurrentUser(UserBO user, Date expireTime) {
        USER_INFO_THREAD_LOCAL.set(checkNotNull(user));
        TOKEN_EXPIRE_TIME_THREAD_LOCAL.set(checkNotNull(expireTime));
    }


    /**
     * avoid memory leaking
     */
    public static void cleanCache() {
        USER_INFO_THREAD_LOCAL.remove();
        TOKEN_EXPIRE_TIME_THREAD_LOCAL.remove();
    }

    /**
     * currentUser
     *
     * @return
     */
    public static UserBO currentUser() {
        return USER_INFO_THREAD_LOCAL.get();
    }

    /**
     * currentUser
     *
     * @return
     */
    public static Date expireTime() {
        return TOKEN_EXPIRE_TIME_THREAD_LOCAL.get();
    }


}
