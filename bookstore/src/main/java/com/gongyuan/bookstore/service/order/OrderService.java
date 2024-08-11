package com.gongyuan.bookstore.service.order;

import com.gongyuan.bookstore.controller.order.OrderEvent;
import com.gongyuan.bookstore.controller.order.OrderRequest;
import com.gongyuan.bookstore.model.bo.OrderBO;

/**
 * @author: gongyuan
 * @date: 2024/8/11 14:08
 */
public interface OrderService {

    /**
     * queryOrderById
     *
     * @param id
     * @return
     */
    OrderBO queryOrderById(long id);

    /**
     * createOrder
     *
     * @param request
     * @return
     */
    long createOrder(OrderRequest request);


    /**
     * changeStatus
     *
     * @param event
     * @param id
     * @return
     */
    boolean changeStatus(OrderEvent event, long id);
}
