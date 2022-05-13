package com.netckracker.ordering.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {

    @NotNull(message = "Field must be not empty")
    @Positive(message = "Value must be positive")
    private Integer cost;

    @NotNull(message = "Field must be not empty")
    private Boolean paymentStatus = false;

    @NotNull(message = "Field must be not empty")
    private Integer orderStatusId;

    @NotNull(message = "Field must be not empty")
    private Integer paymentTypeId;
}
