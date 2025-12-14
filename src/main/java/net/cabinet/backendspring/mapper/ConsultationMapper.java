package net.cabinet.backendspring.mapper;


import net.cabinet.backendspring.dto.ConsultationDto;
import net.cabinet.backendspring.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultationMapper {

    public ConsultationDto toDto(Consultation consultation){
        ConsultationDto dto = new ConsultationDto();

        dto.setIdConsultation(consultation.getIdConsultation());
        dto.setTypeConsultation(consultation.getTypeConsultation());
        dto.setDateConsultation(consultation.getDateConsultation());
        dto.setExamenClinique(consultation.getExamenClinique());
        dto.setExamenSupplementaire(consultation.getExamenSupplementaire());
        dto.setDiagnostic(consultation.getDiagnostic());
        dto.setTraitement(consultation.getTraitement());
        dto.setObservations(consultation.getObservations());

        dto.setRendezVousId(consultation.getRendezVous().getIdRendezVous());
        dto.setDateRdv(consultation.getRendezVous().getDateRdv());
        dto.setHeureRdv(consultation.getRendezVous().getHeureRdv());

        dto.setPatientId(consultation.getPatient().getId());
        dto.setCin(consultation.getPatient().getCin());
        dto.setPatientNom(consultation.getPatient().getNom());

        dto.setMedecinId(consultation.getMedecin().getId());
        dto.setMedecinNom(consultation.getMedecin().getNom());

        dto.setDossierMedicalId(consultation.getDossierMedical().getIdDossier());


        return dto;

    }

    public List<ConsultationDto> toDtoList(List<Consultation> consultations){
        return consultations.stream().map(this::toDto).toList();

    }

    public Consultation toEntity(ConsultationDto dto , RendezVous rendezVous , Patient patient , Utilisateur medecin ){
        Consultation consultation = new Consultation();

        consultation.setIdConsultation(dto.getIdConsultation());
        consultation.setTypeConsultation(dto.getTypeConsultation());
        consultation.setDateConsultation(dto.getDateConsultation());
        consultation.setExamenClinique(dto.getExamenClinique());
        consultation.setExamenSupplementaire(dto.getExamenSupplementaire());
        consultation.setDiagnostic(dto.getDiagnostic());
        consultation.setTraitement(dto.getTraitement());
        consultation.setObservations(dto.getObservations());

        consultation.setRendezVous(rendezVous);

        consultation.setPatient(patient);

        consultation.setMedecin(medecin);

//        consultation.setDossierMedical(dossierMedical);

        return consultation;
    }

    public void updateConsultation(ConsultationDto dto, Consultation consultation){

        consultation.setTypeConsultation(dto.getTypeConsultation());
        consultation.setDateConsultation(dto.getDateConsultation());
        consultation.setExamenClinique(dto.getExamenClinique());
        consultation.setExamenSupplementaire(dto.getExamenSupplementaire());
        consultation.setDiagnostic(dto.getDiagnostic());
        consultation.setTraitement(dto.getTraitement());
        consultation.setObservations(dto.getObservations());
    }
}
