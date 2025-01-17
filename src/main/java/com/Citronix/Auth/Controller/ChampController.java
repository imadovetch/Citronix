package com.Citronix.Auth.Controller;

import com.Citronix.Auth.Service.ChampInterface;
import com.Citronix.Auth.Service.ChampService;
import com.Citronix.Auth.dto.ChampDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/champs")
public class ChampController {

    private final ChampInterface champService;

    @Autowired
    public ChampController(ChampService champService) {
        this.champService = champService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addChamp(@RequestBody ChampDTO champDTO) {

        return champService.addChamp(champDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> addChamp(@PathVariable("id") Long id) {
        return champService.getbyid(id);
    }

}