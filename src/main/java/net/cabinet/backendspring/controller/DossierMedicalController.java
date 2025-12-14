package net.cabinet.backendspring.controller;


import net.cabinet.backendspring.dto.DossierMedicalDto;
import net.cabinet.backendspring.service.DossierMedicalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dossier-medical")
public class DossierMedicalController {

    private final DossierMedicalService dossierMedicalService;

    public DossierMedicalController(DossierMedicalService dossierMedicalService) {
        this.dossierMedicalService = dossierMedicalService;
    }

    @GetMapping
    public ResponseEntity<List<DossierMedicalDto>> getAllDossierMedicals(){
        List<DossierMedicalDto> dossierMedicalDtos = dossierMedicalService.getAllDossierMedicals();
        return ResponseEntity.status(HttpStatus.OK).body(dossierMedicalDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DossierMedicalDto> getDossierMedical(@PathVariable Long id){
        DossierMedicalDto dto = dossierMedicalService.getDossierMedicalById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<DossierMedicalDto> getDossierMedicalForPatient(@PathVariable Long patientId){
        DossierMedicalDto dto = dossierMedicalService.getDossierMedicalByPatientId(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<DossierMedicalDto> createDossierMedical(@RequestBody DossierMedicalDto dto){
        DossierMedicalDto dossierMedicalDto = dossierMedicalService.createDossierMedical(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dossierMedicalDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DossierMedicalDto> updateDossierMedical(@PathVariable Long id , @RequestBody DossierMedicalDto dto){
        DossierMedicalDto dossierMedicalDto = dossierMedicalService.updateDossierMedical(dto,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(dossierMedicalDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDossierMedical(@PathVariable Long id){
        dossierMedicalService.deleteDossierMedicalById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Le Dossier Medical a ete supprime avec succes");
    }

}
