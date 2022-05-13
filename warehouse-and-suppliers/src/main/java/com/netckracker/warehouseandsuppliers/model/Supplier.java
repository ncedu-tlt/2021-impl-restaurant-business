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
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotEmpty(message = "Field must be not empty")
    @Size(message = "Field must have length from 2 to 32 symbols", min = 2, max = 32)
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @NotEmpty(message = "Field must be not empty")
    @Size(message = "Field must have length from 2 to 256 symbols", min = 2, max = 256)
    @Column(name = "address", nullable = false, length = 256)
    private String address;

    @NotEmpty(message = "Field must be not empty")
    @Column(name = "phone", nullable = false, length = 32)
    private String phone;

    @NotEmpty(message = "Field must be not empty")
    @Size(message = "Field must have 12 symbols length", min = 12, max = 12)
    @Column(name = "tix", nullable = false, length = 12)
    private String tix;

    @NotEmpty(message = "Field must be not empty")
    @Size(message = "Field must have 13 symbols length", min = 13, max = 13)
    @Column(name = "msrn", nullable = false, length = 13)
    private String msrn;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier")
    private Set<Product> products = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "supplier")
    private Set<Order> orders = new LinkedHashSet<>();
}