package com.Citronix.Auth.repository;

import com.Citronix.Auth.Entity.Champ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampRepository extends JpaRepository<Champ, Integer> {

}
