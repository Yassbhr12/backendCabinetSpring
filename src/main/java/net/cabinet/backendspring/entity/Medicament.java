package net.cabinet.backendspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "medicament")
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String forme;
    private String dosage;

    @ElementCollection
    @CollectionTable(name = "indication" , joinColumns = @JoinColumn(name = "medicament_id"))
    private List<String> indication;

    @ElementCollection
    @CollectionTable(name = "contr_indication" , joinColumns = @JoinColumn(name = "medicament_id"))
    private List<String> contrIndication;

    private Boolean actif = true;

    public Medicament() {
    }

    public Medicament(String nom, String forme, String dosage, List<String> indication, List<String> contrIndication, Boolean actif) {
        this.nom = nom;
        this.forme = forme;
        this.dosage = dosage;
        this.indication = indication;
        this.contrIndication = contrIndication;
        this.actif = actif;
    }
}
