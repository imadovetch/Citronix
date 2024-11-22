package com.Citronix.Auth.repository;

import com.Citronix.Auth.Entity.Recolte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RecolteRepository extends JpaRepository<Recolte, Integer> {
    @Query("SELECT r FROM Recolte r WHERE r.champ.id = :champId ORDER BY r.dateRecolte DESC")
    Optional<Recolte> findLatestRecolteByChampId(@Param("champId") Long champId);
}
