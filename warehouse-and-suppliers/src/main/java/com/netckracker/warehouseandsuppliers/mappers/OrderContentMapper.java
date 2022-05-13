package com.netckracker.warehouseandsuppliers.mappers;

import com.netckracker.warehouseandsuppliers.dto.OrderContentDto;
import com.netckracker.warehouseandsuppliers.model.OrderContent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderContentMapper {
    OrderContentMapper INSTANCE = Mappers.getMapper(OrderContentMapper.class);

    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "productId", target = "product.id")
    OrderContent toOrderContent(OrderContentDto orderContentDto);
}
