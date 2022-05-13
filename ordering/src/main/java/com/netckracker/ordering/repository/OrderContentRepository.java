package com.netckracker.ordering.repository;

import com.netckracker.ordering.model.OrderContent;
import com.netckracker.ordering.model.OrderContentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OrderContentRepository extends JpaRepository<OrderContent, OrderContentId> {

    @Transactional
    Iterable<OrderContent> findAllByOrderId(Integer id);

    @Transactional
    @NonNull
    Optional<OrderContent> findById(@NonNull OrderContentId id);

    @Transactional
    void deleteById(@NonNull OrderContentId id);
}
