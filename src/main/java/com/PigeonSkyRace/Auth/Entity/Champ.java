package com.PigeonSkyRace.Auth.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "champs")
public class Champ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Double superficie;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ferme_id", nullable = false)
    private Ferme ferme;
}
