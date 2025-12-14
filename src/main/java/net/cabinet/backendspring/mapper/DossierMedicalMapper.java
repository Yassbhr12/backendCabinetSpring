package net.cabinet.backendspring.mapper;

import net.cabinet.backendspring.dto.DossierMedicalDto;
import net.cabinet.backendspring.entity.DossierMedical;
import net.cabinet.backendspring.entity.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DossierMedicalMapper {

    public DossierMedicalDto toDto(DossierMedical dossierMedical){
        DossierMedicalDto dto = new DossierMedicalDto();

        dto.setId(dossierMedical.getIdDossier());
        dto.setAntMedicaux(dossierMedical.getAntMedicaux());
        dto.setAntChirug(dossierMedical.getAntChirug());
        dto.setAllergies(dossierMedical.getAllergies());
        dto.setTraitement(dossierMedical.getTraitement());
        dto.setHabitudes(dossierMedical.getHabitudes());
        dto.setDocumentsMedicaux(dossierMedical.getDocumentsMedicaux());

        dto.setPatientId(dossierMedical.getPatient().getId());
        dto.setPatienCin(dossierMedical.getPatient().getCin());
        dto.setPatienNom(dossierMedical.getPatient().getNom());

        return dto;
    }

    public List<DossierMedicalDto> toDtoList(List<DossierMedical> dossierMedicals){
        return dossierMedicals.stream().map(this::toDto).toList();
    }

    public DossierMedical toEntity(DossierMedicalDto dto , Patient patient){
        DossierMedical dossierMedical = new DossierMedical();

        dossierMedical.setIdDossier(dto.getId());
        dossierMedical.setAntMedicaux(dto.getAntMedicaux());
        dossierMedical.setAntChirug(dto.getAntChirug());
        dossierMedical.setAllergies(dto.getAllergies());
        dossierMedical.setTraitement(dto.getTraitement());
        dossierMedical.setHabitudes(dto.getHabitudes());
        dossierMedical.setDocumentsMedicaux(dto.getDocumentsMedicaux());
        dossierMedical.setDateCreation(LocalDate.now());
        dossierMedical.setPatient(patient);

        return dossierMedical;
    }

    public void updateEntity(DossierMedical dossierMedical , DossierMedicalDto dto){

        dossierMedical.setAntMedicaux(dto.getAntMedicaux());
        dossierMedical.setAntChirug(dto.getAntChirug());
        dossierMedical.setAllergies(dto.getAllergies());
        dossierMedical.setTraitement(dto.getTraitement());
        dossierMedical.setHabitudes(dto.getHabitudes());
        dossierMedical.setDocumentsMedicaux(dto.getDocumentsMedicaux());
//        dossierMedical.setPatient(patient);


    }

}
