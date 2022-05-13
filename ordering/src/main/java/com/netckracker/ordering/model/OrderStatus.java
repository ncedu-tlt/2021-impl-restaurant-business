package com.netckracker.ordering.model;

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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_status")
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotEmpty(message = "Field must be not empty")
    @Size(message = "Field must have length from 2 to 32 symbols", min = 2, max = 32)
    @Column(name = "name", length = 32, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "orderStatus")
    private Set<Order> orders = new LinkedHashSet<>();
}