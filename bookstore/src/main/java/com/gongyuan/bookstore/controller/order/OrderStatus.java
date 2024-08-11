package com.gongyuan.bookstore.controller.order;

import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author: gongyuan
 * @date: 2024/8/11 10:52
 */
@AllArgsConstructor
public enum OrderStatus {

    /**
     * The user has placed an order but has not yet completed payment
     */
    PENDING_PAYMENT(EnumSet.of(OrderEvent.CANCEL, OrderEvent.PAY)),

    /**
     * The user has paid for the order and is waiting for the merchant to ship
     */
    WAITING_FOR_SHIPMENT(EnumSet.of(OrderEvent.REFUND, OrderEvent.SHIPMENTS)),

    /**
     * The merchant has shipped the item and is awaiting confirmation of receipt from the user
     */
    SHIPPED(EnumSet.of(OrderEvent.SIGN)),

    /**
     * The user has received the item and signed for it
     */
    SIGNED(EnumSet.of(OrderEvent.REFUND)),

    /**
     * The order has been cancelled due to special reasons
     */
    CANCELLED(Collections.emptySet()),
    /**
     * The buyer has requested a refund and is awaiting merchant approval
     */
    REFUND_REQUESTED(EnumSet.of(OrderEvent.REFUND_APPLY)),
    /**
     * The refund request has been processed and the funds have been returned to the buyer
     */
    REFUND_SUCCESSFUL(Collections.emptySet()),

    ;

    private final Set<OrderEvent> supportEvents;


    public boolean supportEvent(OrderEvent event) {
        return supportEvents.contains(event);
    }


}
