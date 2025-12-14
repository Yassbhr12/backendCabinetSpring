//package net.cabinet.backendspring.entity;
//
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.List;
//
//@Setter
//@Getter
//@Entity
//@Table(name = "medecin")
//public class Medecin {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String nom;
//
//    private String prenom;
//
//    private String numTel;
//
//    private String signature;
//
//
//
//    public Medecin() {
//    }
//
//    public Medecin(String nom, String prenom, String numTel, String signature) {
//        this.nom = nom;
//        this.prenom = prenom;
//        this.numTel = numTel;
//        this.signature = signature;
//    }
//
//    @Override
//    public String toString() {
//        return "Medecin{" +
//                "id=" + id +
//                ", nom='" + nom + '\'' +
//                ", prenom='" + prenom + '\'' +
//                ", numTel='" + numTel + '\'' +
//                ", signature='" + signature + '\'' +
//                '}';
//    }
//}
