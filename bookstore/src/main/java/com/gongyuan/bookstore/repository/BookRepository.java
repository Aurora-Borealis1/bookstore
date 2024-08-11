package com.gongyuan.bookstore.repository;

import com.github.pagehelper.PageInfo;
import com.gongyuan.bookstore.controller.book.BookStatus;
import com.gongyuan.bookstore.model.bo.BookBO;

/**
 * @author: gongyuan
 * @date: 2024/8/10 23:00
 */
public interface BookRepository {

    long insertBook(BookBO book);

    BookBO queryBookInfoById(long id);

    boolean updateBasicInfoById(BookBO book, long id);

    boolean deleteById(long id);

    boolean updateStock(BookBO book, int stockAmount);

    boolean updateStatus(BookBO book, BookStatus bookStatus);

    PageInfo<BookBO> pageSelect(int pageNum, int pageSize);

}
