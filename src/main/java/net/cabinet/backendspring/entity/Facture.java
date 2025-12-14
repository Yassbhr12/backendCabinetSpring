package net.cabinet.backendspring.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.cabinet.backendspring.helper.enums.ModePaiement;
import net.cabinet.backendspring.helper.enums.StatutFacture;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "facture")
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFacture;

    private Float montant;

    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;

    private LocalDate dateFacture;

    @Enumerated(EnumType.STRING)
    private StatutFacture statut;

    @OneToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Facture() {
    }

    public Facture(Float montant, ModePaiement modePaiement, LocalDate dateFacture, StatutFacture statut, Consultation consultation, Patient patient) {
        this.montant = montant;
        this.modePaiement = modePaiement;
        this.dateFacture = dateFacture;
        this.statut = statut;
        this.consultation = consultation;
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "idFacture=" + idFacture +
                ", montant=" + montant +
                ", modePaiement=" + modePaiement +
                ", dateFacture=" + dateFacture +
                ", statut=" + statut +
                ", consultation=" + consultation +
                ", patient=" + patient +
                '}';
    }
}
