package net.cabinet.backendspring.service;


import net.cabinet.backendspring.dto.FactureDto;
import net.cabinet.backendspring.entity.Consultation;
import net.cabinet.backendspring.entity.Facture;
import net.cabinet.backendspring.entity.Patient;
import net.cabinet.backendspring.mapper.FactureMapper;
import net.cabinet.backendspring.repository.ConsultationRepo;
import net.cabinet.backendspring.repository.FactureRepo;
import net.cabinet.backendspring.repository.PatientRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FactureService {
    private final FactureRepo factureRepo;
    private final PatientRepo patientRepo;
    private final ConsultationRepo consultationRepo;
    private final FactureMapper mapper;

    public FactureService(FactureRepo factureRepo, PatientRepo patientRepo, ConsultationRepo consultationRepo, FactureMapper mapper) {
        this.factureRepo = factureRepo;
        this.patientRepo = patientRepo;
        this.consultationRepo = consultationRepo;
        this.mapper = mapper;
    }

    @Transactional
    public FactureDto createFacture(FactureDto dto ){
        Patient patient = patientRepo.findById(dto.getPatientId()).orElseThrow(()-> new RuntimeException("Patient Not Found"));
        Consultation consultation = consultationRepo.findById(dto.getConsultationId()).orElseThrow(()->new RuntimeException("Consultation Not Found"));
        Facture facture = mapper.toEntity(dto,consultation,patient);

        Facture saved = factureRepo.save(facture);

        return mapper.toDto(saved);
    }

    @Transactional
    public FactureDto updateFacture(FactureDto dto , Long id){
        Facture facture = factureRepo.findById(id).orElseThrow(()->new RuntimeException("Facture Not Found"));
        mapper.updateEntity(facture,dto);

        return mapper.toDto(facture);
    }

    public FactureDto getFactureById(Long id){
        Facture facture = factureRepo.findById(id).orElseThrow(()->new RuntimeException("Facture Not Found"));
        return mapper.toDto(facture);
    }

    public List<FactureDto> getFacturesByPatientId(Long patientId){
        return factureRepo.findFactureByPatient_Id(patientId).map(mapper::toDtoList).orElseThrow(()->new RuntimeException("Factures Not Found"));
    }

    public List<FactureDto> getAllFactures(){
        return mapper.toDtoList(factureRepo.findAll());
    }

    @Transactional
    public void deleteFactureById(Long id){
        Facture facture = factureRepo.findById(id).orElseThrow(()->new RuntimeException("Facture Not Found"));
        factureRepo.delete(facture);
    }

}
