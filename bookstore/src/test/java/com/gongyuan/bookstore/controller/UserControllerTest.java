package com.gongyuan.bookstore.controller;

import com.gongyuan.bookstore.BookstoreApplicationTests;
import com.gongyuan.bookstore.controller.common.CommonResult;
import com.gongyuan.bookstore.controller.user.LoginType;
import com.gongyuan.bookstore.controller.user.UserController;
import com.gongyuan.bookstore.controller.user.UserGenderType;
import com.gongyuan.bookstore.controller.user.UserLoginRequest;
import com.gongyuan.bookstore.controller.user.UserManageRequest;
import com.gongyuan.bookstore.model.bo.UserBO;
import com.gongyuan.bookstore.model.dto.UserDTO;
import com.gongyuan.bookstore.repository.UserRepository;
import com.gongyuan.bookstore.util.SessionUtil;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: gongyuan
 * @date: 2024/8/11 18:00
 */
public class UserControllerTest extends BookstoreApplicationTests {

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testRegister() throws Exception {
        UserManageRequest request = request("123456789", "securePassword");
        CommonResult<Boolean> registerResult = userController.register(request);
        Assertions.assertNotNull(registerResult);
        Assertions.assertTrue(registerResult.isSuccess());


    }

    @Test
    public void testRegisterError() {
        boolean res = true;
        try {
            userController.register(new UserManageRequest());
        } catch (Exception e) {
            res = false;
        }
        Assertions.assertFalse(res);
    }


    @Test
    public void testLogin() {
        String accountNo = "hello";
        String password = "123456";
        userController.register(request(accountNo, password));
        UserLoginRequest request = new UserLoginRequest();
        request.setUserAccountNo(accountNo);
        request.setPassword("securePassword");
        request.setLoginType(LoginType.ACCOUNT_PASSWORD.name());
        CommonResult<String> result = userController.login(request);
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isSuccess());
    }

    @Test
    public void testLoginError() {
        boolean res = true;
        try {
            userController.login(new UserLoginRequest());
        } catch (Exception e) {
            res = false;
        }
        Assertions.assertFalse(res);
    }



    @Test
    public void testQuery() throws Exception {
        String accountNo = "hhh";
        String password = "123456";
        UserManageRequest request = request(accountNo, password);
        userController.register(request);
        UserBO user = userRepository.queryUserByAccountNo(accountNo);
        SessionUtil.recordCurrentUser(user, new Date(System.currentTimeMillis() +1000000000));
        CommonResult<UserDTO> result = userController.queryUserInfo();
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isSuccess());
    }





    private UserManageRequest request(String accountNo, String password) {
        UserManageRequest request = new UserManageRequest();
        // Required fields
        request.setAccountNo(accountNo);
        request.setPassword(password);
        request.setNickname("JohnDoe"); // Corrected the message in @NotBlank annotation
        request.setIcon("https://example.com/icon.png"); // Corrected the message in @NotBlank annotation
        // Optional fields
        request.setGender(UserGenderType.MALE.name());
        request.setEmail("john.doe@example.com");
        request.setPhone("123-456-7890");
        request.setBirthDay(864000000L); // Example Unix timestamp for January 1, 2000
        request.setAddress("123 Elm Street, Anytown, USA");
        request.setInterestingTags(Lists.newArrayList("technology", "science", "music"));
        Map<String, Object> extInfo = new HashMap<>();
        extInfo.put("favoriteColor", "blue");
        extInfo.put("language", "English");
        request.setExtInfo(extInfo);
        return request;
    }
}
