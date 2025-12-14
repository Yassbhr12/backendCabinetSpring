package net.cabinet.backendspring.controller;


import net.cabinet.backendspring.dto.*;
import net.cabinet.backendspring.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;
    private final DossierMedicalService dossierMedicalService;
    private final RendezVousService rendezVousService;
    private final FactureService factureService;
    private final ConsultationService consultationService;

    public PatientController(PatientService patientService, DossierMedicalService dossierMedicalService, RendezVousService rendezVousService, FactureService factureService , ConsultationService consultationService) {
        this.patientService = patientService;
        this.dossierMedicalService = dossierMedicalService;
        this.rendezVousService = rendezVousService;
        this.factureService = factureService;
        this.consultationService = consultationService;
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients(){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id){
        PatientDto dto = patientService.getPatientById(id);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/{id}/dossier-medical")
    public ResponseEntity<DossierMedicalDto> getDossierMedicalOfPatient(@PathVariable Long id){
        DossierMedicalDto dossierMedicalDto = dossierMedicalService.getDossierMedicalByPatientId(id);

        return ResponseEntity.status(HttpStatus.OK).body(dossierMedicalDto);
    }

    @GetMapping("/{id}/rendez-vous")
    public ResponseEntity<List<RendezVousDto>> getRendezVousOfPatient(@PathVariable Long id){
        List<RendezVousDto> rendezVousDtos = rendezVousService.getRendezVousByPatientId(id);
        return ResponseEntity.status(HttpStatus.OK).body(rendezVousDtos);
    }

    @GetMapping("/{id}/factures")
    public ResponseEntity<List<FactureDto>> getFacturesOfPatient(@PathVariable Long id){
        List<FactureDto> factureDtos = factureService.getFacturesByPatientId(id);
        return ResponseEntity.status(HttpStatus.OK).body(factureDtos);
    }

    @GetMapping("/{id}/consultations")
    public ResponseEntity<List<ConsultationDto>> getConsultationsOfPatient(@PathVariable Long id){
        List<ConsultationDto> consultationDtos = consultationService.getConsultationsByPatientId(id);
        return ResponseEntity.status(HttpStatus.OK).body(consultationDtos);
    }

    @PostMapping
    public ResponseEntity<PatientDto> creatPatient(@RequestBody PatientDto dto){
        PatientDto patientDto = patientService.createPatient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto dto , @PathVariable Long id){
        PatientDto patientDto = patientService.updatePatient(dto , id);

        return ResponseEntity.status(HttpStatus.CREATED).body(patientDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id){
        patientService.deletePatientById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Le Patient a ete supprime avec succes!");
    }

}
