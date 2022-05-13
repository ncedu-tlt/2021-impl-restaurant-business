package com.netckracker.ordering.repository;

import com.netckracker.ordering.model.DishContent;
import com.netckracker.ordering.model.DishContentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishContentRepository extends JpaRepository<DishContent, DishContentId> {

}
