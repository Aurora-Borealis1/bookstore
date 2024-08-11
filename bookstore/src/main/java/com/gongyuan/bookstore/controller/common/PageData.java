package com.gongyuan.bookstore.controller.common;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author: gongyuan
 * @date: 2024/8/11 20:33
 */
@AllArgsConstructor
public class PageData<T> implements Serializable {

    private final long total;


    private final List<T> data;



}
