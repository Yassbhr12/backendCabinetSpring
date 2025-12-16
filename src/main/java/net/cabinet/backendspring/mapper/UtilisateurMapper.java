package net.cabinet.backendspring.mapper;


import net.cabinet.backendspring.dto.UtilisateurDto;
import net.cabinet.backendspring.entity.Cabinet;
//import net.cabinet.backendspring.entity.Medecin;
//import net.cabinet.backendspring.entity.Secretaire;
import net.cabinet.backendspring.entity.Utilisateur;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UtilisateurMapper {

    public UtilisateurDto toDto(Utilisateur utilisateur){
        UtilisateurDto dto = new UtilisateurDto();

        dto.setId(utilisateur.getId());
        dto.setLogin(utilisateur.getLogin());
        dto.setActif(utilisateur.getActif());
        dto.setRole(utilisateur.getRole());
        dto.setNom(utilisateur.getNom());
        dto.setPrenom(utilisateur.getPrenom());
        dto.setNumTel(utilisateur.getNumTel());
        dto.setSignature(utilisateur.getSignature());

        if(utilisateur.getCabinet() != null){
            dto.setCabinetId(utilisateur.getCabinet().getId());
            dto.setNomCabinet(utilisateur.getCabinet().getNom());
        }


//        dto.setMedecinId(utilisateur.getMedecin().getId());
//        dto.setMedecinNom(utilisateur.getMedecin().getNom());
//        dto.setMedecinPrenom(utilisateur.getMedecin().getPrenom());
//
//        dto.setSecretaireId(utilisateur.getSecretaire().getId());
//        dto.setSecretaireNom(utilisateur.getSecretaire().getNom());
//        dto.setSecretairePrenom(utilisateur.getSecretaire().getPrenom());

        return dto;
    }

    public List<UtilisateurDto> toDtoList(List<Utilisateur> utilisateurs){
        return utilisateurs.stream().map(this::toDto).toList();
    }

    public Utilisateur toEntity(UtilisateurDto dto, Cabinet cabinet){
//        if(secretaire!= null && medecin!= null){
//            throw new IllegalArgumentException("Un utilisateur ne peut pas être lié à la fois à un medecin et une secretaire ");
//        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getId());
        utilisateur.setLogin(dto.getLogin());
        utilisateur.setNom(dto.getNom());
        utilisateur.setPrenom(dto.getPrenom());
        utilisateur.setNumTel(dto.getNumTel());
        utilisateur.setSignature(dto.getSignature());
        utilisateur.setActif(dto.getActif());
        utilisateur.setRole(dto.getRole());

        utilisateur.setCabinet(cabinet);
//        utilisateur.setMedecin(medecin);
//        utilisateur.setSecretaire(secretaire);

        return utilisateur;
    }

    public void updateEntity(Utilisateur utilisateur , UtilisateurDto dto ){
        utilisateur.setLogin(dto.getLogin());
        utilisateur.setNom(dto.getNom());
        utilisateur.setPrenom(dto.getPrenom());
        utilisateur.setNumTel(dto.getNumTel());
        utilisateur.setSignature(dto.getSignature());
        utilisateur.setActif(dto.getActif());
        utilisateur.setRole(dto.getRole());

//        utilisateur.setCabinet(cabinet);
//        utilisateur.setMedecin(medecin);
//        utilisateur.setSecretaire(secretaire);

    }
}
