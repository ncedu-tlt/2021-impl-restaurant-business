package com.netckracker.warehouseandsuppliers.mappers;

import com.netckracker.warehouseandsuppliers.dto.WarehouseContentDto;
import com.netckracker.warehouseandsuppliers.model.WarehouseContent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarehouseContentMapper {
    WarehouseContentMapper INSTANCE = Mappers.getMapper(WarehouseContentMapper.class);

    WarehouseContent toWarehouseContent(WarehouseContentDto warehouseContentDto);
}
