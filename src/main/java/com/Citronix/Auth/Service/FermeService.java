package com.Citronix.Auth.Service;

import com.Citronix.Auth.dto.FermeDTO;
import com.Citronix.Auth.exaption.ResourceNotFoundException;
import com.Citronix.Auth.Entity.Ferme;
import com.Citronix.Auth.repository.FermeRepository;
import com.Citronix.Auth.mapper.FermeMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FermeService implements FermeInterface {

    private final FermeRepository fermeRepository;
    private final FermeMapper fermeMapper;

    @Autowired
    public FermeService(FermeRepository fermeRepository, FermeMapper fermeMapper) {
        this.fermeRepository = fermeRepository;
        this.fermeMapper = fermeMapper;
    }

    public FermeDTO addFerme(FermeDTO fermeDTO) {
        Ferme ferme = fermeMapper.toEntity(fermeDTO);

        if (ferme.getDateDeCreation() == null) {
            ferme.setDateDeCreation(new Date());
        }

        Ferme savedFerme = fermeRepository.save(ferme);

        return fermeMapper.toDTO(savedFerme);
    }

    public List<FermeDTO> getAllFermes() {
        List<Ferme> fermes = fermeRepository.findAll();
        return fermes.stream().map(fermeMapper::toDTO).collect(Collectors.toList());
    }

    public FermeDTO getFermeById(int id) {
        Ferme ferme = fermeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ferme with ID " + id + " not found."));
        return fermeMapper.toDTO(ferme);
    }
    public FermeDTO delete(int id) {
        Ferme ferme = fermeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ferme with ID " + id + " not found."));
        fermeRepository.delete(ferme);
        return fermeMapper.toDTO(ferme);
    }
}
