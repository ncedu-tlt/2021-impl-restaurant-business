package com.netckracker.warehouseandsuppliers.service;

import com.netckracker.warehouseandsuppliers.model.WarehouseContent;
import com.netckracker.warehouseandsuppliers.model.Warehouse;
import com.netckracker.warehouseandsuppliers.model.WarehouseContentId;
import com.netckracker.warehouseandsuppliers.repository.WarehouseContentRepository;
import com.netckracker.warehouseandsuppliers.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public record WarehouseService(WarehouseRepository warehouseRepository, WarehouseContentRepository warehouseContentRepository) {
    public Integer createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse).getId();
    }

    public WarehouseContentId createWarehouseContent(WarehouseContent warehouseContent) {
        return warehouseContentRepository.save(warehouseContent).getId();
    }

    public Iterable<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(Integer id) {
        return warehouseRepository.findById(id).orElseThrow();
    }

    public void updateWarehouseById(Integer id, Warehouse warehouse) {
        if (!warehouseRepository.existsById(id)) {
            throw new NoSuchElementException();
        }

        Warehouse updatedWarehouse = warehouseRepository.findById(id).orElseThrow();
        updatedWarehouse.setAddress(warehouse.getAddress());
        updatedWarehouse.setPhone(warehouse.getPhone());
        warehouseRepository.save(updatedWarehouse);
    }

    public void deleteWarehouseById(Integer id) {
        if (!warehouseRepository.existsById(id)) {
            throw new NoSuchElementException();
        }

        warehouseRepository.deleteById(id);
    }

    public void deleteWarehouseContentById(WarehouseContentId warehouseContentId) {
        if (!warehouseContentRepository.existsById(warehouseContentId)) {
            throw new NoSuchElementException();
        }

        warehouseContentRepository.deleteById(warehouseContentId);
    }
}
