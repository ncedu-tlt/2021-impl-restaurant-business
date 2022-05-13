package com.netckracker.ordering.controller;

import com.netckracker.ordering.model.Category;
import com.netckracker.ordering.service.CategoryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createCategory(@Valid @RequestBody Category category) {
        return ResponseEntity.created(URI.create("/category/" + categoryService.createCategory(category))).build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getAllCategories(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateCategoryById(@PathVariable("id") Integer id,
                                                         @Valid @RequestBody Category category) {
        categoryService.updateCategoryById(id, category);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable("id") Integer id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
