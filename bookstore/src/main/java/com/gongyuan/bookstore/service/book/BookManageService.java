package com.gongyuan.bookstore.service.book;

import com.gongyuan.bookstore.controller.book.BookAddRequest;
import com.gongyuan.bookstore.controller.book.BookEvent;
import com.gongyuan.bookstore.controller.book.BookUpdateRequest;
import com.gongyuan.bookstore.controller.common.PageData;
import com.gongyuan.bookstore.model.bo.BookBO;

/**
 * @author: gongyuan
 * @date: 2024/8/10 23:09
 */
public interface BookManageService {


    boolean deleteById(long id);

    long addBook(BookAddRequest request);

    BookBO queryBookInfoById(long id);

    boolean updateBasicInfoById(BookUpdateRequest request, long id);

    boolean updateStock(long id, int stockAmount);


    boolean updateStatus(long id, BookEvent bookEvent);

    PageData<BookBO> pageQuery(int pageNumber, int pageSize);

}
