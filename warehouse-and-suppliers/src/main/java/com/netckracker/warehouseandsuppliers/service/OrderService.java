package com.netckracker.warehouseandsuppliers.service;

import com.netckracker.warehouseandsuppliers.error.NoSuchRelatedElementException;
import com.netckracker.warehouseandsuppliers.model.*;
import com.netckracker.warehouseandsuppliers.repository.OrderContentRepository;
import com.netckracker.warehouseandsuppliers.repository.OrderRepository;
import com.netckracker.warehouseandsuppliers.repository.ProductRepository;
import com.netckracker.warehouseandsuppliers.repository.SupplierRepository;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public record OrderService(OrderRepository orderRepository, OrderContentRepository orderContentRepository,
                           SupplierRepository supplierRepository, ProductRepository productRepository) {
    public Integer createOrder(Order order) {
        if (!supplierRepository.existsById(order.getSupplier().getId())) {
            throw new NoSuchRelatedElementException(Supplier.class.getSimpleName());
        }

        return orderRepository.save(order).getId();
    }

    public OrderContentId createOrderContent(OrderContent orderContent) {
        if (!orderRepository.existsById(orderContent.getOrder().getId())) {
            throw new NoSuchRelatedElementException(Order.class.getSimpleName());
        }
        if (!productRepository.existsById(orderContent.getProduct().getId())) {
            throw new NoSuchRelatedElementException(Product.class.getSimpleName());
        }

        return orderContentRepository.save(orderContent).getId();
    }

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public void updateOrderById(Integer id, Order order) {
        Order updatedOrder = orderRepository.findById(id).orElseThrow();

        if (!supplierRepository.existsById(order.getSupplier().getId())) {
            throw new NoSuchRelatedElementException(Supplier.class.getSimpleName());
        }

        updatedOrder.setSupplier(order.getSupplier());
        updatedOrder.setDate(order.getDate());
        updatedOrder.setEvaluation(order.getEvaluation());
        updatedOrder.setComment(order.getComment());

        orderRepository.save(updatedOrder);
    }

    public void deleteOrderById(Integer id) {
        if (!orderRepository.existsById(id)) {
            throw new NoSuchElementException();
        }

        orderRepository.deleteById(id);
    }

    public void deleteOrderContentById(OrderContentId orderContentId) {
        if (!orderRepository.existsById(orderContentId.getOrderId())) {
            throw new NoSuchRelatedElementException(Order.class.getSimpleName());
        }
        if (!productRepository.existsById(orderContentId.getProductId())) {
            throw new NoSuchRelatedElementException(Product.class.getSimpleName());
        }

        orderContentRepository.deleteById(orderContentId);
    }
}
