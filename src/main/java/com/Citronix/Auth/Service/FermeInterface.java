package com.Citronix.Auth.Service;

import com.Citronix.Auth.dto.FermeDTO;
import java.util.List;

public interface FermeInterface {

    FermeDTO addFerme(FermeDTO fermeDTO);

    List<FermeDTO> getAllFermes();

    FermeDTO getFermeById(int id);

    FermeDTO delete(int id);
}
