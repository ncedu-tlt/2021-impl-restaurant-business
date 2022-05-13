package com.netckracker.warehouseandsuppliers.service;

import com.netckracker.warehouseandsuppliers.error.NoSuchRelatedElementException;
import com.netckracker.warehouseandsuppliers.model.Product;
import com.netckracker.warehouseandsuppliers.model.Supplier;
import com.netckracker.warehouseandsuppliers.model.Unit;
import com.netckracker.warehouseandsuppliers.repository.ProductRepository;
import com.netckracker.warehouseandsuppliers.repository.SupplierRepository;
import com.netckracker.warehouseandsuppliers.repository.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public record ProductService(ProductRepository productRepository, SupplierRepository supplierRepository,
                             UnitRepository unitRepository) {
    public Integer createProduct(Product product) {
        if (!supplierRepository.existsById(product.getSupplier().getId())) {
            throw new NoSuchRelatedElementException(Supplier.class.getSimpleName());
        }
        if (!unitRepository.existsById(product.getUnit().getId())) {
            throw new NoSuchRelatedElementException(Unit.class.getSimpleName());
        }
        return productRepository.save(product).getId();
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Iterable<Product> getAllProductsByName(String name) {
        return productRepository.findAllByName(name);
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow();
    }

    public void updateProductById(Integer id, Product product) {
        Product updatedProduct = productRepository.findById(id).orElseThrow();
        if (!supplierRepository.existsById(product.getSupplier().getId())) {
            throw new NoSuchRelatedElementException(Supplier.class.getSimpleName());
        }
        if (!unitRepository.existsById(product.getUnit().getId())) {
            throw new NoSuchRelatedElementException(Unit.class.getSimpleName());
        }
        updatedProduct.setSupplier(product.getSupplier());
        updatedProduct.setName(product.getName());
        updatedProduct.setUnit(product.getUnit());
        updatedProduct.setCost(product.getCost());
        updatedProduct.setDescription(product.getDescription());
        productRepository.save(updatedProduct);
    }

    public void deleteProductById(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        productRepository.deleteById(id);
    }
}
