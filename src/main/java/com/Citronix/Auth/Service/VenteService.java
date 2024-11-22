package com.Citronix.Auth.Service;

import com.Citronix.Auth.Entity.Arbre;
import com.Citronix.Auth.Entity.Champ;
import com.Citronix.Auth.Entity.Recolte;
import com.Citronix.Auth.Entity.Vente;
import com.Citronix.Auth.dto.ArbreDetDTO;
import com.Citronix.Auth.dto.ResponseVenteDTO;
import com.Citronix.Auth.dto.VenteDTO;
import com.Citronix.Auth.exaption.ResourceNotFoundException;
import com.Citronix.Auth.mapper.VenteMapper;
import com.Citronix.Auth.repository.RecolteRepository;
import com.Citronix.Auth.repository.VenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class VenteService implements VenteInterface{

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private RecolteRepository recolteRepository;

    @Autowired
    private VenteMapper venteMapper;

    public ResponseEntity<?> createVente(VenteDTO venteDTO) {
        // Fetch the recolte by ID
        Recolte recolte = recolteRepository.findById(Math.toIntExact(venteDTO.getRecolteId()))
                .orElseThrow(() -> new ResourceNotFoundException("Recolte not found with ID: " + venteDTO.getRecolteId()));

        Optional<Vente> existingVente = venteRepository.findByRecolte(recolte);
        if (existingVente.isPresent()) {
            return new ResponseEntity<>("A Vente already exists for this Recolte", HttpStatus.CONFLICT);
        }


        Vente vente = venteMapper.toEntity(venteDTO);
        vente.setRecolte(recolte);

        Vente savedVente = venteRepository.save(vente);
        return new ResponseEntity<>(venteMapper.toDto(savedVente), HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateVente(Long id, VenteDTO venteDTO) {
        Vente existingVente = venteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vente not found with ID: " + id));

        Recolte recolte = recolteRepository.findById(Math.toIntExact(venteDTO.getRecolteId()))
                .orElseThrow(() -> new ResourceNotFoundException("Recolte not found with ID: " + venteDTO.getRecolteId()));

        existingVente.setDateVente(venteDTO.getDateVente());
        existingVente.setPrixUnitaire(venteDTO.getPrixUnitaire());
        existingVente.setClient(venteDTO.getClient());
        existingVente.setRecolte(recolte);

        Vente updatedVente = venteRepository.save(existingVente);
        return new ResponseEntity<>(venteMapper.toDto(updatedVente), HttpStatus.OK);
    }

    public ResponseEntity<?> getVenteById(Long id) {
        Vente vente = venteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vente not found with ID: " + id));

        return new ResponseEntity<>(venteMapper.toDto(vente), HttpStatus.OK);
    }

    public ResponseEntity<?> getAllVentes() {
        List<Vente> ventes = venteRepository.findAll();
        return new ResponseEntity<>(venteMapper.toDtos(ventes), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteVente(Long id) {
        Vente vente = venteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vente not found with ID: " + id));

        venteRepository.delete(vente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getdetailsById(Long id) {
        Vente vente = venteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vente not found with id: " + id));

        Champ champ = vente.getRecolte().getChamp(); // Access the Champ of the Vente
        List<Arbre> arbres = champ.getArbres(); // Get the list of Arbres in the Champ

        // Get quantity from the Recolte associated with this Vente
        double quantity = vente.getRecolte().getQuantiteTotale();

        // Calculate revenue (Revenu)
        double revenu = quantity * vente.getPrixUnitaire();

        // Map Arbre details to DTO
        List<ArbreDetDTO> arbreDetDTOList = arbres.stream()
                .map(arbre -> {
                    ArbreDetDTO arbreDetDTO = new ArbreDetDTO();
                    arbreDetDTO.setId(arbre.getId());
                    arbreDetDTO.setQuantity(GetArbreauantity(arbre));
                    return arbreDetDTO;
                })
                .collect(Collectors.toList());

        // Create and populate ResponseVenteDTO
        ResponseVenteDTO responseVenteDTO = new ResponseVenteDTO();
        responseVenteDTO.setDateVente(vente.getDateVente());
        responseVenteDTO.setPrixUnitaire(vente.getPrixUnitaire());
        responseVenteDTO.setClient(vente.getClient());
        responseVenteDTO.setQuantity(quantity); // Set quantity from Recolte
        responseVenteDTO.setRevenu(revenu);
        responseVenteDTO.setArbreDetDTOList(arbreDetDTOList);

        return new ResponseEntity<>(responseVenteDTO, HttpStatus.OK);
    }
    private Double GetArbreauantity(Arbre arbre){

        int age = arbre.getÂge();

        // Apply a simple logic based on tree age
        if (age >= 1 && age <= 3) {
             return  2.5;
        } else if (age > 3 && age <= 10) {
             return  12.0;
        } else if (age > 10) {
             return  20.0;
        }
        return  0.0;


    }
}
