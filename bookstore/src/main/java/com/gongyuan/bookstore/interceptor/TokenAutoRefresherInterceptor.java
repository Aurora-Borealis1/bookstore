package com.gongyuan.bookstore.interceptor;

import com.gongyuan.bookstore.controller.user.TokenHelper;
import com.gongyuan.bookstore.model.bo.UserBO;
import com.gongyuan.bookstore.util.SessionUtil;
import com.gongyuan.bookstore.util.constants.HttpHeaderConstants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author: gongyuan
 * @date: 2024/8/10 20:40
 */
@AllArgsConstructor
@Component
public class TokenAutoRefresherInterceptor implements HandlerInterceptor {

    private final long autoRefreshDurationThreshold = TimeUnit.HOURS.toMillis(1);

    private final TokenHelper tokenHelper;

    private final TokenWhiteListConfig tokenWhiteListConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (tokenWhiteListConfig.isWhiteMethodHandler(handler)) {
            return true;
        }
        Date expireTime = SessionUtil.expireTime();
        if (Objects.isNull(expireTime)) {
            return true;
        }
        long duration = expireTime.getTime() - System.currentTimeMillis();
        // auto refresh token if token about to expire
        if (duration < autoRefreshDurationThreshold) {
            UserBO user = SessionUtil.currentUser();
            response.addHeader(HttpHeaderConstants.TOKEN, tokenHelper.generateToken(user.getId()));
        }
        return true;
    }
}
