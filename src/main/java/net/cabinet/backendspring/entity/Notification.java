package net.cabinet.backendspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.cabinet.backendspring.helper.enums.TypeNotification;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @Enumerated(EnumType.STRING)
    private TypeNotification type;

    private Boolean lu = false;

    private LocalDateTime dateCreation;

    @ManyToOne
    @JoinColumn(name = "destinataire_id")
    private Utilisateur destinataire;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Notification() {
    }

    public Notification(String message, TypeNotification type, Boolean lu, LocalDateTime dateCreation, Utilisateur destinataire, Patient patient) {
        this.message = message;
        this.type = type;
        this.lu = lu;
        this.dateCreation = dateCreation;
        this.destinataire = destinataire;
        this.patient = patient;
    }
}


