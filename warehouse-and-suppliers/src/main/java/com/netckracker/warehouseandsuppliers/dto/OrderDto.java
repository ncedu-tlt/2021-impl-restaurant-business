package com.netckracker.warehouseandsuppliers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {

    @NotNull(message = "Field must be not null")
    @Positive(message = "Value must be positive")
    private Integer supplierId;

    @NotNull(message = "Field must be not null")
    private LocalDate date;

    @NotNull(message = "Field must be not null")
    @Min(value = 1, message = "The evaluation must be bigger than or equal to 1")
    @Max(value = 10, message = "The evaluation must be less then or equal to 10")
    private Integer evaluation;

    private String comment;
}
