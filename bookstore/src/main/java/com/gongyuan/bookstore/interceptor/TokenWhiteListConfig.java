package com.gongyuan.bookstore.interceptor;

import com.gongyuan.bookstore.controller.user.UserController;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Set;

/**
 * @author: gongyuan
 * @date: 2024/8/11 12:16
 */
@Component
public class TokenWhiteListConfig {

    private final Set<Method> whiteMethods;

    public TokenWhiteListConfig(Set<Method> whiteMethods) {
        Set<String> methodNames = Sets.newHashSet("register", "login");
        Set<Method> set = Sets.newHashSet();
        for (Method method : UserController.class.getDeclaredMethods()) {
            if (methodNames.contains(method.getName())) {
                set.add(method);
            }
        }
        this.whiteMethods = Collections.unmodifiableSet(set);

    }


    public boolean isWhiteMethodHandler(Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        return whiteMethods.contains(method);
    }
}
