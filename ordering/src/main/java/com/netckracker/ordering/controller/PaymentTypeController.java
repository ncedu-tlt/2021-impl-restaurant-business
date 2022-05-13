package com.netckracker.ordering.controller;

import com.netckracker.ordering.model.PaymentType;
import com.netckracker.ordering.service.PaymentTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("type")
public class PaymentTypeController {
    private final PaymentTypeService paymentTypeService;

    public PaymentTypeController(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createCategory(@Valid @RequestBody PaymentType paymentType) {
        return ResponseEntity.created(URI.create("/type/" +
                paymentTypeService.createPaymentType(paymentType))).build();
    }

    @GetMapping
    public ResponseEntity<Iterable<PaymentType>> getAllCategories() {
        return ResponseEntity.ok(paymentTypeService.getAllPaymentTypes());
    }

    @GetMapping("{id}")
    public ResponseEntity<PaymentType> getAllCategories(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(paymentTypeService.getCategoryById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateCategoryById(@PathVariable("id") Integer id,
                                                         @Valid @RequestBody PaymentType paymentType) {
        paymentTypeService.updatePaymentTypeById(id, paymentType);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable("id") Integer id) {
        paymentTypeService.deletePaymentTypeById(id);
        return ResponseEntity.noContent().build();
    }
}
