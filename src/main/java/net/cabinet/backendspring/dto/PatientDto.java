package net.cabinet.backendspring.dto;


import lombok.Getter;
import lombok.Setter;
import net.cabinet.backendspring.helper.enums.Sexe;
import net.cabinet.backendspring.helper.enums.TypeMutuelle;

import java.time.LocalDate;

@Getter
@Setter
public class PatientDto {

    private Long id;

    private String cin;

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    private Sexe sexe;

    private String numTel;

    private TypeMutuelle typeMutuelle;

    private Long dossierMedicalId;

    public PatientDto() {
    }

    public PatientDto(String cin, String nom, String prenom, LocalDate dateNaissance, Sexe sexe, String numTel, TypeMutuelle typeMutuelle, Long dossierMedicalId) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.numTel = numTel;
        this.typeMutuelle = typeMutuelle;
        this.dossierMedicalId = dossierMedicalId;

    }
}
