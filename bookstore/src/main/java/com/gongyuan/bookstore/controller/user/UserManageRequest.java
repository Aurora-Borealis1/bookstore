package com.gongyuan.bookstore.controller.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author: gongyuan
 * @date: 2024/8/8 22:26
 */
@Data
public class UserManageRequest implements Serializable {

    /**
     * accountNo
     */
    @NotBlank(message = "accountNo cannot be blank")
    private String accountNo;

    /**
     * password
     */
    @NotBlank(message = "password cannot be blank")
    private String password;

    /**
     * nickname
     */
    @NotBlank(message = "password cannot be blank")
    private String nickname;

    /**
     * icon
     */
    @NotBlank(message = "password cannot be blank")
    private String icon;

    // ===================== following fields for personalized recommendation =========================
    /**
     * gender
     */
    private String gender;

    /**
     * email
     */
    private String email;

    /**
     * phone
     */
    private String phone;

    /**
     * birthDay
     */
    private long birthDay;

    /**
     * address
     */
    private String address;

    /**
     * interestingTags
     */
    private List<String> interestingTags;

    /**
     * extInfo
     */
    private Map<String, Object> extInfo;


}
