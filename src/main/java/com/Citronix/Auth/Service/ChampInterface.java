package com.Citronix.Auth.Service;

import com.Citronix.Auth.dto.ChampDTO;
import org.springframework.http.ResponseEntity;

public interface ChampInterface {

    ResponseEntity<?> addChamp(ChampDTO champDTO);

    ResponseEntity<?> getbyid(Long id);
}
