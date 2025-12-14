package net.cabinet.backendspring.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.cabinet.backendspring.helper.enums.TypeConsultation;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "consultation")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConsultation;

    @Enumerated(EnumType.STRING)
    private TypeConsultation typeConsultation;

    private LocalDate dateConsultation;

    @ElementCollection
    @CollectionTable(name = "examen_clinique", joinColumns = @JoinColumn(name = "consultation_id"))
    @Column(name = "examen")
    private List<String> examenClinique;

    @ElementCollection
    @CollectionTable(name = "examen_supplementaire", joinColumns = @JoinColumn(name = "consultation_id"))
    @Column(name = "examen")
    private List<String> examenSupplementaire;

    private String diagnostic;

    @ElementCollection
    @CollectionTable(name = "traitement", joinColumns = @JoinColumn(name = "consultation_id"))
    @Column(name = "medicament")
    private List<String> traitement;

    private String observations;

    @OneToOne
    @JoinColumn(name = "rendez_vous_id")
    private RendezVous rendezVous;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Utilisateur medecin;

    @ManyToOne
    @JoinColumn(name = "dossier_medical_id")
    private DossierMedical dossierMedical;

    @OneToOne(mappedBy = "consultation")
    private Facture facture;

    public Consultation() {
    }

    public Consultation(TypeConsultation typeConsultation, LocalDate dateConsultation, List<String> examenClinique, List<String> examenSupplementaire, String diagnostic, List<String> traitement, String observations, RendezVous rendezVous, Patient patient, Utilisateur medecin, DossierMedical dossierMedical, Facture facture) {
        this.typeConsultation = typeConsultation;
        this.dateConsultation = dateConsultation;
        this.examenClinique = examenClinique;
        this.examenSupplementaire = examenSupplementaire;
        this.diagnostic = diagnostic;
        this.traitement = traitement;
        this.observations = observations;
        this.rendezVous = rendezVous;
        this.patient = patient;
        this.medecin = medecin;
        this.dossierMedical = dossierMedical;
        this.facture = facture;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "idConsultation=" + idConsultation +
                ", typeConsultation=" + typeConsultation +
                ", dateConsultation=" + dateConsultation +
                ", examenClinique=" + examenClinique +
                ", examenSupplementaire=" + examenSupplementaire +
                ", diagnostic='" + diagnostic + '\'' +
                ", traitement=" + traitement +
                ", observations='" + observations + '\'' +
                ", rendezVous=" + rendezVous +
                ", patient=" + patient +
                ", medecin=" + medecin +
                ", dossierMedical=" + dossierMedical +
                ", facture=" + facture +
                '}';
    }
}
