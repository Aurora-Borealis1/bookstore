package com.gongyuan.bookstore.service.book;

import com.github.pagehelper.PageInfo;
import com.gongyuan.bookstore.controller.book.BookAddRequest;
import com.gongyuan.bookstore.controller.book.BookEvent;
import com.gongyuan.bookstore.controller.book.BookStatus;
import com.gongyuan.bookstore.controller.book.BookUpdateRequest;
import com.gongyuan.bookstore.controller.common.PageData;
import com.gongyuan.bookstore.model.bo.BookBO;
import com.gongyuan.bookstore.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: gongyuan
 * @date: 2024/8/10 23:15
 */
@AllArgsConstructor
@Service
class BookManageServiceImpl implements BookManageService {

    private final BookRepository bookRepository;

    private final BookBO.Converter converter;


    @Override
    public boolean deleteById(long id) {
        BookBO book = bookRepository.queryBookInfoById(id);
        if (Objects.isNull(book)) {
            return false;
        }
        if (book.getStatus() != BookStatus.OFFLINE) {
            return false;
        }
        return bookRepository.deleteById(id);
    }

    @Override
    public long addBook(BookAddRequest request) {
        BookBO book = converter.fromRequest(request);
        return bookRepository.insertBook(book);
    }

    @Override
    public BookBO queryBookInfoById(long id) {
        BookBO book = bookRepository.queryBookInfoById(id);
        if (Objects.isNull(book) || book.getStatus() == BookStatus.DELETE) {
            return null;
        }
        return book;
    }

    @Override
    public boolean updateBasicInfoById(BookUpdateRequest request, long id) {
        if (Objects.isNull(queryBookInfoById(id))) {
            return false;
        }
        BookBO updateBaseInfoBO = converter.fromRequest(request);
        return bookRepository.updateBasicInfoById(updateBaseInfoBO, id);
    }

    @Override
    public boolean updateStock(long id, int stockAmount) {
        BookBO book = queryBookInfoById(id);
        if (Objects.isNull(book)) {
            return false;
        }
        return bookRepository.updateStock(book, stockAmount);
    }

    @Override
    public boolean updateStatus(long id, BookEvent bookEvent) {
        BookBO book = queryBookInfoById(id);
        if (Objects.isNull(book)) {
            return false;
        }
        if (!book.getStatus().support(bookEvent)) {
            return false;
        }
        return bookRepository.updateStatus(book, bookEvent.nextStatus());
    }

    @Override
    public PageData<BookBO> pageQuery(int pageNumber, int pageSize) {
        PageInfo<BookBO> pageInfo = bookRepository.pageSelect(pageNumber, pageSize);
        return new PageData<>(pageInfo.getTotal(), pageInfo.getList());
    }


}
