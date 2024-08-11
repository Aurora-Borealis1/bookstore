package com.gongyuan.bookstore.service.user;

import com.gongyuan.bookstore.controller.user.LoginType;
import com.gongyuan.bookstore.model.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Account Password
 *
 * @author: gongyuan
 * @date: 2024/8/10 12:17
 */
@Component
class AccountPasswordValidator implements UserLoginValidator {

    @Override
    public boolean validate(String userAccountNo, String password, UserBO userBO) {
        String passwordDigest = userBO.getPasswordDigest();
        return StringUtils.equals(passwordDigest, userBO.getPasswordDigest());
    }

    @Override
    public LoginType supportType() {
        return LoginType.ACCOUNT_PASSWORD;
    }
}
