package net.cabinet.backendspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cabinet")
public class Cabinet {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String logo;

    private String nom ;

    private String specialite ;

    private String adresse;

    private String numTel;

    @Column(name = "actif")
    private Boolean actif = true;

    @OneToMany(mappedBy = "cabinet" , cascade = CascadeType.ALL)
    private List<Utilisateur> utilisateurs;

    public Cabinet() {
    }

    public Cabinet(String logo, String nom, String specialite, String adresse, String numTel) {
        this.logo = logo;
        this.nom = nom;
        this.specialite = specialite;
        this.adresse = adresse;
        this.numTel = numTel;
    }


    @Override
    public String toString() {
        return "Cabinet{" +
                "id=" + id +
                ", logo='" + logo + '\'' +
                ", nom='" + nom + '\'' +
                ", specialite='" + specialite + '\'' +
                ", adress='" + adresse + '\'' +
                ", numTel='" + numTel + '\'' +
                '}';
    }
}
