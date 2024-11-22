package com.Citronix.Auth.Controller;

import com.Citronix.Auth.Service.ArbreService;
import com.Citronix.Auth.dto.ArbreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arbres")
public class ArbreController {

    @Autowired
    private ArbreService arbreService;


    @PostMapping
    public ResponseEntity<?> addArbre(@RequestBody ArbreDTO arbreDTO) {
        return arbreService.createArbre(arbreDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArbre(@PathVariable int id, @RequestBody ArbreDTO arbreDTO) {
        return arbreService.updateArbre(id, arbreDTO);
    }

    @GetMapping
    public ResponseEntity<List<ArbreDTO>> getAllArbres() {
        return arbreService.getAllArbres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArbreById(@PathVariable int id) {
        return arbreService.getArbreById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArbre(@PathVariable int id) {
        return arbreService.deleteArbre(id);
    }
}
