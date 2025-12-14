//package net.cabinet.backendspring.mapper;
//
//
//import net.cabinet.backendspring.dto.SecretaireDto;
//import net.cabinet.backendspring.entity.Secretaire;
//
//public class SecretaireMapper {
//
//    public SecretaireDto toDto(Secretaire secretaire){
//        SecretaireDto dto = new SecretaireDto();
//
//        dto.setId(secretaire.getId());
//        dto.setNom(secretaire.getNom());
//        dto.setPrenom(secretaire.getPrenom());
//        dto.setNumTel(secretaire.getNumTel());
//        dto.setSignature(secretaire.getSignature());
//
//        return dto;
//    }
//
//    public Secretaire toEntity(SecretaireDto dto){
//        Secretaire secretaire = new Secretaire();
//
//        secretaire.setId(dto.getId());
//        secretaire.setNom(dto.getNom());
//        secretaire.setPrenom(dto.getPrenom());
//        secretaire.setNumTel(dto.getNumTel());
//        secretaire.setSignature(dto.getSignature());
//
//        return secretaire;
//
//    }
//}
