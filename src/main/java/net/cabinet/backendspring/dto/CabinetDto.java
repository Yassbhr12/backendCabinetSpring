package net.cabinet.backendspring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CabinetDto {

    private Long id;

    private String nom ;

    private String specialite;

    private String adresse;

    private String numTel;

    private Boolean actif;

    private String logo;

    public CabinetDto() {
    }

    public CabinetDto(String nom, String specialite, String adresse, String numTel, Boolean actif, String logo) {
        this.nom = nom;
        this.specialite = specialite;
        this.adresse = adresse;
        this.numTel = numTel;
        this.actif = actif;
        this.logo = logo;
    }
}
