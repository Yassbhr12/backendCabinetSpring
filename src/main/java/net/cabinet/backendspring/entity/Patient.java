package net.cabinet.backendspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.cabinet.backendspring.helper.enums.Sexe;
import net.cabinet.backendspring.helper.enums.TypeMutuelle;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cin;

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    private String numTel;

    @Enumerated(EnumType.STRING)
    private TypeMutuelle typeMutuelle;

    @OneToOne(mappedBy = "patient",cascade = CascadeType.ALL)
    private DossierMedical dossierMedical;

    @OneToMany(mappedBy = "patient")
    private List<RendezVous> rendezVousList;

    @OneToMany(mappedBy = "patient")
    private List<Consultation> consultations;

    @OneToMany(mappedBy = "patient" , cascade = CascadeType.ALL)
    private List<Facture> factures;

    public Patient() {
    }

    public Patient(String cin, String nom, String prenom, LocalDate dateNaissance, Sexe sexe, String numTel, TypeMutuelle typeMutuelle) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.numTel = numTel;
        this.typeMutuelle = typeMutuelle;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", cin='" + cin + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", sexe=" + sexe +
                ", numTel='" + numTel + '\'' +
                ", typeMutuelle=" + typeMutuelle +
                '}';
    }
}
