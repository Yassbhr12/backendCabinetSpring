//package net.cabinet.backendspring.mapper;
//
//
//import net.cabinet.backendspring.dto.MedecinDto;
//import net.cabinet.backendspring.entity.Medecin;
//
//public class MedecinMapper {
//
//    public MedecinDto toDto(Medecin medecin){
//        MedecinDto dto = new MedecinDto();
//
//        dto.setId(medecin.getId());
//        dto.setNom(medecin.getNom());
//        dto.setPrenom(medecin.getPrenom());
//        dto.setNumTel(medecin.getNumTel());
//        dto.setSignature(medecin.getSignature());
//
//        return dto;
//    }
//
//    public Medecin toEntity(MedecinDto dto){
//        Medecin medecin = new Medecin();
//        medecin.setId(dto.getId());
//        medecin.setNom(dto.getNom());
//        medecin.setPrenom(dto.getPrenom());
//        medecin.setNumTel(dto.getNumTel());
//        medecin.setSignature(dto.getSignature());
//
//        return medecin;
//    }
//
//    public void updateEntity(Medecin medecin , MedecinDto dto){
//
//        medecin.setId(dto.getId());
//        medecin.setNom(dto.getNom());
//        medecin.setPrenom(dto.getPrenom());
//        medecin.setNumTel(dto.getNumTel());
//        medecin.setSignature(dto.getSignature());
//
//    }
//}
