package com.netckracker.ordering.mapper;

import com.netckracker.ordering.dto.OrderContentDto;
import com.netckracker.ordering.model.OrderContent;

import com.netckracker.ordering.model.OrderContentId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

public class OrderContentMapper {

    @Mapper
    public interface OnInput {
        OnInput INSTANCE = Mappers.getMapper(OnInput.class);

        @Mapping(source = "orderId", target = "order.id")
        @Mapping(source = "dishId", target = "dish.id")
        @Mapping(source = "orderContentDto", target = "id", qualifiedByName = "contentId")
        OrderContent toOrderContent(OrderContentDto orderContentDto);

        @Named("contentId")
        static OrderContentId contentId(OrderContentDto orderContentDto) {
            return new OrderContentId(orderContentDto.getOrderId(), orderContentDto.getDishId());
        }
    }

    @Mapper
    public interface OnOutput {

    }
}
