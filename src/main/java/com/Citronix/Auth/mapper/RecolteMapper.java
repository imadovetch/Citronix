package com.Citronix.Auth.mapper;

import com.Citronix.Auth.Entity.Recolte;
import com.Citronix.Auth.dto.RecolteDTO;
import org.springframework.stereotype.Component;

@Component
public class RecolteMapper {

    public Recolte toEntity(RecolteDTO dto) {
        Recolte recolte = new Recolte();
        recolte.setDateRecolte(dto.dateRecolte());
        recolte.setQuantiteTotale(dto.quantiteTotale());
        recolte.setSeason(dto.season());
        return recolte;
    }

    public RecolteDTO toDto(Recolte recolte) {
        return new RecolteDTO(
                recolte.getId(),
                recolte.getChamp().getId(),
                recolte.getSeason(),
                recolte.getDateRecolte(),
                recolte.getQuantiteTotale()
        );
    }
}
