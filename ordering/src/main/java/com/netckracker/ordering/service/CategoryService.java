package com.netckracker.ordering.service;

import com.netckracker.ordering.model.Category;
import com.netckracker.ordering.repository.CategoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public record CategoryService(CategoryRepository categoryRepository) {

    public Integer createCategory(Category category) {
        return categoryRepository.save(category).getId();
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public void updateCategoryById(Integer id, Category category) {
        Category updatedCategory = categoryRepository.findById(id).orElseThrow();
        updatedCategory.setName(category.getName());
        categoryRepository.save(updatedCategory);
    }

    public void deleteCategoryById(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        categoryRepository.deleteById(id);
    }
}
