package com.Citronix.Auth.Service;

import com.Citronix.Auth.Entity.Arbre;
import com.Citronix.Auth.Entity.Recolte;
import com.Citronix.Auth.Entity.Champ;
import com.Citronix.Auth.dto.RecolteDTO;
import com.Citronix.Auth.exaption.ResourceNotFoundException;
import com.Citronix.Auth.mapper.RecolteMapper;
import com.Citronix.Auth.repository.RecolteRepository;
import com.Citronix.Auth.repository.ChampRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
   import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import java.util.List;

@Service
@Transactional
public class RecolteService {

    private final RecolteRepository recolteRepository;
    private final ChampRepository champRepository;
    private final RecolteMapper recolteMapper;

    @Autowired
    public RecolteService(RecolteRepository recolteRepository, ChampRepository champRepository, RecolteMapper recolteMapper) {
        this.recolteRepository = recolteRepository;
        this.champRepository = champRepository;
        this.recolteMapper = recolteMapper;
    }

    public ResponseEntity<?> createRecolte(RecolteDTO dto) {


        Champ champ = champRepository.findById(dto.champId())
                .orElseThrow(() -> new ResourceNotFoundException("Champ not found with id: " + dto.champId()));

        Double quantite = calculateQuantite(champ);

        String validationError = validateRecolteForSeason(dto, champ);
        if (validationError != null) {
            return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
        }

        Recolte recolte = recolteMapper.toEntity(dto);
        recolte.setQuantiteTotale(quantite);
        recolte.setChamp(champ);

        Recolte savedRecolte = recolteRepository.save(recolte);

        return new ResponseEntity<>(recolteMapper.toDto(savedRecolte), HttpStatus.CREATED);
    }
    public Double calculateQuantite(Champ champ) {
        double totalQuantite = 0.0;


        for (Arbre arbre : champ.getArbres()) {
            int age = arbre.getÂge();


            if (age >= 1 && age <= 3) {
                totalQuantite += 2.5;
            } else if (age > 3 && age <= 10) {
                totalQuantite += 12.0;
            } else if (age > 10) {
                totalQuantite += 20.0;
            }
        }

        return totalQuantite;
    }


    public ResponseEntity<?> updateRecolte(int id, RecolteDTO dto) {
        Recolte existingRecolte = recolteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recolte not found with id: " + id));

        Champ champ = champRepository.findById(dto.champId())
                .orElseThrow(() -> new ResourceNotFoundException("Champ not found with id: " + dto.champId()));

        String validationError = validateRecolteForSeason(dto, champ);
        if (validationError != null) {
            return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
        }

        existingRecolte.setDateRecolte(dto.dateRecolte());
        existingRecolte.setQuantiteTotale(dto.quantiteTotale());
        existingRecolte.setSeason(dto.season());
        existingRecolte.setChamp(champ);

        Recolte updatedRecolte = recolteRepository.save(existingRecolte);

        return new ResponseEntity<>(recolteMapper.toDto(updatedRecolte), HttpStatus.OK);
    }

    public ResponseEntity<?> getAllRecoltes() {
        List<Recolte> recoltes = recolteRepository.findAll();
        return new ResponseEntity<>(recolteMapper.toDtoList(recoltes), HttpStatus.OK);
    }

    public ResponseEntity<?> getRecolteById(int id) {
        Recolte recolte = recolteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recolte not found with id: " + id));
        return new ResponseEntity<>(recolteMapper.toDto(recolte), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteRecolte(int id) {
        Recolte recolte = recolteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recolte not found with id: " + id));
        recolteRepository.delete(recolte);
        return new ResponseEntity<>("Recolte deleted successfully", HttpStatus.NO_CONTENT);
    }


    private String validateRecolteForSeason(RecolteDTO dto, Champ champ) {


        Optional<Recolte> lastRecolte = recolteRepository.findLatestRecolteByChampId((long) champ.getId());

        if (lastRecolte.isPresent()) {
            Recolte previousRecolte = lastRecolte.get();


            if (previousRecolte.getSeason() == dto.season()) {
                return "A recolte has already been recorded for this champ in the " + dto.season() + " season. last recolte was " + previousRecolte.getSeason();
            }


            LocalDate lastRecolteDate = previousRecolte.getDateRecolte();
            LocalDate currentRecolteDate = dto.dateRecolte();

            Period periodBetween = Period.between(lastRecolteDate, currentRecolteDate);


            if (periodBetween.getMonths() < 3) {
                return "At least 3 months must pass between each recolte. The last recolte was on: " + lastRecolteDate;
            }
        }

        return null;
    }

}
