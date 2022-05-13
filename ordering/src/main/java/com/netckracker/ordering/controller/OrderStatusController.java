package com.netckracker.ordering.controller;

import com.netckracker.ordering.model.OrderStatus;
import com.netckracker.ordering.service.OrderStatusService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("status")
public class OrderStatusController {
    private final OrderStatusService orderStatusService;

    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createCategory(@Valid @RequestBody OrderStatus orderStatus) {
        return ResponseEntity.created(URI.create("/status/" + orderStatusService.createOrderStatus(orderStatus))).build();
    }

    @GetMapping
    public ResponseEntity<Iterable<OrderStatus>> getAllCategories() {
        return ResponseEntity.ok(orderStatusService.getAllOrderStatuses());
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderStatus> getAllCategories(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(orderStatusService.getOrderStatusById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateCategoryById(@PathVariable("id") Integer id,
                                                         @Valid @RequestBody OrderStatus orderStatus) {
        orderStatusService.updateOrderStatusById(id, orderStatus);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable("id") Integer id) {
        orderStatusService.deleteOrderStatusById(id);
        return ResponseEntity.noContent().build();
    }
}
