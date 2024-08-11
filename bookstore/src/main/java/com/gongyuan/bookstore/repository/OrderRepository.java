package com.gongyuan.bookstore.repository;

import com.gongyuan.bookstore.controller.order.OrderStatus;
import com.gongyuan.bookstore.model.bo.OrderBO;

/**
 * OrderRepository
 *
 * @author: gongyuan
 * @date: 2024/8/10 12:19
 */
public interface OrderRepository {

    /**
     * query user by id
     *
     * @param id
     * @return
     */
    OrderBO queryOrderById(long id);

    /**
     * insertUser
     *
     * @param order
     * @return
     */
    long addOrder(OrderBO order);


    /**
     * updateById
     *
     * @param order
     * @param id
     * @return
     */
    boolean updateById(OrderBO order, long id);

    /**
     * updateById
     *
     * @param status
     * @param id
     * @return
     */
    boolean updateStatusById(OrderStatus status, long id);

}
