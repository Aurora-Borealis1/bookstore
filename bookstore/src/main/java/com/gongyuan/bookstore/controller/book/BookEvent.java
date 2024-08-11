package com.gongyuan.bookstore.controller.book;

/**
 * @author: gongyuan
 * @date: 2024/8/11 15:16
 */

public enum BookEvent {
    TAKE_DOWN {
        @Override
        public BookStatus nextStatus() {
            return BookStatus.OFFLINE;
        }
    },
    PUT_UP {
        @Override
        public BookStatus nextStatus() {
            return BookStatus.ONLINE;
        }
    },
    RECOVER {
        @Override
        public BookStatus nextStatus() {
            return BookStatus.OFFLINE;
        }
    },
    DELETE{
        @Override
        public BookStatus nextStatus() {
            return BookStatus.DELETE;
        }
    },
    ;
    public abstract BookStatus nextStatus();

}
