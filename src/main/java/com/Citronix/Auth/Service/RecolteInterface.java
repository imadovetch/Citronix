package com.Citronix.Auth.Service;

import com.Citronix.Auth.dto.RecolteDTO;
import org.springframework.http.ResponseEntity;

public interface RecolteInterface {

    ResponseEntity<?> createRecolte(RecolteDTO dto);

    ResponseEntity<?> updateRecolte(int id, RecolteDTO dto);

    ResponseEntity<?> getAllRecoltes();

    ResponseEntity<?> getRecolteById(int id);

    ResponseEntity<?> deleteRecolte(int id);

}
