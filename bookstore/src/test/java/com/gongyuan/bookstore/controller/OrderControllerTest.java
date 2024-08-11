package com.gongyuan.bookstore.controller;

import com.gongyuan.bookstore.BookstoreApplicationTests;
import com.gongyuan.bookstore.controller.book.BookManageController;
import com.gongyuan.bookstore.controller.book.BookUpdateRequest;
import com.gongyuan.bookstore.controller.book.CurrencyType;
import com.gongyuan.bookstore.controller.book.LanguageType;
import com.gongyuan.bookstore.controller.common.CommonResult;
import com.gongyuan.bookstore.controller.order.OrderController;
import com.gongyuan.bookstore.controller.order.OrderEvent;
import com.gongyuan.bookstore.controller.order.OrderRequest;
import com.gongyuan.bookstore.controller.order.OrderStatus;
import com.gongyuan.bookstore.controller.user.UserController;
import com.gongyuan.bookstore.controller.user.UserGenderType;
import com.gongyuan.bookstore.controller.user.UserManageRequest;
import com.gongyuan.bookstore.model.bo.UserBO;
import com.gongyuan.bookstore.model.dto.OrderDTO;
import com.gongyuan.bookstore.repository.UserRepository;
import com.gongyuan.bookstore.util.SessionUtil;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: gongyuan
 * @date: 2024/8/11 18:00
 */
public class OrderControllerTest extends BookstoreApplicationTests {

    @Autowired
    private OrderController orderController;

    @Autowired
    private UserController userController;

    @Autowired
    private BookManageController bookManageController;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreate() {
        UserManageRequest request = request("123456789", "securePassword");
        userController.register(request);
        UserBO user = userRepository.queryUserByAccountNo("123456789");
        SessionUtil.recordCurrentUser(user, new Date(System.currentTimeMillis() + 1000000));
        CommonResult<Long> result = bookManageController.addBook(request(100));
        long bookId = result.getData();
        CommonResult<Long> res = orderController.createOrder(request(bookId));
        Assertions.assertNotNull(res);
        Assertions.assertNotNull(res.getData());
        CommonResult<OrderDTO> orderDTOCommonResult = orderController.queryOrderInfo(res.getData());
        Assertions.assertNotNull(orderDTOCommonResult);
        Assertions.assertNotNull(orderDTOCommonResult.getData());
        Assertions.assertEquals(orderDTOCommonResult.getData().getBookId(), bookId);
    }

    @Test
    public void testChangeStatus() {
        UserManageRequest request = request("1234567891", "securePassword");
        userController.register(request);
        UserBO user = userRepository.queryUserByAccountNo("1234567891");
        SessionUtil.recordCurrentUser(user, new Date(System.currentTimeMillis() + 1000000));
        CommonResult<Long> result = bookManageController.addBook(request(100));
        long bookId = result.getData();
        CommonResult<Long> res = orderController.createOrder(request(bookId));
        Assertions.assertNotNull(res);
        Assertions.assertNotNull(res.getData());


        CommonResult<Boolean> booleanCommonResult = orderController.changeStatus(res.getData(), OrderEvent.PAY.name());

        Assertions.assertNotNull(booleanCommonResult);
        Assertions.assertTrue(booleanCommonResult.isSuccess());

        CommonResult<OrderDTO> orderDTOCommonResult = orderController.queryOrderInfo(res.getData());
        Assertions.assertNotNull(orderDTOCommonResult);
        Assertions.assertNotNull(orderDTOCommonResult.getData());
        Assertions.assertEquals(orderDTOCommonResult.getData().getStatus(), OrderStatus.WAITING_FOR_SHIPMENT);

    }


    private OrderRequest request(long bookId) {
        OrderRequest request = new OrderRequest();

        // Set values for the required fields
        request.setBookId(bookId);
        request.setQuantity(3);
        request.setAddress("123 Elm Street, Anytown, USA");
        request.setPhone("+1234567890");

        // Optionally set additional information
        Map<String, Object> extInfo = new HashMap<>();
        extInfo.put("deliveryInstructions", "Leave at the back door.");
        extInfo.put("orderNotes", "Please include a freebie if possible.");
        request.setExtInfo(extInfo);
        return request;
    }

    private UserManageRequest request(String accountNo, String password) {
        UserManageRequest request = new UserManageRequest();
        // Required fields
        request.setAccountNo(accountNo);
        request.setPassword(password);
        request.setNickname("JohnDoe"); // Corrected the message in @NotBlank annotation
        request.setIcon("https://example.com/icon.png"); // Corrected the message in @NotBlank annotation
        // Optional fields
        request.setGender(UserGenderType.MALE.name());
        request.setEmail("john.doe@example.com");
        request.setPhone("123-456-7890");
        request.setBirthDay(864000000L); // Example Unix timestamp for January 1, 2000
        request.setAddress("123 Elm Street, Anytown, USA");
        request.setInterestingTags(Lists.newArrayList("technology", "science", "music"));
        Map<String, Object> extInfo = new HashMap<>();
        extInfo.put("favoriteColor", "blue");
        extInfo.put("language", "English");
        request.setExtInfo(extInfo);
        return request;
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
