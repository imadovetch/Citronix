package com.Citronix.Auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenteDTO {
    private Long id;
    private LocalDate dateVente;
    private Double prixUnitaire;
    private String client;
    private Long recolteId;  // Reference to associated recolte
}
