package com.netckracker.warehouseandsuppliers.mappers;

import com.netckracker.warehouseandsuppliers.dto.ProductDto;
import com.netckracker.warehouseandsuppliers.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "unitId", target = "unit.id")
    @Mapping(source = "supplierId", target = "supplier.id")
    Product toProduct(ProductDto productDto);
}
