package com.netckracker.ordering.controller;

import com.netckracker.ordering.dto.OrderContentDto;
import com.netckracker.ordering.dto.OrderDto;
import com.netckracker.ordering.mapper.OrderContentMapper;
import com.netckracker.ordering.mapper.OrderMapper;
import com.netckracker.ordering.model.Order;
import com.netckracker.ordering.model.OrderContent;
import com.netckracker.ordering.model.OrderContentId;
import com.netckracker.ordering.service.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createOrder(@Valid @RequestBody OrderDto orderDto) {
        return ResponseEntity.created(URI.create("/order/" + orderService.createOrder(
                OrderMapper.INSTANCE.toOrder(orderDto)))).build();
    }

    @PostMapping("content")
    public ResponseEntity<HttpStatus> createOrderContent(@Valid @RequestBody OrderContentDto orderContentDto) {
        OrderContentId orderContentId = orderService.createOrderContent(
                OrderContentMapper.OnInput.INSTANCE.toOrderContent(orderContentDto));
        return ResponseEntity.created(URI.create("/order/" + orderContentId.getOrderId() + "/content/" +
                orderContentId.getDishId())).build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("{id}/content")
    public ResponseEntity<Iterable<OrderContent>> getOrderContentByOrderId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(orderService.getOrderContentByOrderId(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateOrderById(@PathVariable("id") Integer id,
                                                     @Valid @RequestBody OrderDto orderDto) {
        orderService.updateOrderById(id, OrderMapper.INSTANCE.toOrder(orderDto));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{order_id}/content/{dish_id}")
    public ResponseEntity<HttpStatus> updateOrderContentByKey(@PathVariable("order_id") Integer orderId,
                                                              @PathVariable("dish_id") Integer dishId,
                                                              @RequestBody @Valid OrderContentDto orderContentDto) {
        orderService.updateOrderContentByKey(new OrderContentId(orderId, dishId),
                OrderContentMapper.OnInput.INSTANCE.toOrderContent(orderContentDto));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> updateOrderById(@PathVariable("id") Integer id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{order_id}/content/{dish_id}")
    public ResponseEntity<HttpStatus> deleteOrderContentByKey(@PathVariable("order_id") Integer orderId,
                                                              @PathVariable("dish_id") Integer dishId) {
        orderService.deleteOrderContentByKey(new OrderContentId(orderId, dishId));
        return ResponseEntity.noContent().build();
    }
}
