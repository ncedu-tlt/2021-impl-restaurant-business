package com.netckracker.warehouseandsuppliers.controller;

import com.netckracker.warehouseandsuppliers.dto.ProductDto;
import com.netckracker.warehouseandsuppliers.mappers.ProductMapper;
import com.netckracker.warehouseandsuppliers.model.Product;
import com.netckracker.warehouseandsuppliers.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createProduct(@Valid @RequestBody ProductDto productDto) {
        return ResponseEntity.created(URI.create("/product/" +
                productService.createProduct(ProductMapper.INSTANCE.toProduct(productDto)))).build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("name/{name}")
    public ResponseEntity<Iterable<Product>> getAllProducts(@PathVariable("name") String name) {
        return ResponseEntity.ok(productService.getAllProductsByName(name));
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateProductById(@PathVariable("id") Integer id,
                                                         @Valid @RequestBody ProductDto productDto) {
        productService.updateProductById(id, ProductMapper.INSTANCE.toProduct(productDto));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable("id") Integer id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
