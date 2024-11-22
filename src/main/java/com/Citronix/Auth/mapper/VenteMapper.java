package com.Citronix.Auth.mapper;

import com.Citronix.Auth.Entity.Vente;
import com.Citronix.Auth.dto.VenteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VenteMapper {

    VenteMapper INSTANCE = Mappers.getMapper(VenteMapper.class);

    // Convert Entity to DTO
    @Mapping(source = "recolte.id", target = "recolteId") // Map Recolte ID
    VenteDTO toDto(Vente vente);

    // Convert DTO to Entity
    @Mapping(target = "recolte", ignore = true) // Recolte should be set in the service layer
    Vente toEntity(VenteDTO venteDTO);

    List<VenteDTO> toDtos(List<Vente> ventes);
}
