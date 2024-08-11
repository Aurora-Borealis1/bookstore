package com.gongyuan.bookstore.controller.order;

import com.gongyuan.bookstore.controller.common.CommonResult;
import com.gongyuan.bookstore.controller.common.ResultCode;
import com.gongyuan.bookstore.model.bo.OrderBO;
import com.gongyuan.bookstore.model.dto.OrderDTO;
import com.gongyuan.bookstore.service.order.OrderService;
import com.gongyuan.bookstore.util.validation.ValidEnumName;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Objects;

/**
 * @author: gongyuan
 * @date: 2024/8/11 14:01
 */
@AllArgsConstructor
@RestController
@RequestMapping("/order")
@Validated
public class OrderController {

    private final OrderService orderService;

    private final OrderBO.Converter converter;

    @GetMapping("/{id}")
    public CommonResult<OrderDTO> queryOrderInfo(@PathVariable("id") @Positive(message = "wrong id") long id) {
        OrderBO order = orderService.queryOrderById(id);
        if (Objects.isNull(order)) {
            return CommonResult.failed(ResultCode.ORDER_NOT_EXiST);
        }
        return CommonResult.successful(converter.toDTO(order));
    }

    @PostMapping
    public CommonResult<Long> createOrder(@Valid @RequestBody OrderRequest request) {
        long id = orderService.createOrder(request);
        return id > 0 ? CommonResult.successful(id) : CommonResult.failed(ResultCode.BOOK_INSERT_FAILED);
    }


    @PutMapping("/{id}/{event}")
    public CommonResult<Boolean> changeStatus(
            @PathVariable("id") @Positive(message = "wrong id") long id,
            @PathVariable("event") @ValidEnumName(OrderEvent.class) String event) {
        boolean success = orderService.changeStatus(OrderEvent.valueOf(event), id);
        return success ? CommonResult.successful() : CommonResult.failed(ResultCode.ORDER_STATUS_CHANGE_FAILED);
    }
}
