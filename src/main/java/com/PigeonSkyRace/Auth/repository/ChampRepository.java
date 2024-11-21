package com.PigeonSkyRace.Auth.repository;

import com.PigeonSkyRace.Auth.Entity.Champ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampRepository extends JpaRepository<Champ, Integer> {
}
