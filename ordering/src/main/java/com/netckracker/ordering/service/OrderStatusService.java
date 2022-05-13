package com.netckracker.ordering.service;

import com.netckracker.ordering.model.OrderStatus;
import com.netckracker.ordering.repository.OrderStatusRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public record OrderStatusService(OrderStatusRepository orderStatusRepository) {

    public Integer createOrderStatus(OrderStatus orderStatus) {
        return orderStatusRepository.save(orderStatus).getId();
    }

    public Iterable<OrderStatus> getAllOrderStatuses() {
        return orderStatusRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public OrderStatus getOrderStatusById(Integer id) {
        return orderStatusRepository.findById(id).orElseThrow();
    }

    public void updateOrderStatusById(Integer id, OrderStatus orderStatus) {
        OrderStatus updatedOrderStatus = orderStatusRepository.findById(id).orElseThrow();
        updatedOrderStatus.setName(orderStatus.getName());
        orderStatusRepository.save(updatedOrderStatus);
    }

    public void deleteOrderStatusById(Integer id) {
        if (!orderStatusRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        orderStatusRepository.deleteById(id);
    }
}
