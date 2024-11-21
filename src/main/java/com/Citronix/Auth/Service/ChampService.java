package com.Citronix.Auth.Service;

import com.Citronix.Auth.Entity.Champ;
import com.Citronix.Auth.Entity.Ferme;
import com.Citronix.Auth.dto.ChampDTO;
import com.Citronix.Auth.mapper.ChampMapper;
import com.Citronix.Auth.repository.ChampRepository;
import com.Citronix.Auth.repository.FermeRepository;
import com.Citronix.Auth.exaption.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampService {

    private final ChampRepository champRepository;
    private final FermeRepository fermeRepository;
    private final ChampMapper champMapper;

    private static final double MIN_SUPERFICIE = 0.1;
    private static final double MAX_CHAMP_PERCENTAGE = 0.50;
    private static final int MAX_CHAMPS_PER_FERME = 10;

    @Autowired
    public ChampService(ChampRepository champRepository, FermeRepository fermeRepository, ChampMapper champMapper) {
        this.champRepository = champRepository;
        this.fermeRepository = fermeRepository;
        this.champMapper = champMapper;
    }

    public ResponseEntity<?> addChamp(ChampDTO champDTO) {
        Ferme ferme = fermeRepository.findById(champDTO.getFermeId())
                .orElseThrow(() -> new ResourceNotFoundException("Ferme not found with id: " + champDTO.getFermeId()));

        String validationError = validateChampConstraints(champDTO, ferme);
        if (validationError != null) {
            return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
        }

        Champ champ = champMapper.toEntity(champDTO);
        champ.setFerme(ferme);

        Champ savedChamp = champRepository.save(champ);

        return new ResponseEntity<>(champMapper.toDto(savedChamp), HttpStatus.CREATED);
    }

    private String validateChampConstraints(ChampDTO champDTO, Ferme ferme) {
        double superficie = champDTO.getSuperficie();

        if (superficie < MIN_SUPERFICIE) {
            return "Superficie must be at least " + MIN_SUPERFICIE + " hectares (1,000 m²).";
        }

        double maxAllowedSuperficie = ferme.getSuperficie() * MAX_CHAMP_PERCENTAGE;
        if (superficie > maxAllowedSuperficie) {
            return "Champ cannot exceed 50% of the farm's total superficie.";
        }

        List<Champ> champs = ferme.getChamps();
        if (champs.size() >= MAX_CHAMPS_PER_FERME) {
            return "A farm cannot contain more than " + MAX_CHAMPS_PER_FERME + " champs.";
        }

        return null;
    }
}
