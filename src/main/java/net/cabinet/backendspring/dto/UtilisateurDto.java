package net.cabinet.backendspring.dto;


import lombok.Getter;
import lombok.Setter;
import net.cabinet.backendspring.helper.enums.Role;

@Setter
@Getter
public class UtilisateurDto {

    private Long id;

    private String login;

    private Boolean actif;

    private Role role;

    private String nom;

    private String prenom;

    private String numTel;

    private String signature;


    private Long cabinetId;
    private String nomCabinet;


    public UtilisateurDto() {
    }

    public UtilisateurDto(String login, Boolean actif, Role role, String nom, String prenom, String numTel, String signature, Long cabinetId, String nomCabinet) {
        this.login = login;
        this.actif = actif;
        this.role = role;
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
        this.signature = signature;
        this.cabinetId = cabinetId;
        this.nomCabinet = nomCabinet;

    }
}
