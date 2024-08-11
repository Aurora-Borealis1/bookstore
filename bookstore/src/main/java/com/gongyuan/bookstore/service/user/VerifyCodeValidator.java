package com.gongyuan.bookstore.service.user;

import com.gongyuan.bookstore.controller.user.LoginType;
import com.gongyuan.bookstore.model.bo.UserBO;
import org.springframework.stereotype.Component;

/**
 * Verify Code
 *
 * @author: gongyuan
 * @date: 2024/8/10 12:17
 */
@Component
public class VerifyCodeValidator implements UserLoginValidator {

    @Override
    public boolean validate(String userAccountNo, String password, UserBO userBO) {
        // TODO: 2024/8/10
        // 1. get verify code
        // 2. compare verify code to passoword
        return true;
    }

    @Override
    public LoginType supportType() {
        return LoginType.VERIFY_CODE;
    }
}
