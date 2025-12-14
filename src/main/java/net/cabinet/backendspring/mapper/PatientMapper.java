package net.cabinet.backendspring.mapper;


import net.cabinet.backendspring.dto.PatientDto;
import net.cabinet.backendspring.entity.DossierMedical;
import net.cabinet.backendspring.entity.Patient;
import net.cabinet.backendspring.mapper.DossierMedicalMapper.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientMapper {

    public PatientDto toDto(Patient patient){
        PatientDto dto = new PatientDto();

        dto.setId(patient.getId());
        dto.setCin(patient.getCin());
        dto.setNom(patient.getNom());
        dto.setPrenom(patient.getPrenom());
        dto.setDateNaissance(patient.getDateNaissance());
        dto.setSexe(patient.getSexe());
        dto.setNumTel(patient.getNumTel());
        dto.setTypeMutuelle(patient.getTypeMutuelle());
        if(patient.getDossierMedical()!=null){
            dto.setDossierMedicalId(patient.getDossierMedical().getIdDossier());
        }


        return dto;
    }

    public List<PatientDto> toDtoList(List<Patient> patients){
        return patients.stream()
                .map(this::toDto)
                .toList();
    }

    public Patient toEntity(PatientDto dto){
        Patient patient = new Patient();

        patient.setId(dto.getId());
        patient.setCin(dto.getCin());
        patient.setNom(dto.getNom());
        patient.setPrenom(dto.getPrenom());
        patient.setDateNaissance(dto.getDateNaissance());
        patient.setSexe(dto.getSexe());
        patient.setNumTel(dto.getNumTel());
        patient.setTypeMutuelle(dto.getTypeMutuelle());
//        patient.setDossierMedical(dossierMedical);

        return patient;
    }

    public void updateEntity(Patient patient , PatientDto dto ){

        patient.setCin(dto.getCin());
        patient.setNom(dto.getNom());
        patient.setPrenom(dto.getPrenom());
        patient.setDateNaissance(dto.getDateNaissance());
        patient.setSexe(dto.getSexe());
        patient.setNumTel(dto.getNumTel());
        patient.setTypeMutuelle(dto.getTypeMutuelle());
//        patient.setDossierMedical(dossierMedical);
    }
}
