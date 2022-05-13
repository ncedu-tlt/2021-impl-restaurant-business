package com.netckracker.warehouseandsuppliers.repository;

import com.netckracker.warehouseandsuppliers.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Modifying
    @Query("SELECT s FROM Supplier s WHERE LOWER(s.name) LIKE CONCAT('%', LOWER(:name), '%')")
    Iterable<Supplier> findAllByName(String name);
}