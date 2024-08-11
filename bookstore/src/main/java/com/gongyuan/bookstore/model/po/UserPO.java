package com.gongyuan.bookstore.model.po;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author: gongyuan
 * @date: 2024/8/10 12:32
 */
@Data
public class UserPO {
    /**
     * primary key
     */
    private long id;

    /**
     * create time
     */
    private LocalDateTime gmtCreateTime;

    /**
     * update time
     */
    private LocalDateTime gmtUpdateTime;

    /**
     * accountNo
     */
    private String accountNo;

    /**
     * passwordDigest
     */
    private String passwordDigest;

    /**
     * nickname
     */
    private String nickname;

    /**
     * icon
     */
    private String icon;

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
    private LocalDate birthDay;

    /**
     * address
     */
    private String address;

    /**
     * interestingTags
     */
    private String interestingTags;

    /**
     * extInfo
     */
    private String extInfo;

}
