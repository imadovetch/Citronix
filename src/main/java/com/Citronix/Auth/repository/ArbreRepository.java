package com.Citronix.Auth.repository;

import com.Citronix.Auth.Entity.Arbre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArbreRepository extends JpaRepository<Arbre, Integer> {
    List<Arbre> findByChampId(int champId);
    long   countByChampId(int id);
}
