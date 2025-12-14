package net.cabinet.backendspring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DossierMedicalDto {

    private Long id;

    private String antMedicaux;

    private String antChirug ;

    private String allergies;

    private String traitement;

    private String habitudes ;

    private String documentsMedicaux;

    private Long patientId;
    private String patienCin;
    private String patienNom;

    public DossierMedicalDto() {
    }

    public DossierMedicalDto(String antMedicaux, String antChirug, String allergies, String traitement, String habitudes, String documentsMedicaux, Long patientId, String cin, String nom) {
        this.antMedicaux = antMedicaux;
        this.antChirug = antChirug;
        this.allergies = allergies;
        this.traitement = traitement;
        this.habitudes = habitudes;
        this.documentsMedicaux = documentsMedicaux;
        this.patientId = patientId;
        this.patienCin = cin;
        this.patienNom = nom;
    }
}
