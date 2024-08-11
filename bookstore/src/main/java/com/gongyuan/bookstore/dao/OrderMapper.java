package com.gongyuan.bookstore.dao;

import com.gongyuan.bookstore.model.po.OrderPO;

/**
 * UserMapper
 *
 * @author: gongyuan
 * @date: 2024/8/10 12:29
 */
public interface OrderMapper {


    /**
     * selectById
     *
     * @param id
     * @return
     */
    OrderPO selectById(long id);

    /**
     * updateById
     *
     * @param orderPO
     * @return
     */
    int updateById(OrderPO orderPO);


    /**
     * insert
     *
     * @param orderPO
     * @return
     */
    int insert(OrderPO orderPO);

}
