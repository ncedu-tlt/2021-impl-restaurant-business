package com.netckracker.warehouseandsuppliers.repository;

import com.netckracker.warehouseandsuppliers.model.WarehouseContent;
import com.netckracker.warehouseandsuppliers.model.WarehouseContentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface WarehouseContentRepository extends JpaRepository<WarehouseContent, WarehouseContentId> {
    @Transactional
    public Iterable<WarehouseContent> findAllByWarehouseId(Integer id);
}
