package com.PigeonSkyRace.Auth.Service;

import com.PigeonSkyRace.Auth.Entity.Ferme;
import com.PigeonSkyRace.Auth.dto.FermeDTO;
import com.PigeonSkyRace.Auth.mapper.FermeMapper;
import com.PigeonSkyRace.Auth.repository.FermeRepository;
import com.PigeonSkyRace.Auth.exaption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FermeService {

    private final FermeRepository fermeRepository;
    private final FermeMapper fermeMapper;

    private static final double MIN_SUPERFICIE = 0.2;


    @Autowired
    public FermeService(FermeRepository fermeRepository, FermeMapper fermeMapper) {
        this.fermeRepository = fermeRepository;
        this.fermeMapper = fermeMapper;
    }

    public ResponseEntity<?> addFerme(FermeDTO fermeDTO) {
        if (fermeDTO.getSuperficie() < MIN_SUPERFICIE) {
            return new ResponseEntity<>("La superficie de la ferme doit être d'au moins " + MIN_SUPERFICIE + " hectares.", HttpStatus.BAD_REQUEST);
        }

        Ferme ferme = fermeMapper.toEntity(fermeDTO);
        Ferme savedFerme = fermeRepository.save(ferme);

        return new ResponseEntity<>(fermeMapper.toDTO(savedFerme), HttpStatus.CREATED);
    }

    public List<FermeDTO> getAllFermes() {
        List<Ferme> fermes = fermeRepository.findAll();
        return fermes.stream().map(fermeMapper::toDTO).toList();
    }

    public ResponseEntity<?> getFermeById(int id) {
        Ferme ferme = fermeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ferme not found with id: " + id));
        return ResponseEntity.ok(fermeMapper.toDTO(ferme));
    }
}
