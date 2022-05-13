package com.netckracker.warehouseandsuppliers.service;

import com.netckracker.warehouseandsuppliers.model.Supplier;
import com.netckracker.warehouseandsuppliers.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public record SupplierService(SupplierRepository supplierRepository) {
    public Integer createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier).getId();
    }


    public Iterable<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Integer id) {
        return supplierRepository.findById(id).orElseThrow();
    }

    public Iterable<Supplier> getAllSuppliersByName(String name) {
        return supplierRepository.findAllByName(name);
    }

    public void updateSupplierById(Integer id, Supplier supplier) {
        Supplier updatedSupplier = supplierRepository.findById(id).orElseThrow();
        updatedSupplier.setName(supplier.getName());
        updatedSupplier.setAddress(supplier.getAddress());
        updatedSupplier.setPhone(supplier.getPhone());
        updatedSupplier.setTix(supplier.getTix());
        updatedSupplier.setMsrn(supplier.getMsrn());
        supplierRepository.save(supplier);
    }

    public void deleteSupplierById(Integer id) {
        if (!supplierRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        supplierRepository.deleteById(id);
    }
}
