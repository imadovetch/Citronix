package com.Citronix.Auth.repository;

import com.Citronix.Auth.Entity.Recolte;
import com.Citronix.Auth.Entity.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Long> {
    Optional<Vente> findByRecolte(Recolte recolte);
}

