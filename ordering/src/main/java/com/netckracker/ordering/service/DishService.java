package com.netckracker.ordering.service;

import com.netckracker.ordering.model.Dish;
import com.netckracker.ordering.repository.CategoryRepository;
import com.netckracker.ordering.repository.DishContentRepository;
import com.netckracker.ordering.repository.DishRepository;
import com.netckracker.ordering.repository.UnitRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public record DishService(DishRepository dishRepository, DishContentRepository dishContentRepository,
                          CategoryRepository categoryRepository, UnitRepository unitsRepository) {

    public Integer createDish(Dish dish) {
        if (!categoryRepository.existsById(dish.getCategory().getId())) {
            throw new NoSuchElementException();
        }
        if (!unitsRepository.existsById(dish.getUnit().getId())) {
            throw new NoSuchElementException();
        }
        return dishRepository.save(dish).getId();
    }

    public Iterable<Dish> getAllDishes() {
        return dishRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Dish getDishById(Integer id) {
        return dishRepository.findById(id).orElseThrow();
    }

    public void updateDishById(Integer id, Dish dish) {
        if (!categoryRepository.existsById(dish.getCategory().getId())) {
            throw new NoSuchElementException();
        }
        if (!unitsRepository.existsById(dish.getUnit().getId())) {
            throw new NoSuchElementException();
        }
        Dish updatedDish = dishRepository.findById(id).orElseThrow();
        updatedDish.setName(dish.getName());
        updatedDish.setUnit(dish.getUnit());
        updatedDish.setCategory(dish.getCategory());
        updatedDish.setCost(dish.getCost());
        dishRepository.save(updatedDish);
    }

    public void deleteDishById(Integer id) {
        if (!dishRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        dishRepository.deleteById(id);
    }
}
