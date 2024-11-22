package com.Citronix.Auth.mapper;

import com.Citronix.Auth.Entity.Arbre;
import com.Citronix.Auth.dto.ArbreDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArbreMapper {

    // Convert DTO to Entity without throwing or accessing the repository directly
    public Arbre toEntity(ArbreDTO arbreDTO) {
        Arbre arbre = new Arbre();
        arbre.setÂge(arbreDTO.getÂge());
        arbre.setDatePlantation(arbreDTO.getDatePlantation());
        // Set the Champ ID in the entity (Champ should be set in the service layer)
        // Champ is set separately in the service after fetching it from the repository
        return arbre;
    }

    // Convert Entity to DTO
    public ArbreDTO toDto(Arbre arbre) {
        ArbreDTO dto = new ArbreDTO();
        if (arbre.getChamp() != null) {
            dto.setChampId(arbre.getChamp().getId());
        }
        dto.setÂge(arbre.getÂge());
        dto.setDatePlantation(arbre.getDatePlantation());
        return dto;
    }

    // Convert List of Entities to List of DTOs
    public List<ArbreDTO> toDtoList(List<Arbre> arbres) {
        return arbres.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
