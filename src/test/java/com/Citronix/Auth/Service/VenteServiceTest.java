package com.Citronix.Auth.Service;

import com.Citronix.Auth.Entity.Recolte;
import com.Citronix.Auth.Entity.Vente;
import com.Citronix.Auth.dto.VenteDTO;
import com.Citronix.Auth.mapper.VenteMapper;
import com.Citronix.Auth.repository.RecolteRepository;
import com.Citronix.Auth.repository.VenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VenteServiceTest {

    @Mock
    private VenteRepository venteRepository;

    @Mock
    private RecolteRepository recolteRepository;

    @Mock
    private VenteMapper venteMapper;

    @InjectMocks
    private VenteService venteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createVente_shouldCreateVente_whenValidDataProvided() {
        // Arrange
        VenteDTO venteDTO = new VenteDTO();
        venteDTO.setRecolteId(1L);
        venteDTO.setDateVente(LocalDate.now());
        venteDTO.setPrixUnitaire(10.0);
        venteDTO.setClient("John Doe");

        Recolte recolte = new Recolte();
        recolte.setId(1);

        Vente vente = new Vente();
        vente.setDateVente(venteDTO.getDateVente());
        vente.setPrixUnitaire(venteDTO.getPrixUnitaire());
        vente.setClient(venteDTO.getClient());
        vente.setRecolte(recolte);

        when(recolteRepository.findById(1)).thenReturn(Optional.of(recolte));
        when(venteRepository.findByRecolte(recolte)).thenReturn(Optional.empty());
        when(venteMapper.toEntity(venteDTO)).thenReturn(vente);
        when(venteRepository.save(any(Vente.class))).thenReturn(vente);
        when(venteMapper.toDto(vente)).thenReturn(venteDTO);

        // Act
        ResponseEntity<?> response = venteService.createVente(venteDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(venteDTO, response.getBody());
    }


    @Test
    void createVente_shouldReturnConflict_whenVenteAlreadyExists() {
        // Arrange
        VenteDTO venteDTO = new VenteDTO();
        venteDTO.setRecolteId(1L);

        Recolte recolte = new Recolte();
        recolte.setId(1);

        Vente existingVente = new Vente();
        existingVente.setId(1L);

        when(recolteRepository.findById(1)).thenReturn(Optional.of(recolte));
        when(venteRepository.findByRecolte(recolte)).thenReturn(Optional.of(existingVente));

        // Act
        ResponseEntity<?> response = venteService.createVente(venteDTO);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("A Vente already exists for this Recolte", response.getBody());
    }
}
