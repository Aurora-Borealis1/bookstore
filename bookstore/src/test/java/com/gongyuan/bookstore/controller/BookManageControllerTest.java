package com.gongyuan.bookstore.controller;

import com.gongyuan.bookstore.BookstoreApplicationTests;
import com.gongyuan.bookstore.controller.book.BookEvent;
import com.gongyuan.bookstore.controller.book.BookManageController;
import com.gongyuan.bookstore.controller.book.BookStatus;
import com.gongyuan.bookstore.controller.book.BookUpdateRequest;
import com.gongyuan.bookstore.controller.book.CurrencyType;
import com.gongyuan.bookstore.controller.book.LanguageType;
import com.gongyuan.bookstore.controller.common.CommonResult;
import com.gongyuan.bookstore.model.dto.BookDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @author: gongyuan
 * @date: 2024/8/11 18:00
 */
public class BookManageControllerTest extends BookstoreApplicationTests {

    @Autowired
    private BookManageController bookManageController;


    @Test
    public void testAdd() {
        CommonResult<Long> result = bookManageController.addBook(request(1));
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isSuccess());
        CommonResult<BookDTO> bookDTOCommonResult = bookManageController.queryBookInfo(result.getData());
        Assertions.assertNotNull(bookDTOCommonResult);
        Assertions.assertTrue(bookDTOCommonResult.isSuccess());
        Assertions.assertNotNull(bookDTOCommonResult.getData());
    }

    @Test
    public void testUpdateBook() {
        BookUpdateRequest request = request(2);
        CommonResult<Long> result = bookManageController.addBook(request);
        request.setVersion(3);
        CommonResult<Boolean> booleanCommonResult = bookManageController.updateBook(request, result.getData());
        Assertions.assertNotNull(booleanCommonResult);
        Assertions.assertTrue(booleanCommonResult.isSuccess());
        CommonResult<BookDTO> bookDTOCommonResult = bookManageController.queryBookInfo(result.getData());
        Assertions.assertNotNull(bookDTOCommonResult);
        Assertions.assertTrue(bookDTOCommonResult.isSuccess());
        Assertions.assertNotNull(bookDTOCommonResult.getData());
        Assertions.assertEquals(bookDTOCommonResult.getData().getVersion(), 3);
    }

    @Test
    public void testUpdateStatus() {
        BookUpdateRequest request = request(4);
        CommonResult<Long> result = bookManageController.addBook(request);
        CommonResult<Boolean> booleanCommonResult = bookManageController.updateStatus(result.getData(), BookEvent.PUT_UP.name());
        Assertions.assertNotNull(booleanCommonResult);
        Assertions.assertTrue(booleanCommonResult.isSuccess());
        CommonResult<BookDTO> bookDTOCommonResult = bookManageController.queryBookInfo(result.getData());
        Assertions.assertNotNull(bookDTOCommonResult);
        Assertions.assertTrue(bookDTOCommonResult.isSuccess());
        Assertions.assertNotNull(bookDTOCommonResult.getData());
        Assertions.assertEquals(bookDTOCommonResult.getData().getStatus(), BookStatus.ONLINE.name());
    }

    @Test
    public void testUpdateStock() {
        BookUpdateRequest request = request(5);
        CommonResult<Long> result = bookManageController.addBook(request);
        CommonResult<Boolean> booleanCommonResult = bookManageController.updateStock(result.getData(), 10);
        Assertions.assertNotNull(booleanCommonResult);
        Assertions.assertTrue(booleanCommonResult.isSuccess());
        CommonResult<BookDTO> bookDTOCommonResult = bookManageController.queryBookInfo(result.getData());
        Assertions.assertNotNull(bookDTOCommonResult);
        Assertions.assertTrue(bookDTOCommonResult.isSuccess());
        Assertions.assertNotNull(bookDTOCommonResult.getData());
        Assertions.assertEquals(bookDTOCommonResult.getData().getStock(), 60);

    }



    public BookUpdateRequest request(int version) {
        BookUpdateRequest request = new BookUpdateRequest();

        // Required fields with @NotBlank
        request.setCover("https://example.com/book_cover.jpg");
        request.setTitle("The Great Gatsby");
        request.setAuthor("F. Scott Fitzgerald");
        request.setPublishingHouse("Scribner");
        request.setBriefIntroduction("A novel about the decadence and excess of the Roaring Twenties.");
        request.setCatalogue("Chapter 1: The Introduction\nChapter 2: The Party\n...");

        // Fields with @NotNull and @Positive
        request.setPublicationDate(1689984000L); // Example Unix timestamp for August 11, 2024
        request.setVersion(version);
        request.setPrice(BigDecimal.valueOf(19.99));
        request.setStock(50);

        // Fields with @ValidEnumName
        request.setCurrency(CurrencyType.USD.name());
        request.setLanguage(LanguageType.ENGLISH.name());

        // Optional fields
        request.setTranslator("Jane Smith");
        request.setTags("{\"tags\": [\"fiction\", \"classic\"]}");
        return request;
    }
}
