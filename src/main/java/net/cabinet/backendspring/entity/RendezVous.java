package net.cabinet.backendspring.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.cabinet.backendspring.helper.enums.Statut;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@Entity
@Table(name = "rendez-vous")
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRendezVous;

    private LocalDate dateRdv;

    private LocalTime heureRdv;

    private String motif;

    private LocalDateTime dateCreation = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Statut statut = Statut.EN_ATTENTE;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "patient_id" )
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Utilisateur medecin;

    @OneToOne(mappedBy = "rendezVous")
    private Consultation consultation;

    public RendezVous() {
    }

    public RendezVous(LocalDate dateRdv, LocalTime heureRdv, String motif, LocalDateTime dateCreation, Statut statut, String notes, Patient patient, Utilisateur medecin, Consultation consultation) {
        this.dateRdv = dateRdv;
        this.heureRdv = heureRdv;
        this.motif = motif;
        this.dateCreation = dateCreation;
        this.statut = statut;
        this.notes = notes;
        this.patient = patient;
        this.medecin = medecin;
        this.consultation = consultation;
    }

    @Override
    public String toString() {
        return "RendezVous{" +
                "idRendezVous=" + idRendezVous +
                ", dateRdv=" + dateRdv +
                ", heureRdv='" + heureRdv + '\'' +
                ", motif='" + motif + '\'' +
                ", dateCreation=" + dateCreation +
                ", statut=" + statut +
                ", notes='" + notes + '\'' +
                ", patient=" + patient +
                ", medecin=" + medecin +
                ", consultation=" + consultation +
                '}';
    }
}
