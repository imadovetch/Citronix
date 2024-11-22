package com.Citronix.Auth.Controller;

import com.Citronix.Auth.Service.VenteInterface;
import com.Citronix.Auth.Service.VenteService;
import com.Citronix.Auth.dto.VenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventes")
public class VenteController {

    @Autowired
    private VenteInterface venteService;

    @PostMapping
    public ResponseEntity<?> createVente(@RequestBody VenteDTO venteDTO) {
        return venteService.createVente(venteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVente(@PathVariable Long id, @RequestBody VenteDTO venteDTO) {
        return venteService.updateVente(id, venteDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVenteById(@PathVariable Long id) {
        return venteService.getVenteById(id);
    }
    @GetMapping("/details/{id}")
    public ResponseEntity<?> getVentedetailsById(@PathVariable Long id) {
        return venteService.getdetailsById(id);
    }

    @GetMapping
    public ResponseEntity<?> getAllVentes() {
        return venteService.getAllVentes();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVente(@PathVariable Long id) {
        return venteService.deleteVente(id);
    }
}
