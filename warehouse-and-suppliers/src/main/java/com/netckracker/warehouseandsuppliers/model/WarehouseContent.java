package com.netckracker.warehouseandsuppliers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "balance")
public class WarehouseContent {

    @EmbeddedId
    private WarehouseContentId id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @MapsId("warehouseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "shelf_date", nullable = false)
    private LocalDate shelfDate;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}