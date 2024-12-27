package org.as1iva.util;

import org.as1iva.dto.PlayerResponseDto;
import org.as1iva.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapperUtil {
    MapperUtil INSTANCE = Mappers.getMapper(MapperUtil.class);

    PlayerResponseDto toDto(Player player);
}
