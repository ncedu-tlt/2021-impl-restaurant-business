package com.netckracker.ordering.service;

import com.netckracker.ordering.error.NoSuchRelatedElementException;
import com.netckracker.ordering.model.*;
import com.netckracker.ordering.repository.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public record OrderService(OrderRepository orderRepository, OrderContentRepository orderContentRepository,
                           DishRepository dishRepository, OrderStatusRepository orderStatusRepository,
                           PaymentTypeRepository paymentTypeRepository) {

    public Integer createOrder(Order order) {
        if (!orderStatusRepository.existsById(order.getOrderStatus().getId())) {
            throw new NoSuchElementException();
        }

        if (!paymentTypeRepository.existsById(order.getPaymentType().getId())) {
            throw new NoSuchElementException();
        }

        return orderRepository.save(order).getId();
    }

    public OrderContentId createOrderContent(OrderContent orderContent) {
        if (!orderRepository.existsById(orderContent.getOrder().getId())) {
            throw new NoSuchRelatedElementException(Order.class.getSimpleName());
        }

        if (!dishRepository.existsById(orderContent.getDish().getId())) {
            throw new NoSuchRelatedElementException(Dish.class.getSimpleName());
        }

        return orderContentRepository.save(orderContent).getId();
    }

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public Iterable<OrderContent> getOrderContentByOrderId(Integer id) {
        // TODO: new DTO for get action
        return orderContentRepository.findAllByOrderId(id);
    }

    public void updateOrderById(Integer id, Order order) {
        if (!orderStatusRepository.existsById(order.getOrderStatus().getId())) {
            throw new NoSuchRelatedElementException(OrderStatus.class.getSimpleName());
        }

        if (!paymentTypeRepository.existsById(order.getPaymentType().getId())) {
            throw new NoSuchRelatedElementException(PaymentType.class.getSimpleName());
        }

        Order updatedOrder = orderRepository.findById(id).orElseThrow();
        updatedOrder.setCost(order.getCost());
        updatedOrder.setPaymentStatus(order.getPaymentStatus());
        updatedOrder.setOrderStatus(order.getOrderStatus());
        updatedOrder.setPaymentType(order.getPaymentType());

        orderRepository.save(updatedOrder);
    }

    public void updateOrderContentByKey(OrderContentId orderContentId, OrderContent orderContent) {
        OrderContent updatedOrderContent = orderContentRepository.findById(orderContentId).orElseThrow();

        // TODO: new DTO for update action
        updatedOrderContent.setQuantity(orderContent.getQuantity());

        orderContentRepository.save(updatedOrderContent);
    }

    public void deleteOrderById(Integer id) {
        if (!orderRepository.existsById(id)) {
            throw new NoSuchElementException();
        }

        orderRepository.deleteById(id);
    }

    public void deleteOrderContentByKey(OrderContentId orderContentId) {
        if (!orderContentRepository.existsById(orderContentId)) {
            throw new NoSuchElementException();
        }

        orderContentRepository.deleteById(orderContentId);
    }
}
