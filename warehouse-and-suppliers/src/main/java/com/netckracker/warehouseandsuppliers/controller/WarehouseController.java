package com.netckracker.warehouseandsuppliers.controller;

import com.netckracker.warehouseandsuppliers.dto.WarehouseContentDto;
import com.netckracker.warehouseandsuppliers.mappers.WarehouseContentMapper;
import com.netckracker.warehouseandsuppliers.model.Warehouse;
import com.netckracker.warehouseandsuppliers.model.WarehouseContentId;
import com.netckracker.warehouseandsuppliers.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createWarehouse(@Valid @RequestBody Warehouse warehouse) {
        return ResponseEntity.created(URI.create("/warehouse/" + warehouseService.createWarehouse(warehouse))).build();
    }

    @PostMapping("content")
    public ResponseEntity<HttpStatus> createWarehouseContent(
            @Valid @RequestBody WarehouseContentDto warehouseContentDto) {
        WarehouseContentId id = warehouseService.createWarehouseContent(
                WarehouseContentMapper.INSTANCE.toWarehouseContent(warehouseContentDto));
        return ResponseEntity.created(URI.create("/warehouse/" + id.getWarehouseId() + "/content/" +
                id.getProductId())).build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Warehouse>> getAllWarehouses() {
        return ResponseEntity.ok(warehouseService.getAllWarehouses());
    }

    @GetMapping("{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(warehouseService.getWarehouseById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> updateWarehouseById(@PathVariable("id") Integer id,
                                                         @Valid @RequestBody Warehouse warehouse) {
        warehouseService.updateWarehouseById(id, warehouse);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteWarehouseById(@PathVariable("id") Integer id) {
        warehouseService.deleteWarehouseById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{warehouse_id}/content/{product_id}")
    public ResponseEntity<HttpStatus> deleteWarehouseContentById(@PathVariable("warehouse_id") Integer warehouseId,
                                                                 @PathVariable("product_id") Integer productId) {
        warehouseService.deleteWarehouseContentById(new WarehouseContentId(warehouseId, productId));
        return ResponseEntity.noContent().build();
    }
}
