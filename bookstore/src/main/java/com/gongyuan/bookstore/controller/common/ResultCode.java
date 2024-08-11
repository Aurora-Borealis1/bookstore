package com.gongyuan.bookstore.controller.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ResultCode used in CommonResult
 *
 * @author: gongyuan
 * @date: 2024/8/10 11:09
 * @see CommonResult
 */
@AllArgsConstructor
@Getter
public enum ResultCode {

    /**
     * SUCCESS
     */
    SUCCESS(0, "success"),

    /**
     * LOGIN_FAILED
     */
    LOGIN_FAILED(1, "login falied, please check accountNo and password"),

    /**
     * NO_TOKEN
     */
    NO_TOKEN(2, "no token"),

    /**
     * DECODE_TOKEN_ERROR
     */
    DECODE_TOKEN_ERROR(3, "decode token error"),

    /**
     * DECODE_TOKEN_ERROR
     */
    TOKEN_INFO_MISSED_ERROR(4, "token info missed error"),

    /**
     * DECODE_TOKEN_ERROR
     */
    TOKEN_HAS_EXPIRED(4, "token has expired, please login again"),

    /**
     * WRONG_USER_ID
     */
    WRONG_USER_ID(5, "wrong user id"),

    /**
     * REGISTER_FAILED
     */
    USER_REGISTER_FAILED(6, "user register failed"),

    /**
     * USER_UPDATE_FAILED
     */
    USER_UPDATE_FAILED(7, "user update failed"),

    /**
     * BOOK_NOT_EXIST
     */
    BOOK_NOT_EXIST(50, "book not exist"),

    /**
     * BOOK_DELETE_FAILED
     */
    BOOK_DELETE_FAILED(51, "delete book failed"),

    /**
     * BOOK_INSERT_FAILED
     */
    BOOK_INSERT_FAILED(52, "insert book failed"),


    /**
     * BOOK_INSERT_FAILED
     */
    BOOK_UPDATE_FAILED(53, "update book failed"),

    /**
     * ORDER_NOT_EXiST
     */
    ORDER_NOT_EXiST(900, "order not exist"),


    /**
     * ORDER_STATUS_CHANGE_FAILED
     */
    ORDER_STATUS_CHANGE_FAILED(901, "order status change failed"),

    /**
     * ILLEGAL_ARGUMENT
     */
    ILLEGAL_ARGUMENT(1000, "illegal argument"),


    /**
     * UNKNOWN_ERROR
     */
    UNKNOWN_ERROR(9999, "unknown error"),

    ;

    /**
     * code
     */
    private final int code;


    /**
     * description
     */
    private final String desc;


}
