package com.gongyuan.bookstore.interceptor;

import com.gongyuan.bookstore.controller.common.ResultCode;
import com.gongyuan.bookstore.controller.user.TokenHelper;
import com.gongyuan.bookstore.model.bo.UserBO;
import com.gongyuan.bookstore.repository.UserRepository;
import com.gongyuan.bookstore.util.SessionUtil;
import com.gongyuan.bookstore.util.constants.HttpHeaderConstants;
import com.gongyuan.bookstore.util.constants.JWTConstants;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.Objects;


/**
 * validate token
 *
 * @author: gongyuan
 * @date: 2024/8/10 12:43
 */
@Component
@AllArgsConstructor
class TokenValidateInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;

    private final TokenHelper tokenHelper;

    private final TokenWhiteListConfig tokenWhiteListConfig;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (tokenWhiteListConfig.isWhiteMethodHandler(handler)) {
            return true;
        }
        String token = request.getHeader(HttpHeaderConstants.TOKEN);
        if (StringUtils.isBlank(token)) {
            throwAuthenticationException(ResultCode.NO_TOKEN);
        }
        Map<String, Object> jwtMap = tokenHelper.decode(token);
        if (MapUtils.isEmpty(jwtMap)) {
            throwAuthenticationException(ResultCode.DECODE_TOKEN_ERROR);
        }
        long userId = (long) jwtMap.get(JWTConstants.USER_ID);
        Date expireDatetime = (Date) jwtMap.get(JWTConstants.EXPIRE_DATETIME);
        if (userId <= 0 || Objects.isNull(expireDatetime)) {
            throwAuthenticationException(ResultCode.TOKEN_INFO_MISSED_ERROR);
        }
        if (new Date().after(expireDatetime)) {
            throwAuthenticationException(ResultCode.TOKEN_HAS_EXPIRED);
        }
        UserBO user = userRepository.queryUserById(userId);
        if (Objects.isNull(user)) {
            throwAuthenticationException(ResultCode.WRONG_USER_ID);
        }
        SessionUtil.recordCurrentUser(user, expireDatetime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        SessionUtil.cleanCache();
    }


    /**
     * throw AuthenticationException and GlobalExceptionHandlerAdvice will catch AuthenticationException
     *
     * @param resultCode
     * @throws AuthenticationException
     * @see GlobalExceptionHandlerAdvice#handleAuthenticationException(AuthenticationException)
     */
    private void throwAuthenticationException(ResultCode resultCode) throws AuthenticationException {
        throw new AuthenticationException(resultCode.name());
    }

}
