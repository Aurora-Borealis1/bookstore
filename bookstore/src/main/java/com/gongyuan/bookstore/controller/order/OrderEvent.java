package com.gongyuan.bookstore.controller.order;

/**
 * @author: gongyuan
 * @date: 2024/8/11 17:18
 */
public enum OrderEvent {

    CREATE {
        @Override
        public OrderStatus nextStatus() {
            return OrderStatus.PENDING_PAYMENT;
        }
    },

    CANCEL {
        @Override
        public OrderStatus nextStatus() {
            return OrderStatus.CANCELLED;
        }
    },

    PAY {
        @Override
        public OrderStatus nextStatus() {
            return OrderStatus.WAITING_FOR_SHIPMENT;
        }
    },

    SHIPMENTS {
        @Override
        public OrderStatus nextStatus() {
            return OrderStatus.SHIPPED;
        }
    },

    SIGN {
        @Override
        public OrderStatus nextStatus() {
            return OrderStatus.SIGNED;
        }
    },

    REFUND {
        @Override
        public OrderStatus nextStatus() {
            return OrderStatus.REFUND_REQUESTED;
        }
    },

    REFUND_APPLY {
        @Override
        public OrderStatus nextStatus() {
            return OrderStatus.REFUND_SUCCESSFUL;
        }
    };


    public abstract OrderStatus nextStatus();
}
