package com.netckracker.ordering.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netckracker.ordering.model.DishContentId;
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
public class DishContentDto {

    @JsonIgnore
    private DishContentId dishContentId;

    @NotNull(message = "Field must be not null")
    @Positive(message = "Value must be positive")
    private Integer dishId;

    @NotNull(message = "Field must be not null")
    @Positive(message = "Value must be positive")
    private Integer productId;

    @NotNull(message = "Field must be not null")
    @Positive(message = "Value must be positive")
    private Integer quantity;

}
