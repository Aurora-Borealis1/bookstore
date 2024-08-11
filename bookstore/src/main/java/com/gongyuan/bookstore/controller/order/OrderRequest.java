package com.gongyuan.bookstore.controller.order;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Map;

/**
 * @author: gongyuan
 * @date: 2024/8/11 14:11
 */
@Data
public class OrderRequest implements Serializable {

    /**
     * The BookPO object associated with this order.
     */
    @NotNull(message = "bookId cannot be null")
    @Positive(message = "bookId must be positive")
    private Long bookId;

    /**
     * Number of books ordered.
     */
    @NotNull(message = "quantity cannot be null")
    @Positive(message = "quantity must be positive")
    private Integer quantity;

    /**
     * order address
     */
    @NotBlank(message = "phone cannot be blank")
    private String address;

    /**
     * phone
     */
    @NotBlank(message = "phone cannot be blank")
    private String phone;

    /**
     * Optional extra information about the order.
     */
    private Map<String, Object> extInfo;
}
