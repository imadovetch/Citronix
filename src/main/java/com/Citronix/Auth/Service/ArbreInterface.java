package com.Citronix.Auth.Service;

import com.Citronix.Auth.dto.ArbreDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArbreInterface {

    ResponseEntity<?> createArbre(ArbreDTO arbreDTO);

    ResponseEntity<?> updateArbre(int id, ArbreDTO arbreDTO);

    ResponseEntity<List<ArbreDTO>> getAllArbres();

    ResponseEntity<?> getArbreById(int id);

    ResponseEntity<?> deleteArbre(int id);
}
