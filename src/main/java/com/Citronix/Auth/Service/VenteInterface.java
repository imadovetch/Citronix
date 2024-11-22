package com.Citronix.Auth.Service;

import com.Citronix.Auth.dto.ResponseVenteDTO;
import com.Citronix.Auth.dto.VenteDTO;
import org.springframework.http.ResponseEntity;

public interface VenteInterface {

    ResponseEntity<?> createVente(VenteDTO venteDTO);

    ResponseEntity<?> updateVente(Long id, VenteDTO venteDTO);

    ResponseEntity<?> getVenteById(Long id);

    ResponseEntity<?> getAllVentes();

    ResponseEntity<?> deleteVente(Long id);

    ResponseEntity<?> getdetailsById(Long id);
}
