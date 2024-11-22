package com.Citronix.Auth.Service;

import com.Citronix.Auth.dto.ResponseVenteDTO;
import com.Citronix.Auth.dto.VenteDTO;
import org.springframework.http.ResponseEntity;

public interface VenteInterface {

    // Method to create a new Vente
    ResponseEntity<?> createVente(VenteDTO venteDTO);

    // Method to update an existing Vente by ID
    ResponseEntity<?> updateVente(Long id, VenteDTO venteDTO);

    // Method to retrieve a Vente by ID
    ResponseEntity<?> getVenteById(Long id);

    // Method to retrieve all Ventes
    ResponseEntity<?> getAllVentes();

    // Method to delete a Vente by ID
    ResponseEntity<?> deleteVente(Long id);

    // Method to get detailed Vente information (including related entities) by ID
    ResponseEntity<?> getdetailsById(Long id);
}
