package com.gongyuan.bookstore.service.user;

import com.gongyuan.bookstore.controller.user.LoginType;
import com.gongyuan.bookstore.model.bo.UserBO;

/**
 * UserLoginValidator
 *
 * @author: gongyuan
 * @date: 2024/8/10 12:16
 */
interface UserLoginValidator {

    /**
     * validate user login info
     *
     * @param userAccountNo
     * @param password
     * @return
     */
    boolean validate(String userAccountNo, String password, UserBO userBO);


    /**
     * supportType
     *
     * @return
     */
    LoginType supportType();

}
