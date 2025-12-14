package net.cabinet.backendspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "dossier_medical")
public class DossierMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDossier;

    private String antMedicaux;

    private String antChirug ;

    private String allergies;

    private String traitement;

    private String habitudes ;

    @OneToMany(mappedBy = "dossierMedical")
    private List<Consultation> historiqueConsultations;

    private String documentsMedicaux;

    private LocalDate dateCreation = LocalDate.now();

    @OneToOne
    @JoinColumn(name = "patient_id" ,unique = true)
    private Patient patient;

    public DossierMedical() {
    }

    public DossierMedical(String antMedicaux, String antChirug, String allergies, String traitement, String habitudes, List<Consultation> historiqueConsultations, String documentsMedicaux, LocalDate dateCreation) {
        this.antMedicaux = antMedicaux;
        this.antChirug = antChirug;
        this.allergies = allergies;
        this.traitement = traitement;
        this.habitudes = habitudes;
        this.historiqueConsultations = historiqueConsultations;
        this.documentsMedicaux = documentsMedicaux;
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "DossierMedical{" +
                "idDossier=" + idDossier +
                ", antMedicaux='" + antMedicaux + '\'' +
                ", antChirug='" + antChirug + '\'' +
                ", allergies='" + allergies + '\'' +
                ", traitement='" + traitement + '\'' +
                ", habitudes='" + habitudes + '\'' +
                ", historiqueConsultations='" + historiqueConsultations + '\'' +
                ", documentsMedicaux='" + documentsMedicaux + '\'' +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
