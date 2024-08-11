package com.gongyuan.bookstore.repository;

import com.gongyuan.bookstore.controller.order.OrderStatus;
import com.gongyuan.bookstore.dao.OrderMapper;
import com.gongyuan.bookstore.model.bo.OrderBO;
import com.gongyuan.bookstore.model.po.OrderPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author: gongyuan
 * @date: 2024/8/11 14:05
 */
@AllArgsConstructor
@Repository
class OrderRepositoryImpl implements OrderRepository {

    private final OrderMapper orderMapper;

    private final OrderBO.Converter converter;

    @Override
    public OrderBO queryOrderById(long id) {
        checkArgument(id > 0, "id must be positive");
        OrderPO order = orderMapper.selectById(id);
        return converter.fromPO(order);
    }

    @Override
    public long addOrder(OrderBO order) {
        checkNotNull(order, "order cannot be null");
        OrderPO orderPO = converter.toPO(order);
        orderMapper.insert(orderPO);
        return orderPO.getId();
    }

    @Override
    public boolean updateById(OrderBO order, long id) {
        checkArgument(id > 0, "id must be positive");
        OrderPO orderPO = converter.toPO(order);
        orderPO.setId(id);
        return orderMapper.updateById(orderPO) == 1;
    }


    @Override
    public boolean updateStatusById(OrderStatus status, long id) {
        checkArgument(id > 0, "id must be positive");
        OrderPO orderPO = new OrderPO();
        orderPO.setId(id);
        orderPO.setStatus(status.name());
        return orderMapper.updateById(orderPO) == 1;
    }
}
