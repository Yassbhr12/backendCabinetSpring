package net.cabinet.backendspring.dto;


import lombok.Getter;
import lombok.Setter;
import net.cabinet.backendspring.helper.enums.TypeConsultation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class ConsultationDto {

    private Long idConsultation;

    private TypeConsultation typeConsultation;

    private LocalDate dateConsultation;

    private List<String> examenClinique;

    private List<String> examenSupplementaire;

    private String diagnostic;

    private List<String> traitement;

    private String observations;

    private Long rendezVousId;
    private LocalDate dateRdv;
    private LocalTime heureRdv;

    private Long patientId;
    private String cin;
    private String patientNom;

    private Long medecinId;
    private String medecinNom ;

    private Long dossierMedicalId;

    public ConsultationDto() {
    }

    public ConsultationDto(TypeConsultation typeConsultation, LocalDate dateConsultation, List<String> examenClinique, List<String> examenSupplementaire, String diagnostic, List<String> traitement, String observations, Long rendezVousId, LocalDate dateRdv, LocalTime heureRdv, Long patientId, String cin, String patientNom, Long medecinId, String medecinNom, Long dossierMedicalId) {
        this.typeConsultation = typeConsultation;
        this.dateConsultation = dateConsultation;
        this.examenClinique = examenClinique;
        this.examenSupplementaire = examenSupplementaire;
        this.diagnostic = diagnostic;
        this.traitement = traitement;
        this.observations = observations;
        this.rendezVousId = rendezVousId;
        this.dateRdv = dateRdv;
        this.heureRdv = heureRdv;
        this.patientId = patientId;
        this.cin = cin;
        this.patientNom = patientNom;
        this.medecinId = medecinId;
        this.medecinNom = medecinNom;
        this.dossierMedicalId = dossierMedicalId;
    }
}
