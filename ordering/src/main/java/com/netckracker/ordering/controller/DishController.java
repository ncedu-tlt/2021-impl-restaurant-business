package com.netckracker.ordering.controller;

import com.netckracker.ordering.dto.DishDto;
import com.netckracker.ordering.mapper.DishMapper;
import com.netckracker.ordering.model.Dish;
import com.netckracker.ordering.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("dish")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createDish(@Valid @RequestBody DishDto dishDto) {
        return ResponseEntity.created(URI.create("/dish/" + dishService.createDish(
                DishMapper.INSTANCE.toDish(dishDto)))).build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Dish>> getAllDishes() {
        return ResponseEntity.ok(dishService.getAllDishes());
    }

    @GetMapping("{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(dishService.getDishById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateDishById(@PathVariable("id") Integer id,
                                                     @Valid @RequestBody DishDto dishDto) {
        dishService.updateDishById(id, DishMapper.INSTANCE.toDish(dishDto));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> updateDishById(@PathVariable("id") Integer id) {
        dishService.deleteDishById(id);
        return ResponseEntity.noContent().build();
    }

}
