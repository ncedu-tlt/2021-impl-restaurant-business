package com.netckracker.ordering.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DishDto {

    @NotEmpty(message = "Field must be not empty")
    @Size(message = "Field must have length from 2 to 32 symbols", min = 2, max = 32)
    private String name;

    @NotNull(message = "Field must be not null")
    private Integer unitId;

    @NotNull(message = "Field must be not null")
    private Integer categoryId;

    @NotNull(message = "Field must be not null")
    @Positive(message = "Value must be positive")
    private Integer cost;
}
