package com.gongyuan.bookstore.service.user;

import com.gongyuan.bookstore.controller.user.UserLoginRequest;
import com.gongyuan.bookstore.controller.user.UserManageRequest;

/**
 * userService
 *
 * @author: gongyuan
 * @date: 2024/8/8 21:55
 */
public interface UserService {

    /**
     * registerUser
     *
     * @return
     */
    boolean registerUser(UserManageRequest request);


    /**
     * updateUserInfo
     *
     * @return
     */
    boolean updateUserInfo(UserManageRequest request);

    /**
     * login
     *
     * @param request
     * @return json web token
     */
    String login(UserLoginRequest request);
}
