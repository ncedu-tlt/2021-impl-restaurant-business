package com.netckracker.ordering.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderContentId implements Serializable {

    @Column(name = "order_id", nullable = false)
    private int orderId;

    @Column(name = "dish_id", nullable = false)
    private int dishId;
}
