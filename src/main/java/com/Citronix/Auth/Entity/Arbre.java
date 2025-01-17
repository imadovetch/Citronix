package com.Citronix.Auth.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference; // import Jackson annotations

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "arbres")
public class Arbre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false)
    private int âge;

    // Date of plantation
    @Column(nullable = false)
    @FutureOrPresent
    private LocalDate datePlantation;

    // Preventing infinite recursion in serialization
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "champ_id", nullable = false)
    @JsonBackReference // This annotation will prevent serialization of the back-reference (prevents infinite recursion)
    private Champ champ;
}
