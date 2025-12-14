package net.cabinet.backendspring.dto;


import lombok.Getter;
import lombok.Setter;
import net.cabinet.backendspring.helper.enums.ModePaiement;
import net.cabinet.backendspring.helper.enums.StatutFacture;
import net.cabinet.backendspring.helper.enums.TypeConsultation;

import java.time.LocalDate;

@Getter
@Setter
public class FactureDto {

    private Long id;

    private Float montant;

    private ModePaiement modePaiement;

    private LocalDate dateFacture;

    private StatutFacture statutFacture;

    private Long consultationId;
    private TypeConsultation typeConsultation;
    private String observations;

    private Long patientId;
    private String cin;
    private String nom;

    public FactureDto() {
    }

    public FactureDto(Float montant, ModePaiement modePaiement, LocalDate dateFacture, StatutFacture statutFacture, Long consultationId, TypeConsultation typeConsultation, String observations, Long patientId, String cin, String nom) {
        this.montant = montant;
        this.modePaiement = modePaiement;
        this.dateFacture = dateFacture;
        this.statutFacture = statutFacture;
        this.consultationId = consultationId;
        this.typeConsultation = typeConsultation;
        this.observations = observations;
        this.patientId = patientId;
        this.cin = cin;
        this.nom = nom;
    }
}
