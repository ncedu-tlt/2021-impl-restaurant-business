package com.netckracker.warehouseandsuppliers.controller;

import com.netckracker.warehouseandsuppliers.model.Supplier;
import com.netckracker.warehouseandsuppliers.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("supplier")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createSupplier(@Valid @RequestBody Supplier supplier) {
        return ResponseEntity.created(URI.create("supplier/" + supplierService.createSupplier(supplier))).build();
    }
    @GetMapping
    public ResponseEntity<Iterable<Supplier>> getAllSuppliers() {
        return new ResponseEntity<>(supplierService.getAllSuppliers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(supplierService.getSupplierById(id), HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<Iterable<Supplier>> getSupplierByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(supplierService.getAllSuppliersByName(name), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateSupplierById(@PathVariable("id") Integer id,
                                                         @Valid @RequestBody Supplier supplier) {
        supplierService.updateSupplierById(id, supplier);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteSupplierById(@PathVariable("id") Integer id) {
        supplierService.deleteSupplierById(id);
        return ResponseEntity.noContent().build();
    }
}
