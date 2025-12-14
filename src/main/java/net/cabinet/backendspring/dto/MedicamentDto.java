package net.cabinet.backendspring.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MedicamentDto {

    private Long id;
    private String nom;
    private String forme;
    private String dosage;
    private List<String> indication;
    private List<String> contrIndication;
    private Boolean actif = true;

    public MedicamentDto() {
    }

    public MedicamentDto(String nom, String forme, String dosage, List<String> indication, List<String> contrIndication, Boolean actif) {
        this.nom = nom;
        this.forme = forme;
        this.dosage = dosage;
        this.indication = indication;
        this.contrIndication = contrIndication;
        this.actif = actif;
    }
}
