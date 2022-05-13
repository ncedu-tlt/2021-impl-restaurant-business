package com.netckracker.warehouseandsuppliers.repository;

import com.netckracker.warehouseandsuppliers.model.OrderContent;
import com.netckracker.warehouseandsuppliers.model.OrderContentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderContentRepository extends JpaRepository<OrderContent, OrderContentId> {

}
