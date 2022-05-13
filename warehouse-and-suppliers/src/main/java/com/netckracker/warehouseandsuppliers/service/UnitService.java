package com.netckracker.warehouseandsuppliers.service;

import com.netckracker.warehouseandsuppliers.model.Unit;
import com.netckracker.warehouseandsuppliers.repository.UnitRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public record UnitService(UnitRepository unitRepository) {
    public Integer createUnit(Unit unit) {
        return unitRepository.save(unit).getId();
    }

    public Iterable<Unit> getAllUnits() {
        return unitRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Unit getUnitById(Integer id) {
        return unitRepository.findById(id).orElseThrow();
    }

    public void updateUnitById(Integer id, Unit unit) {
        Unit updatedCategory = unitRepository.findById(id).orElseThrow();
        updatedCategory.setName(unit.getName());
        unitRepository.save(updatedCategory);
    }

    public void deleteUnitById(Integer id) {
        if (!unitRepository.existsById(id)) {
            throw new NoSuchElementException();
        }
        unitRepository.deleteById(id);
    }
}
