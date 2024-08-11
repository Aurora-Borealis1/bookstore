package com.gongyuan.bookstore.controller.book;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: gongyuan
 * @date: 2024/8/10 22:31
 */
@AllArgsConstructor
@Getter
public enum CurrencyType {
    RMB("¥"),
    USD("$"),
    ;
    private final String code;
}
