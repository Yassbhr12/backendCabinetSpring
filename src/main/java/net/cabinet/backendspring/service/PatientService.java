package net.cabinet.backendspring.service;

import net.cabinet.backendspring.dto.PatientDto;
import net.cabinet.backendspring.entity.Patient;
import net.cabinet.backendspring.helper.enums.Sexe;
import net.cabinet.backendspring.mapper.PatientMapper;
import net.cabinet.backendspring.repository.DossierMedicalRepo;
import net.cabinet.backendspring.repository.PatientRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepo patientRepo;
    private final DossierMedicalRepo dossierMedicalRepo;
    private final PatientMapper mapper;

    public PatientService(PatientRepo patientRepo, PatientMapper mapper , DossierMedicalRepo dossierMedicalRepo) {
        this.patientRepo = patientRepo;
        this.mapper = mapper;
        this.dossierMedicalRepo = dossierMedicalRepo;
    }

    @Transactional
    public PatientDto createPatient(PatientDto dto ){
        Patient patient = mapper.toEntity(dto);
        Patient saved = patientRepo.save(patient);
        return mapper.toDto(saved);
    }

    @Transactional
    public PatientDto updatePatient(PatientDto dto , Long id){
        Patient patient = patientRepo.findById(id).orElseThrow(()-> new RuntimeException("Patient Not Found"));
        mapper.updateEntity(patient,dto);
        Patient updated = patientRepo.save(patient);
        return mapper.toDto(updated);
    }

    public PatientDto getPatientById(Long id){
        return patientRepo.findById(id).map(mapper::toDto).orElseThrow(()-> new RuntimeException("Patient Not Found"));
    }

    public PatientDto getPatientByCin(String cin){
        return patientRepo.findPatientByCin(cin).map(mapper::toDto).orElseThrow(()-> new RuntimeException("Patient Not Found"));
    }

    public List<PatientDto> getPatientsBySexe(String sexe){
        return patientRepo.findPatientBySexe(Sexe.valueOf(sexe)).map(mapper::toDtoList).orElseThrow(()-> new RuntimeException("Patients Not Found"));
    }

    public List<PatientDto> getPatientsByNom(String nom){
        return patientRepo.findPatientByNom(nom).map(mapper::toDtoList).orElseThrow(()-> new RuntimeException("Patients Not Found"));
    }

//    public DossierMedicalDto getDossierMedicalByIdPatient(Long id){
//        return dossierMedicalRepo.findDossierMedicalByPatient_Id(id).map()
//    }

    public List<PatientDto> getAllPatients(){
        return mapper.toDtoList(patientRepo.findAll());

    }

    @Transactional
    public void deletePatientById(Long id){
        Patient patient = patientRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Patient Not Found"));
        patientRepo.delete(patient);
    }

}
