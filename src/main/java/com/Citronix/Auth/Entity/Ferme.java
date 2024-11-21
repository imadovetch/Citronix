package com.Citronix.Auth.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fermes")
public class Ferme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String localisation;

    @Column(nullable = false)
    private Double superficie;

    @Column(name = "date_de_creation", nullable = false)
    private Date dateDeCreation;

    @OneToMany(mappedBy = "ferme", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Champ> champs;
}
