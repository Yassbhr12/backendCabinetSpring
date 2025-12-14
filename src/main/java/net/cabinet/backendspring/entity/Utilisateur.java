package net.cabinet.backendspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.cabinet.backendspring.helper.enums.Role;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String login;

    private String pwd;

    private String nom;

    private String prenom;

    private String numTel;

    private String signature;

    private String validationCode;

    private LocalDateTime validationCodeExpiration;

    @Column(name = "actif")
    private Boolean actif = true;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "cabinet_id")
    private Cabinet cabinet;

    @OneToMany(mappedBy = "medecin")
    private List<RendezVous> rendezVousList;

    @OneToMany(mappedBy = "medecin")
    private List<Consultation> consultations;

    public Utilisateur() {
    }

    public Utilisateur(String login, String pwd, String nom, String prenom, String numTel, String signature, String validationCode, LocalDateTime validationCodeExpiration, Boolean actif, Role role, Cabinet cabinet, List<RendezVous> rendezVousList, List<Consultation> consultations) {
        this.login = login;
        this.pwd = pwd;
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
        this.signature = signature;
        this.validationCode = validationCode;
        this.validationCodeExpiration = validationCodeExpiration;
        this.actif = actif;
        this.role = role;
        this.cabinet = cabinet;
        this.rendezVousList = rendezVousList;
        this.consultations = consultations;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pwd='" + pwd + '\'' +
                ", validationCode='" + validationCode + '\'' +
                ", validationCodeExpiration=" + validationCodeExpiration +
                ", role=" + role +
                '}';
    }
}
