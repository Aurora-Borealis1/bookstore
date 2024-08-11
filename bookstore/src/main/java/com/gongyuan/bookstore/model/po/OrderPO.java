package com.gongyuan.bookstore.model.po;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: gongyuan
 * @date: 2024/8/11 10:47
 */
@Data
public class OrderPO {

    /**
     * Order ID.
     */
    private long id;

    /**
     * Timestamp when the order was created.
     */
    private LocalDateTime gmtCreateTime;

    /**
     * Timestamp when the order was updated.
     */
    private LocalDateTime gmtUpdateTime;

    /**
     * The BookPO object associated with this order.
     */
    private Long bookId;

    /**
     * The userId associated with this order.
     */
    private Long userId;

    /**
     * Number of books ordered.
     */
    private Integer quantity;

    /**
     * Total price for the order.
     */
    private BigDecimal totalPrice;

    /**
     * Currency used for the transaction.
     */
    private String currency;

    /**
     * Status of the order (e.g., 'pending', 'shipped', 'delivered').
     */
    private String status;

    /**
     * Timestamp when the order was paid.
     */
    private LocalDateTime payTime;

    /**
     * Timestamp when the order was shipped.
     */
    private LocalDateTime shipmentTime;

    /**
     * Timestamp when the order was completed.
     */
    private LocalDateTime completeTime;

    /**
     * Timestamp when the order was paid.
     */
    private LocalDateTime cancelTime;

    /**
     * order address
     */
    private String address;

    /**
     * phone
     */
    private String phone;

    /**
     * Optional extra information about the order.
     */
    private String extInfo;


}
