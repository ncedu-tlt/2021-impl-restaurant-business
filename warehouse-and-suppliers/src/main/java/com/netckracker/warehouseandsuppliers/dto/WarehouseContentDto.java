package com.netckracker.warehouseandsuppliers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netckracker.warehouseandsuppliers.model.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WarehouseContentDto {
        @NotNull(message = "Field must be not null")
        @Positive(message = "Value must be positive")
        private Integer warehouseId;

        @NotNull(message = "Field must be not null")
        @Positive(message = "Value must be positive")
        private Integer productId;

        @NotNull(message = "Field must be not null")
        private LocalDate shelfDate;

        @NotNull(message = "Field must be not null")
        @Positive(message = "Value must be positive")
        private Integer quantity;
}
