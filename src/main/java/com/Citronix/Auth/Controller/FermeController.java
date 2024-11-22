package com.Citronix.Auth.Controller;

import com.Citronix.Auth.Service.FermeService;
import com.Citronix.Auth.dto.FermeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fermes")
public class FermeController {

    private final FermeService fermeService;

    @Autowired
    public FermeController(FermeService fermeService) {
        this.fermeService = fermeService;
    }

    @PostMapping("/add")
    public ResponseEntity<FermeDTO> addFerme(@RequestBody FermeDTO fermeDTO) {
        FermeDTO createdFerme = fermeService.addFerme(fermeDTO);
        return ResponseEntity.status(201).body(createdFerme);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FermeDTO>> getAllFermes() {
        List<FermeDTO> fermes = fermeService.getAllFermes();
        return ResponseEntity.ok(fermes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FermeDTO> getFermeById(@PathVariable int id) {
        FermeDTO ferme = fermeService.getFermeById(id);
        return ResponseEntity.ok(ferme);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<FermeDTO> DeleteById(@PathVariable int id) {
        FermeDTO ferme = fermeService.delete(id);
        return ResponseEntity.ok(ferme);
    }
}
