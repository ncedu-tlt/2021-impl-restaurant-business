package com.netckracker.ordering.mapper;

import com.netckracker.ordering.dto.DishDto;
import com.netckracker.ordering.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DishMapper {
    DishMapper INSTANCE = Mappers.getMapper(DishMapper.class);

    @Mapping(source = "unitId", target = "unit.id")
    @Mapping(source = "categoryId", target = "category.id")
    Dish toDish(DishDto DishDto);
}
