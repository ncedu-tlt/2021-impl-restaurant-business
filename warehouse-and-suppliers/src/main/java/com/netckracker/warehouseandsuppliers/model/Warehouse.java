package com.netckracker.warehouseandsuppliers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotEmpty(message = "Field must be not empty")
    @Size(message = "Field must have length from 2 to 256 symbols", min = 2, max = 256)
    @Column(name = "address", nullable = false, length = 256)
    private String address;

    @NotEmpty(message = "Field must be not empty")
    @Size(message = "Field must have length from 2 to 32 symbols", min = 2, max = 32)
    @Column(name = "phone", nullable = false, length = 32)
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "warehouse")
    private Set<WarehouseContent> warehouseContents = new LinkedHashSet<>();
}