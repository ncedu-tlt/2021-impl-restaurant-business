package com.netckracker.ordering.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DishContentId implements Serializable {

    @Column(name = "dish_id", nullable = false)
    private Integer dishId;

    @Column(name = "product_id", nullable = false)
    private Integer productId;
}
