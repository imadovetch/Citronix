package com.PigeonSkyRace.Auth.mapper;

import com.PigeonSkyRace.Auth.Entity.Champ;
import com.PigeonSkyRace.Auth.dto.ChampDTO;
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
