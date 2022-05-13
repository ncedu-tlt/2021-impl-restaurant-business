package com.netckracker.warehouseandsuppliers.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WarehouseContentId implements Serializable {

    @Serial
    private static final long serialVersionUID = 5722887359493135229L;

    @Column(name = "warehouse_id", nullable = false)
    private Integer warehouseId;

    @Column(name = "product_id", nullable = false)
    private Integer productId;
}