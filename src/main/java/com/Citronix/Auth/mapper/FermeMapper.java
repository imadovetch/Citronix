package com.Citronix.Auth.mapper;

import com.Citronix.Auth.dto.FermeDTO;
import com.Citronix.Auth.Entity.Ferme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FermeMapper {

    FermeMapper INSTANCE = Mappers.getMapper(FermeMapper.class);

    @Mapping(target = "dateDeCreation", source = "dateDeCreation")
    FermeDTO toDTO(Ferme ferme);


    @Mapping(target = "dateDeCreation", source = "dateDeCreation")
    Ferme toEntity(FermeDTO fermeDTO);

    List<FermeDTO> toDTOList(List<Ferme> fermes);


    List<Ferme> toEntityList(List<FermeDTO> fermeDTOs);
}
