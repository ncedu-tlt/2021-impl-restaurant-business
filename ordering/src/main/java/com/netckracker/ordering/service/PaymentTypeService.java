package com.netckracker.ordering.service;

import com.netckracker.ordering.model.PaymentType;
import com.netckracker.ordering.repository.PaymentTypeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public record PaymentTypeService(PaymentTypeRepository paymentTypeRepository) {

    public Integer createPaymentType(PaymentType paymentType) {
        return paymentTypeRepository.save(paymentType).getId();
    }

    public Iterable<PaymentType> getAllPaymentTypes() {
        return paymentTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public PaymentType getCategoryById(Integer id) {
        return paymentTypeRepository.findById(id).orElseThrow();
    }

    public void updatePaymentTypeById(Integer id, PaymentType paymentType) {
        PaymentType updatedPaymentType = paymentTypeRepository.findById(id).orElseThrow();
        updatedPaymentType.setName(paymentType.getName());
        paymentTypeRepository.save(updatedPaymentType);
    }

    public void deletePaymentTypeById(Integer id) {
        if (!paymentTypeRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        paymentTypeRepository.deleteById(id);
    }
}
