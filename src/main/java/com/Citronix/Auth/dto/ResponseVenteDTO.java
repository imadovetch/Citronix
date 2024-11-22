package com.Citronix.Auth.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data

public class ResponseVenteDTO {

    private LocalDate dateVente;
    private Double prixUnitaire;
    private String client;
    private Double quantity;
    private Double Revenu;
    List<ArbreDetDTO> arbreDetDTOList;
}
