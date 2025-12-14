package net.cabinet.backendspring.service;

import net.cabinet.backendspring.dto.DossierMedicalDto;
import net.cabinet.backendspring.entity.DossierMedical;
import net.cabinet.backendspring.entity.Patient;
import net.cabinet.backendspring.mapper.DossierMedicalMapper;
import net.cabinet.backendspring.repository.DossierMedicalRepo;
import net.cabinet.backendspring.repository.PatientRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DossierMedicalService {
    private final DossierMedicalRepo dossierMedicalRepo;
    private final DossierMedicalMapper mapper;
    private final PatientRepo patientRepo;

    public DossierMedicalService(DossierMedicalRepo dossierMedicalRepo, DossierMedicalMapper mapper, PatientRepo patientRepo) {
        this.dossierMedicalRepo = dossierMedicalRepo;
        this.mapper = mapper;
        this.patientRepo = patientRepo;
    }

    @Transactional
    public DossierMedicalDto createDossierMedical(DossierMedicalDto dto){
        Patient patient = patientRepo.findById(dto.getPatientId()).orElseThrow(()-> new RuntimeException("Patient Not Found"));
        DossierMedical dossierMedical = mapper.toEntity(dto , patient);
        DossierMedical saved = dossierMedicalRepo.save(dossierMedical);
        return mapper.toDto(saved);
    }

    @Transactional
    public DossierMedicalDto updateDossierMedical(DossierMedicalDto dto , Long id){
        DossierMedical dossierMedical = dossierMedicalRepo.findById(id).orElseThrow(()-> new RuntimeException("Dossier Medical Not Found"));
        mapper.updateEntity(dossierMedical , dto);
        DossierMedical saved = dossierMedicalRepo.save(dossierMedical);

        return mapper.toDto(saved);
    }

    public DossierMedicalDto getDossierMedicalById(Long id){
        DossierMedical dossierMedical = dossierMedicalRepo.findById(id).orElseThrow(()-> new RuntimeException("Dossier Medical Not Found"));
        return mapper.toDto(dossierMedical);

    }

    public List<DossierMedicalDto> getAllDossierMedicals(){
        return mapper.toDtoList(dossierMedicalRepo.findAll());
    }

    public DossierMedicalDto getDossierMedicalByPatientId(Long patientId){
        return dossierMedicalRepo.findDossierMedicalByPatient_Id(patientId).map(mapper::toDto).orElseThrow(()-> new RuntimeException("Dossier Medical Not Found"));
    }

    @Transactional
    public void deleteDossierMedicalById(Long id){
        DossierMedical dossierMedical =  dossierMedicalRepo.findById(id).orElseThrow(()-> new RuntimeException("Dossier Medical Not Found"));
        dossierMedicalRepo.delete(dossierMedical);
    }

}
