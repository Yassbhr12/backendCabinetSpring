package net.cabinet.backendspring.controller;


import net.cabinet.backendspring.dto.ConsultationDto;
import net.cabinet.backendspring.service.ConsultationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {

    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @GetMapping
    public ResponseEntity<List<ConsultationDto>> getAllConsultations(){
        List<ConsultationDto> consultationDtos = consultationService.getAllConsultations();
        return ResponseEntity.status(HttpStatus.OK).body(consultationDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDto> getConsultationById(@PathVariable Long id){
        ConsultationDto dto = consultationService.getConsultationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/medecin/{medecinId}")
    public ResponseEntity<List<ConsultationDto>> getConsultationsOfMedecin(@PathVariable Long medecinId){
        List<ConsultationDto> consultationDtos = consultationService.getConsultationByMedecinId(medecinId);
        return ResponseEntity.status(HttpStatus.OK).body(consultationDtos);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ConsultationDto>> getConsultationsOfPatient(@PathVariable Long patientId){
        List<ConsultationDto> consultationDtos = consultationService.getConsultationsByPatientId(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(consultationDtos);
    }

    @PostMapping
    public ResponseEntity<ConsultationDto> createConsulation(@RequestBody ConsultationDto dto){
        ConsultationDto consultationDto = consultationService.createConsultation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultationDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultationDto> updateConsultation(@PathVariable Long id , @RequestBody ConsultationDto dto){
        ConsultationDto consultationDto = consultationService.updateConsultationById(dto,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultationDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteConsultation(@PathVariable Long id){
        consultationService.deleteConsultationById(id);
        return ResponseEntity.status(HttpStatus.OK).body("La consultation a ete supprime avec succes");
    }

}
