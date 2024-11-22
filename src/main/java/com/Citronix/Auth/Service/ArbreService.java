package com.Citronix.Auth.Service;

import com.Citronix.Auth.Entity.Arbre;
import com.Citronix.Auth.Entity.Champ;
import com.Citronix.Auth.dto.ArbreDTO;
import com.Citronix.Auth.exaption.ResourceNotFoundException;
import com.Citronix.Auth.mapper.ArbreMapper;
import com.Citronix.Auth.repository.ArbreRepository;
import com.Citronix.Auth.repository.ChampRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ArbreService implements ArbreInterface {

    @Autowired
    private ArbreRepository arbreRepository;

    @Autowired
    private ChampService champService;


    @Autowired
    private ChampRepository champRepository;

    @Autowired
    private ArbreMapper arbreMapper;


    public ResponseEntity<?> createArbre(ArbreDTO arbreDTO) {

        Champ champ = champRepository.findById(arbreDTO.getChampId())
                .orElseThrow(() -> new ResourceNotFoundException("Champ not found with ID: " + arbreDTO.getChampId()));


        String validationError = validateArbreConstraints(arbreDTO, champ);
        if (validationError != null) {
            return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
        }



        Arbre arbre = arbreMapper.toEntity(arbreDTO);
        arbre.setChamp(champ);
        Arbre savedArbre = arbreRepository.save(arbre);

        return new ResponseEntity<>(savedArbre, HttpStatus.CREATED);
    }


    private String validateArbreConstraints(ArbreDTO arbreDTO, Champ champ) {

        if (arbreDTO.getÂge() < 1) {
            return "Tree age must be at least 1 year.";
        }


        double champArea = champ.getSuperficie();

        long currentTreeCount = arbreRepository.countByChampId(champ.getId());


        double maxTrees = (champArea / 1000) * 10;


        if (currentTreeCount >= maxTrees) {
            return "Champ cannot contain more than " + maxTrees + " trees. Current density exceeds the limit.";
        }


        return null;
    }


    public ResponseEntity<?> updateArbre(int id, ArbreDTO arbreDTO) {

        Arbre existingArbre = arbreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arbre not found with ID: " + id));


        Champ champ = champRepository.findById(arbreDTO.getChampId())
                .orElseThrow(() -> new ResourceNotFoundException("Champ not found with ID: " + arbreDTO.getChampId()));


        existingArbre.setÂge(arbreDTO.getÂge());
        existingArbre.setDatePlantation(arbreDTO.getDatePlantation());
        existingArbre.setChamp(champ);


        Arbre updatedArbre = arbreRepository.save(existingArbre);
        return new ResponseEntity<>(arbreMapper.toDto(updatedArbre), HttpStatus.OK);
    }


    public ResponseEntity<List<ArbreDTO>> getAllArbres() {
        List<Arbre> arbres = arbreRepository.findAll();
        return new ResponseEntity<>(arbreMapper.toDtoList(arbres), HttpStatus.OK);
    }


    public ResponseEntity<?> getArbreById(int id) {
        Arbre arbre = arbreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arbre not found with ID: " + id));
        return new ResponseEntity<>(arbreMapper.toDto(arbre), HttpStatus.OK);
    }


    public ResponseEntity<?> deleteArbre(int id) {
        Arbre arbre = arbreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arbre not found with ID: " + id));
        arbreRepository.delete(arbre);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
