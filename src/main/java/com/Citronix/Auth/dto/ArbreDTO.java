package com.Citronix.Auth.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ArbreDTO {

    private int champId;          // ID of the related Champ
    private int âge;              // Age of the Arbre
    private LocalDate datePlantation;  // Planting date of the Arbre

    // Additional fields can be added if needed
}
