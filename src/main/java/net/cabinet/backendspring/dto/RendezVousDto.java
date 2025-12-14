package net.cabinet.backendspring.dto;


import lombok.Getter;
import lombok.Setter;
import net.cabinet.backendspring.helper.enums.Statut;
import net.cabinet.backendspring.helper.enums.TypeConsultation;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class RendezVousDto {

    private Long id;

    private LocalDate dateRdv;

    private LocalTime heureRdv;

    private String motif;

    private Statut statut;

    private String notes;

    private Long PatientId;
    private String cin;
    private String patientNom;

    private Long MedecinId;
    private String medecinNom;
    private String medecinPrenom;

    private Long consultationId;
    private TypeConsultation typeConsultation;

    public RendezVousDto() {
    }

    public RendezVousDto(LocalDate dateRdv, LocalTime heureRdv, String motif, Statut statut, String notes, Long patientId, String cin, String patientNom, Long medecinId, String medecinNom, String medecinPrenom, Long consultationId, TypeConsultation typeConsultation) {
        this.dateRdv = dateRdv;
        this.heureRdv = heureRdv;
        this.motif = motif;
        this.statut = statut;
        this.notes = notes;
        PatientId = patientId;
        this.cin = cin;
        this.patientNom = patientNom;
        MedecinId = medecinId;
        this.medecinNom = medecinNom;
        this.medecinPrenom = medecinPrenom;
        this.consultationId = consultationId;
        this.typeConsultation = typeConsultation;
    }
}
