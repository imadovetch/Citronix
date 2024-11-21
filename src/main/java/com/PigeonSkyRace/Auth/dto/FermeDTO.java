
package com.PigeonSkyRace.Auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FermeDTO {
    private int id;
    private String nom;
    private String localisation;
    private Double superficie;
    private Date dateDeCreation;
}
