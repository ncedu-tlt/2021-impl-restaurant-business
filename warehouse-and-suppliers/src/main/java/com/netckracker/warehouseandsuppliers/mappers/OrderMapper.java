package com.netckracker.warehouseandsuppliers.mappers;

import com.netckracker.warehouseandsuppliers.dto.OrderDto;
import com.netckracker.warehouseandsuppliers.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "supplierId", target = "supplier.id")
    Order toOrder(OrderDto orderDto);
}
