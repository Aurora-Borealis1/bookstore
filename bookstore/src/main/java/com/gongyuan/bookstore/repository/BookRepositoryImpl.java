package com.gongyuan.bookstore.repository;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gongyuan.bookstore.controller.book.BookStatus;
import com.gongyuan.bookstore.dao.BookMapper;
import com.gongyuan.bookstore.model.bo.BookBO;
import com.gongyuan.bookstore.model.po.BookPO;
import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author: gongyuan
 * @date: 2024/8/10 23:04
 */
@AllArgsConstructor
@Repository
class BookRepositoryImpl implements BookRepository {

    private final BookMapper bookMapper;

    private final BookBO.Converter converter;

    @Override
    public long insertBook(BookBO book) {
        BookPO bookPO = converter.toPO(checkNotNull(book, "book cannot be null"));
        bookMapper.insert(bookPO);
        return bookPO.getId();
    }

    @Override
    @Cacheable(cacheNames="book", key="#id")
    public BookBO queryBookInfoById(long id) {
        checkArgument(id > 0, "id must be positive");
        BookPO bookPO = bookMapper.selectById(id);
        return converter.fromPO(bookPO);
    }

    @Override
    @CacheEvict(cacheNames="book", key="#id")
    public boolean updateBasicInfoById(BookBO book, long id) {
        checkArgument(id > 0, "id must be positive");
        BookPO bookPO = converter.toPO(checkNotNull(book, "book cannot be null"));
        bookPO.setId(id);
        bookPO.setStock(null);
        bookPO.setStatus(null);
        return bookMapper.updateById(bookPO) == 1;
    }

    @Override
    @CacheEvict(cacheNames="book", key="#id")
    public boolean deleteById(long id) {
        checkArgument(id > 0, "id must be positive");
        return bookMapper.deleteById(id) == 1;
    }

    @Override
    @CacheEvict(cacheNames="book", key="#book.id")
    public boolean updateStock(BookBO book, int stockAmount) {
        if (Objects.isNull(book)) {
            return false;
        }
        if (stockAmount == 0) {
            return false;
        }
        int restStock = book.getStock() + stockAmount;
        Preconditions.checkArgument(restStock >= 0, "rest must be positive");
        BookPO bookPO = converter.toPO(checkNotNull(book, "book cannot be null"));
        return bookMapper.updateStock(bookPO, stockAmount) == 1;
    }

    @Override
    @CacheEvict(cacheNames="book", key="#book.id")
    public boolean updateStatus(BookBO book, BookStatus bookStatus) {
        checkNotNull(bookStatus, "bookStatus cannot be null");
        BookPO bookPO = converter.toPO(checkNotNull(book, "book cannot be null"));
        bookPO.setStatus(bookStatus.name());
        return bookMapper.updateById(bookPO) == 1;
    }

    @Override
    public PageInfo<BookBO> pageSelect(int pageNum, int pageSize) {
        try (Page<Object> objects = PageHelper.startPage(pageNum, pageSize)){
            List<BookPO> books = bookMapper.select();
            return new PageInfo<>(converter.fromPO(books));
        }

    }


}
