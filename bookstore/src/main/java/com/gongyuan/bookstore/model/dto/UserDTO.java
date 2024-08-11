package com.gongyuan.bookstore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * @author: gongyuan
 * @date: 2024/8/10 20:33
 */
@AllArgsConstructor
@Builder
@Getter
@ToString
public class UserDTO implements Serializable {

    /**
     * accountNo
     */
    private final String accountNo;

    /**
     * nickname
     */
    private final String nickname;

    /**
     * icon
     */
    private final String icon;

    /**
     * gender
     */
    private final String gender;

    /**
     * email
     */
    private final String email;

    /**
     * phone
     */
    private final String phone;

    /**
     * birthDay
     */
    private final long birthDay;

    /**
     * address
     */
    private final String address;

    /**
     * extInfo
     */
    private final Map<String, Object> extInfo;

}
