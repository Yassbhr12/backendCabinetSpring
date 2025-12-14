package net.cabinet.backendspring.service;


import net.cabinet.backendspring.dto.ConsultationDto;
import net.cabinet.backendspring.entity.Consultation;
import net.cabinet.backendspring.entity.Patient;
import net.cabinet.backendspring.entity.RendezVous;
import net.cabinet.backendspring.entity.Utilisateur;
import net.cabinet.backendspring.mapper.ConsultationMapper;
import net.cabinet.backendspring.repository.ConsultationRepo;
import net.cabinet.backendspring.repository.PatientRepo;
import net.cabinet.backendspring.repository.RendezVousRepo;
import net.cabinet.backendspring.repository.UtilisateurRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultationService {

    private final ConsultationRepo consultationRepo;
    private final PatientRepo patientRepo;
    private final UtilisateurRepo utilisateurRepo;
    private final RendezVousRepo rendezVousRepo;
    private final ConsultationMapper mapper;

    public ConsultationService(ConsultationRepo consultationRepo, PatientRepo patientRepo, UtilisateurRepo utilisateurRepo, RendezVousRepo rendezVousRepo, ConsultationMapper mapper) {
        this.consultationRepo = consultationRepo;
        this.patientRepo = patientRepo;
        this.utilisateurRepo = utilisateurRepo;
        this.rendezVousRepo = rendezVousRepo;
        this.mapper = mapper;
    }

    @Transactional
    public ConsultationDto createConsultation(ConsultationDto consultationDto ){
        Patient patient = patientRepo.findById(consultationDto.getPatientId()).orElseThrow(()->new RuntimeException("Patient Not Found"));
        Utilisateur medecin = utilisateurRepo.findById(consultationDto.getMedecinId()).orElseThrow(()->new RuntimeException("Medecin Not Found"));
        RendezVous rendezVous = rendezVousRepo.findById(consultationDto.getRendezVousId()).orElseThrow(()->new RuntimeException("Rendez-vous Not Found"));

        Consultation consultation = mapper.toEntity(consultationDto,rendezVous,patient,medecin);

        Consultation saved = consultationRepo.save(consultation);

        return mapper.toDto(saved);
    }

    @Transactional
    public ConsultationDto updateConsultationById(ConsultationDto dto, Long id){

        Consultation consultation = consultationRepo.findById(id).orElseThrow(()->new RuntimeException("Consultation Not Found"));

        mapper.updateConsultation(dto , consultation);

        Consultation saved = consultationRepo.save(consultation);

        return mapper.toDto(saved);

    }

    public ConsultationDto getConsultationById(Long id){
        return mapper.toDto(consultationRepo.findById(id).orElseThrow(()->new RuntimeException("Consultation Not Found")));
    }

    public List<ConsultationDto> getAllConsultations(){
        return mapper.toDtoList(consultationRepo.findAll());
    }

    public List<ConsultationDto> getConsultationsByPatientId(Long patientId){
        return consultationRepo.findConsultationByPatient_Id(patientId).map(mapper::toDtoList).orElseThrow(()->new RuntimeException("Consultation Not Found"));
    }

    public List<ConsultationDto> getConsultationByMedecinId(Long medecinId){
        return consultationRepo.findConsultationByMedecin_Id(medecinId).map(mapper::toDtoList).orElseThrow(()->new RuntimeException("Consultation Not Found"));
    }

    @Transactional
    public void deleteConsultationById(Long id){
        Consultation consultation = consultationRepo.findById(id).orElseThrow(()->new RuntimeException("Consultation Not Found"));
        consultationRepo.delete(consultation);
    }


}
