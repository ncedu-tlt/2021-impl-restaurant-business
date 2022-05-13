package com.netckracker.ordering.controller;

import com.netckracker.ordering.model.Unit;
import com.netckracker.ordering.service.UnitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("unit")
public class UnitController {
    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createUnit(@RequestBody Unit unit) {
        return ResponseEntity.created(URI.create("/unit/" + unitService.createUnit(unit))).build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Unit>> getAllCategories() {
        return ResponseEntity.ok(unitService.getAllUnits());
    }

    @GetMapping("{id}")
    public ResponseEntity<Unit> getAllCategories(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(unitService.getUnitById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateCategoryById(@PathVariable("id") Integer id,
                                                         @RequestBody Unit unit) {
        unitService.updateUnitById(id, unit);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable("id") Integer id) {
        unitService.deleteUnitById(id);
        return ResponseEntity.noContent().build();
    }

}
