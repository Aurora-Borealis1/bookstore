package com.gongyuan.bookstore.model.bo;

import com.gongyuan.bookstore.controller.book.BookAddRequest;
import com.gongyuan.bookstore.controller.book.CurrencyType;
import com.gongyuan.bookstore.controller.order.OrderStatus;
import com.gongyuan.bookstore.model.dto.OrderDTO;
import com.gongyuan.bookstore.model.po.OrderPO;
import com.gongyuan.bookstore.util.mapstruct.CommonMapStructConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author: gongyuan
 * @date: 2024/8/11 13:29
 */
@AllArgsConstructor
@Builder
@Getter
public class OrderBO implements Serializable {

    /**
     * Order ID.
     */
    private final long id;

    /**
     * Timestamp when the order was created.
     */
    private final LocalDateTime gmtCreateTime;

    /**
     * Timestamp when the order was updated.
     */
    private final LocalDateTime gmtUpdateTime;

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
    private final CurrencyType currency;

    /**
     * Status of the order (e.g., 'pending', 'shipped', 'delivered').
     */
    private final OrderStatus status;

    /**
     * Timestamp when the order was paid.
     */
    private final LocalDateTime payTime;

    /**
     * Timestamp when the order was shipped.
     */
    private final LocalDateTime shipmentTime;

    /**
     * Timestamp when the order was completed.
     */
    private final LocalDateTime completeTime;

    /**
     * Timestamp when the order was paid.
     */
    private final LocalDateTime cancelTime;

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


    @Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CommonMapStructConverter.class)
    public interface Converter {

        /**
         * po to bo
         *
         * @param po
         * @return bo
         */
        OrderBO fromPO(OrderPO po);

        /**
         * requet to bo
         *
         * @param request
         * @return
         */
        OrderBO fromRequest(BookAddRequest request);

        /**
         * bo to po
         *
         * @param bo
         * @return po
         */
        OrderPO toPO(OrderBO bo);

        /**
         * bo to dto
         *
         * @param bo
         * @return dto
         */
        OrderDTO toDTO(OrderBO bo);


    }
}
