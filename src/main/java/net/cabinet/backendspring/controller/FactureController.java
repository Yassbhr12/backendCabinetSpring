package net.cabinet.backendspring.controller;


import net.cabinet.backendspring.dto.FactureDto;
import net.cabinet.backendspring.service.FactureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facture")
public class FactureController {
    private final FactureService factureService;

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @GetMapping
    public ResponseEntity<List<FactureDto>> getAllFactures(){
        List<FactureDto> factureDtos = factureService.getAllFactures();
        return ResponseEntity.status(HttpStatus.OK).body(factureDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureDto> getFactureById(@PathVariable Long id){
        FactureDto factureDto = factureService.getFactureById(id);
        return ResponseEntity.status(HttpStatus.OK).body(factureDto);
    }

    @PostMapping
    public ResponseEntity<FactureDto> createFacture(@RequestBody FactureDto factureDto){
        FactureDto dto = factureService.createFacture(factureDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactureDto> updateFacture(@PathVariable Long id , @RequestBody FactureDto dto){
        FactureDto factureDto = factureService.updateFacture(dto,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(factureDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFacture(@PathVariable Long id){
        factureService.deleteFactureById(id);
        return ResponseEntity.status(HttpStatus.OK).body("La Facture a ete supprime avec succes");
    }

}
