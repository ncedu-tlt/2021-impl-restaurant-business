package com.netckracker.warehouseandsuppliers.controller;

import com.netckracker.warehouseandsuppliers.dto.OrderContentDto;
import com.netckracker.warehouseandsuppliers.dto.OrderDto;
import com.netckracker.warehouseandsuppliers.mappers.OrderContentMapper;
import com.netckracker.warehouseandsuppliers.mappers.OrderMapper;
import com.netckracker.warehouseandsuppliers.model.Order;
import com.netckracker.warehouseandsuppliers.model.OrderContentId;
import com.netckracker.warehouseandsuppliers.service.OrderService;
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
        return ResponseEntity.created(URI.create("/order/" +
                orderService.createOrder(OrderMapper.INSTANCE.toOrder(orderDto)))).build();
    }

    @PostMapping("/content")
    public ResponseEntity<HttpStatus> createOrderContent(@Valid @RequestBody OrderContentDto orderContentDto) {
        OrderContentId id = orderService.createOrderContent(OrderContentMapper.INSTANCE.toOrderContent(orderContentDto));
        return ResponseEntity.created(URI.create("/order/" + id.getOrderId() + "/content/" +
                id.getProductId())).build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateOrderById(@PathVariable("id") Integer id,
                                                        @Valid @RequestBody OrderDto orderDto) {
        orderService.updateOrderById(id, OrderMapper.INSTANCE.toOrder(orderDto));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteOrderById(@PathVariable("id") Integer id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{order_id}/content/{product_id}")
    public ResponseEntity<HttpStatus> deleteWarehouseContentById(@PathVariable("order_id") Integer orderId,
                                                                 @PathVariable("product_id") Integer productId) {
        orderService.deleteOrderContentById(new OrderContentId(orderId, productId));
        return ResponseEntity.noContent().build();
    }
}
