package net.cabinet.backendspring.controller;


import net.cabinet.backendspring.dto.MedicamentDto;
import net.cabinet.backendspring.service.MedicamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicament")
public class MedicamentController {

    private final MedicamentService medicamentService;

    public MedicamentController(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    @GetMapping
    public ResponseEntity<List<MedicamentDto>> getAllMedicaments(){
        List<MedicamentDto> medicamentDtos = medicamentService.getAllMedicament();
        return ResponseEntity.status(HttpStatus.OK).body(medicamentDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentDto> getMedicament(@PathVariable Long id){
        MedicamentDto dto = medicamentService.getMedicamentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<MedicamentDto> createMedicament(@RequestBody MedicamentDto dto){
        MedicamentDto medicamentDto = medicamentService.createMedicament(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentDto> updateMedicament(@PathVariable Long id , @RequestBody MedicamentDto dto){
        MedicamentDto medicamentDto = medicamentService.updateMedicament(dto,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicamentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicament(@PathVariable Long id){
        medicamentService.deleteMedicament(id);
        return ResponseEntity.status(HttpStatus.OK).body("Le Medicament a ete supprime avec succes");
    }
}
