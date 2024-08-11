package com.gongyuan.bookstore.dao;

import com.gongyuan.bookstore.BookstoreApplicationTests;
import com.gongyuan.bookstore.controller.order.OrderStatus;
import com.gongyuan.bookstore.model.po.OrderPO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @author: gongyuan
 * @date: 2024/8/11 13:14
 */
public class OrderMapperTest extends BookstoreApplicationTests {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testInsert() {
        orderMapper.insert(createOrder(1));
        OrderPO order = createOrder(2);
        orderMapper.insert(order);
        long id = order.getId();
        OrderPO orderPO = orderMapper.selectById(id);
        Assertions.assertEquals(orderPO.getStatus(), OrderStatus.PENDING_PAYMENT.name());
    }


    @Test
    public void testUpdate() {
        OrderPO order = createOrder(3);
        orderMapper.insert(order);
        long id = order.getId();
        OrderPO orderPO = orderMapper.selectById(id);
        orderPO.setStatus(OrderStatus.WAITING_FOR_SHIPMENT.name());
        orderMapper.updateById(orderPO);
        orderPO = orderMapper.selectById(id);
        Assertions.assertEquals(orderPO.getStatus(), OrderStatus.WAITING_FOR_SHIPMENT.name());
    }


    public OrderPO createOrder(long bookId) {
        // Create an instance of OrderPO
        OrderPO order = new OrderPO();
        order.setBookId(bookId);
        order.setQuantity(3);
        order.setTotalPrice(new BigDecimal("59.97")); // Assuming each book costs $19.99 and 3 were bought
        order.setCurrency("USD");
        order.setStatus(OrderStatus.PENDING_PAYMENT.name());
        order.setPayTime(null); // Order is not yet paid
        order.setShipmentTime(null); // Order is not yet shipped
        order.setCompleteTime(null); // Order is not yet completed
        order.setCancelTime(null); // Order has not been canceled
        order.setAddress("123 Main St, Anytown, USA");
        return order;


    }
}
