package com.netckracker.warehouseandsuppliers.dto;

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
public class OrderContentDto {
    @NotNull(message = "Field must be not null")
    @Positive(message = "Value must be positive")
    private Integer orderId;

    @NotNull(message = "Field must be not null")
    @Positive(message = "Value must be positive")
    private Integer productId;

    @NotNull(message = "Field must be not null")
    @Positive(message = "Value must be positive")
    private Integer quantity;

}
