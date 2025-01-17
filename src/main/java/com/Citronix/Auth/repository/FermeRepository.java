package com.Citronix.Auth.repository;

import com.Citronix.Auth.Entity.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FermeRepository extends JpaRepository<Ferme, Integer> {

    List<Ferme> findByNomContaining(String nom);

    List<Ferme> findByLocalisation(String localisation);
}
