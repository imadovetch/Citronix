package com.Citronix.Auth.mapper;

import com.Citronix.Auth.Entity.Arbre;
import com.Citronix.Auth.dto.ArbreDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArbreMapper {

    ArbreMapper INSTANCE = Mappers.getMapper(ArbreMapper.class);

    // Convert DTO to Entity
    @Mapping(target = "champ", ignore = true) // Champ is handled in the service layer
    Arbre toEntity(ArbreDTO arbreDTO);

    // Convert Entity to DTO
    @Mapping(source = "champ.id", target = "champId")
    ArbreDTO toDto(Arbre arbre);

    // Convert List of Entities to List of DTOs
    List<ArbreDTO> toDtoList(List<Arbre> arbres);

    // Convert List of DTOs to List of Entities
    List<Arbre> toEntityList(List<ArbreDTO> arbreDTOs);
}
