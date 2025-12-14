package net.cabinet.backendspring.mapper;


import net.cabinet.backendspring.dto.RendezVousDto;

import net.cabinet.backendspring.entity.Patient;
import net.cabinet.backendspring.entity.RendezVous;
import net.cabinet.backendspring.entity.Utilisateur;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class RendezVousMapper {

    public RendezVousDto toDto(RendezVous rendezVous){
        RendezVousDto dto =  new RendezVousDto();

        dto.setId(rendezVous.getIdRendezVous());
        dto.setDateRdv(rendezVous.getDateRdv());
        dto.setHeureRdv(rendezVous.getHeureRdv());
        dto.setMotif(rendezVous.getMotif());
        dto.setStatut(rendezVous.getStatut());
        dto.setNotes(rendezVous.getNotes());

        dto.setPatientId(rendezVous.getPatient().getId());
        dto.setCin(rendezVous.getPatient().getCin());
        dto.setPatientNom(rendezVous.getPatient().getNom());

        dto.setMedecinId(rendezVous.getMedecin().getId());
        dto.setMedecinNom(rendezVous.getMedecin().getNom());
        dto.setMedecinPrenom(rendezVous.getMedecin().getPrenom());

        dto.setConsultationId(rendezVous.getConsultation().getIdConsultation());
        dto.setTypeConsultation(rendezVous.getConsultation().getTypeConsultation());

        return dto;
    }

    public RendezVous toEntity(RendezVousDto dto, Patient patient , Utilisateur medecin){
        RendezVous rendezVous = new RendezVous();

        rendezVous.setIdRendezVous(dto.getId());
        rendezVous.setDateRdv(dto.getDateRdv());
        rendezVous.setHeureRdv(dto.getHeureRdv());
        rendezVous.setMotif(dto.getMotif());
        rendezVous.setStatut(dto.getStatut());
        rendezVous.setNotes(dto.getNotes());

        rendezVous.setPatient(patient);

        rendezVous.setMedecin(medecin);

        return rendezVous;
    }

    public List<RendezVousDto> toDtoList(List<RendezVous> rendezVousList){
        return rendezVousList.stream().map(this::toDto).toList();
    }

    public void updateEntity(RendezVous rendezVous , RendezVousDto dto){

        rendezVous.setDateRdv(dto.getDateRdv());
        rendezVous.setHeureRdv(dto.getHeureRdv());
        rendezVous.setMotif(dto.getMotif());
        rendezVous.setStatut(dto.getStatut());
        rendezVous.setNotes(dto.getNotes());

//        rendezVous.setPatient(patient);
//
//        rendezVous.setMedecin(medecin);
    }
}
