package com.gongyuan.bookstore.model.dto;

import com.gongyuan.bookstore.controller.order.OrderStatus;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author: gongyuan
 * @date: 2024/8/11 13:29
 */
@Data
public class OrderDTO implements Serializable {

    /**
     * Order ID.
     */
    private final long id;

    /**
     * Timestamp when the order was created.
     */
    private final Long gmtCreateTime;

    /**
     * Timestamp when the order was updated.
     */
    private final Long gmtUpdateTime;

    /**
     * The Book id associated with this order.
     */
    private final Long bookId;

    /**
     * The userId associated with this order.
     */
    private final Long userId;

    /**
     * Number of books ordered.
     */
    private final Integer quantity;

    /**
     * Total price for the order.
     */
    private final BigDecimal totalPrice;

    /**
     * Currency used for the transaction.
     */
    private final String currency;

    /**
     * Status of the order (e.g., 'pending', 'shipped', 'delivered').
     */
    private final OrderStatus status;

    /**
     * Timestamp when the order was paid.
     */
    private final Long payTime;

    /**
     * Timestamp when the order was shipped.
     */
    private final Long shipmentTime;

    /**
     * Timestamp when the order was completed.
     */
    private final Long completeTime;

    /**
     * Timestamp when the order was paid.
     */
    private final Long cancelTime;

    /**
     * order address
     */
    private final String address;

    /**
     * phone
     */
    private final String phone;

    /**
     * Optional extra information about the order.
     */
    private final Map<String, Object> extInfo;
}
