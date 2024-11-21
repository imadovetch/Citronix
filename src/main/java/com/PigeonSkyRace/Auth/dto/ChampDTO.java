package com.PigeonSkyRace.Auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChampDTO {
    private int id; // This can be omitted for new inserts
    private Double superficie;
    private int fermeId; // Reference to the associated `Ferme`
}
