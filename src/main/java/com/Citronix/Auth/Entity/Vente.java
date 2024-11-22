package com.Citronix.Auth.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ventes")
public class Vente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Date of sale
    @FutureOrPresent
    @Column(nullable = false)
    private LocalDate dateVente;

    // Unit price of the product sold
    @Column(nullable = false)
    private Double prixUnitaire;

    // Client name (could be changed to a Client entity if more client details are needed)
    @Column(nullable = false)
    private String client;

    // Many sales can be associated with one recolte (one-to-many relationship)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recolte_id", nullable = false)
    private Recolte recolte;
}
