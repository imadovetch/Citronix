package com.Citronix.Auth.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @JsonBackReference // Avoid recursive serialization
    private Ferme ferme;

    @OneToMany(mappedBy = "champ", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Allow serialization of the list of arbres
    private List<Arbre> arbres;
}
