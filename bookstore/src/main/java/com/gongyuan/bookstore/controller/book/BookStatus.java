package com.gongyuan.bookstore.controller.book;

import lombok.AllArgsConstructor;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author: gongyuan
 * @date: 2024/8/10 22:26
 */
@AllArgsConstructor
public enum BookStatus {

    ONLINE(EnumSet.of(BookEvent.TAKE_DOWN)),

    OFFLINE(EnumSet.of(BookEvent.PUT_UP, BookEvent.DELETE)),

    DELETE(EnumSet.of(BookEvent.RECOVER)),

    ;

    private final Set<BookEvent> supportEvents;


    public boolean support(BookEvent event) {
        return supportEvents.contains(event);
    }

}
