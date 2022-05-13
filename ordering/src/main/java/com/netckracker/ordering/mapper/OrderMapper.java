package com.netckracker.ordering.mapper;

import com.netckracker.ordering.dto.OrderDto;
import com.netckracker.ordering.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "orderStatusId", target = "orderStatus.id")
    @Mapping(source = "paymentTypeId", target = "paymentType.id")
    Order toOrder(OrderDto orderDto);
}
