package com.gongyuan.bookstore.service.order;

import com.gongyuan.bookstore.controller.order.OrderEvent;
import com.gongyuan.bookstore.controller.order.OrderRequest;
import com.gongyuan.bookstore.controller.order.OrderStatus;
import com.gongyuan.bookstore.model.bo.BookBO;
import com.gongyuan.bookstore.model.bo.OrderBO;
import com.gongyuan.bookstore.model.bo.UserBO;
import com.gongyuan.bookstore.repository.BookRepository;
import com.gongyuan.bookstore.repository.OrderRepository;
import com.gongyuan.bookstore.util.SessionUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author: gongyuan
 * @date: 2024/8/11 14:22
 */
@Service
@AllArgsConstructor
@Slf4j
class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final TransactionTemplate transactionTemplate;

    private final BookRepository bookRepository;

    @Override
    public OrderBO queryOrderById(long id) {
        return orderRepository.queryOrderById(id);
    }

    @Override
    public long createOrder(OrderRequest request) {
        UserBO user = checkNotNull(SessionUtil.currentUser(), "current user cannot be null");
        BookBO book = checkNotNull(bookRepository.queryBookInfoById(request.getBookId()), "book id is wrong");
        checkArgument(request.getQuantity() <= book.getStock(),
                "stock not enough, request=%s,rest=%s", request.getQuantity(), book.getStock());
        Long result = transactionTemplate.execute(status -> {
            if (bookRepository.updateStock(book, request.getQuantity())) {
                BigDecimal totalPrice = book.getPrice().multiply(new BigDecimal(request.getQuantity()));
                OrderBO order = OrderBO.builder()
                        .bookId(book.getId())
                        .userId(user.getId())
                        .quantity(request.getQuantity())
                        .totalPrice(totalPrice)
                        .currency(book.getCurrency())
                        .status(OrderEvent.CREATE.nextStatus())
                        .address(request.getAddress())
                        .phone(request.getPhone())
                        .extInfo(request.getExtInfo())
                        .build();
                return orderRepository.addOrder(order);
            } else {
                log.error("[OrderServiceImpl#createOrder] updateStock failed");
                return 0L;
            }
        });
        return Optional.ofNullable(result).orElse(0L);
    }

    @Override
    public boolean changeStatus(OrderEvent event, long id) {
        OrderBO order = orderRepository.queryOrderById(id);
        if (Objects.isNull(order) || Objects.isNull(order.getStatus())) {
            return false;
        }
        if (!order.getStatus().supportEvent(event)) {
            return false;
        }
        OrderStatus orderStatus = event.nextStatus();
        return orderRepository.updateStatusById(orderStatus, id);
    }
}
