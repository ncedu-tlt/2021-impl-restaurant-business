package com.netckracker.warehouseandsuppliers.repository;

import com.netckracker.warehouseandsuppliers.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {

}