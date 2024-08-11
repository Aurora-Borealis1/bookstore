package com.gongyuan.bookstore.controller.user;

import com.gongyuan.bookstore.util.validation.ValidEnumName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author: gongyuan
 * @date: 2024/8/8 22:26
 */
@Data
public class UserLoginRequest implements Serializable {

    /**
     * userAccountNo
     */
    @NotBlank(message = "userAccountNo is blank")
    private String userAccountNo;

    /**
     * password
     */
    @NotBlank(message = "password is blank")
    private String password;

    /**
     * loginType
     */
    @ValidEnumName(value = LoginType.class, message = "loginType is wrong")
    private String loginType;

}
