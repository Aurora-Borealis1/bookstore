package com.gongyuan.bookstore.controller.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * use common result for aop proxy or filter proxy
 *
 * @author: gongyuan
 * @date: 2024/8/10 11:06
 */
@AllArgsConstructor
@Getter
public class CommonResult<T> implements Serializable {

    private final boolean success;

    private final int resultCode;

    private final String resultMessage;

    private final T data;

    /**
     * successful static constructor
     *
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> successful() {
        return new CommonResult<>(true, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc(), null);
    }

    /**
     * successful static constructor
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> successful(T data) {
        return new CommonResult<>(true, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getDesc(), data);
    }


    /**
     * failed static constructor
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(T data, ResultCode resultCode) {
        return new CommonResult<>(false, resultCode.getCode(), resultCode.getDesc(), data);
    }


    /**
     * failed static constructor
     *
     * @param resultCode
     * @param resultMessage
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(ResultCode resultCode, String resultMessage) {
        return new CommonResult<>(false, resultCode.getCode(), resultMessage, null);
    }

    /**
     * failed static constructor
     *
     * @param resultCode
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(ResultCode resultCode) {
        return new CommonResult<>(false, resultCode.getCode(), resultCode.getDesc(), null);
    }
}
