package com.Citronix.Auth.Controller;

import com.Citronix.Auth.Service.RecolteInterface;
import com.Citronix.Auth.Service.RecolteService;
import com.Citronix.Auth.dto.RecolteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recoltes")
public class RecolteController {

    private final RecolteInterface recolteService;

    @Autowired
    public RecolteController(RecolteService recolteService) {
        this.recolteService = recolteService;
    }

    @PostMapping
    public ResponseEntity<?> addRecolte(@RequestBody RecolteDTO recolteDTO) {
        return recolteService.createRecolte(recolteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecolte(@PathVariable int id, @RequestBody RecolteDTO recolteDTO) {
        return recolteService.updateRecolte(id, recolteDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllRecoltes() {
        return recolteService.getAllRecoltes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecolteById(@PathVariable int id) {
        return recolteService.getRecolteById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecolte(@PathVariable int id) {
        return recolteService.deleteRecolte(id);
    }
}
