package com.gongyuan.bookstore.dao;

import com.gongyuan.bookstore.BookstoreApplicationTests;
import com.gongyuan.bookstore.controller.book.BookStatus;
import com.gongyuan.bookstore.controller.book.LanguageType;
import com.gongyuan.bookstore.model.po.BookPO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author: gongyuan
 * @date: 2024/8/11 10:21
 */
public class BookMapperTest extends BookstoreApplicationTests {

    @Autowired
    private BookMapper bookMapper;


    @Test
    public void testInsert() {
        createBook(1);
        BookPO book = createBook(2);
        bookMapper.insert(book);
        // generated id
        long id = book.getId();
        Assertions.assertTrue(id > 0);
        BookPO bookPO = bookMapper.selectById(id);
        Assertions.assertEquals(book.getVersion(), bookPO.getVersion());

    }


    @Test
    public void testUpdate() {
        BookPO book = createBook(3);
        bookMapper.insert(book);
        book = bookMapper.selectById(book.getId());
        book.setLanguage(LanguageType.CHINESE.name());
        bookMapper.updateById(book);
        book = bookMapper.selectById(book.getId());
        Assertions.assertEquals(book.getLanguage(), LanguageType.CHINESE.name());

    }


    @Test
    public void testDelete() {
        BookPO book = createBook(4);
        bookMapper.insert(book);
        book = bookMapper.selectById(book.getId());
        bookMapper.deleteById(book.getId());
        book = bookMapper.selectById(book.getId());
        Assertions.assertEquals(book.getStatus(), BookStatus.DELETE.name());

    }

    public BookPO createBook(int version) {
        BookPO bookPO = new BookPO();
        bookPO.setGmtCreateTime(LocalDateTime.now());
        bookPO.setGmtUpdateTime(LocalDateTime.now());
        bookPO.setCover("https://example.com/cover.jpg");
        bookPO.setTitle("The Great Gatsby");
        bookPO.setAuthor("F. Scott Fitzgerald");
        bookPO.setTranslator("Jane Smith");
        bookPO.setPublishingHouse("Penguin Random House");
        bookPO.setPublicationDate(LocalDate.of(1925, 4, 10));
        bookPO.setVersion(version);
        bookPO.setPrice(new BigDecimal("19.99"));
        bookPO.setCurrency("USD");
        bookPO.setStock(100);
        bookPO.setTags("{\"fiction\": true, \"classic\": true}");
        bookPO.setBriefIntroduction("A novel by F. Scott Fitzgerald, first published in 1925.");
        bookPO.setCatalogue("Chapter 1\nChapter 2\n...");
        bookPO.setLanguage("English");
        bookPO.setStatus("published");
        bookPO.setExtInfo("{\"specialEdition\": false}");
        return bookPO;
    }
}
