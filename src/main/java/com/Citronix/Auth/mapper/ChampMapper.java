package com.Citronix.Auth.mapper;

import com.Citronix.Auth.Entity.Champ;
import com.Citronix.Auth.dto.ChampDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChampMapper {

    ChampMapper INSTANCE = Mappers.getMapper(ChampMapper.class);

    @Mapping(source = "ferme.id",target = "fermeId")
    ChampDTO toDto(Champ champ);

    @Mapping(source = "fermeId",target = "ferme.id")
    Champ toEntity(ChampDTO champDTO);
}
