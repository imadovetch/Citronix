package com.Citronix.Auth.dto;

import com.Citronix.Auth.Entity.Season;
import java.time.LocalDate;

public record RecolteDTO(
        int id,
        int champId,
        Season season,
        LocalDate dateRecolte,
        double quantiteTotale
) {}
