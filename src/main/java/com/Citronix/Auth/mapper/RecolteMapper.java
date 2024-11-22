package com.Citronix.Auth.mapper;

import com.Citronix.Auth.Entity.Recolte;
import com.Citronix.Auth.dto.RecolteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecolteMapper {

    RecolteMapper INSTANCE = Mappers.getMapper(RecolteMapper.class);

    // Convert DTO to Entity
    @Mapping(target = "champ", ignore = true) // Champ should be set in the service layer
    Recolte toEntity(RecolteDTO dto);

    // Convert Entity to DTO
    @Mapping(source = "champ.id", target = "champId")
    RecolteDTO toDto(Recolte recolte);
    List<RecolteDTO> toDtoList(List<Recolte> recolteList);
}
