package net.cabinet.backendspring.controller;

import net.cabinet.backendspring.dto.CabinetDto;
import net.cabinet.backendspring.service.CabinetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cabinets")
public class CabinetController {

    private final CabinetService cabinetService;

    public CabinetController(CabinetService cabinetService) {
        this.cabinetService = cabinetService;
    }

    @GetMapping
    public ResponseEntity<List<CabinetDto>> getAllCabinets() {
        List<CabinetDto> cabinets = cabinetService.getAllCabinets();
        return ResponseEntity.status(HttpStatus.OK).body(cabinets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CabinetDto> getCabinetById(@PathVariable Long id) {
        CabinetDto dto = cabinetService.getCabinetById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<CabinetDto> createCabinet(@RequestBody CabinetDto dto) {
        CabinetDto created = cabinetService.createCabinet(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CabinetDto> updateCabinet(@RequestBody CabinetDto dto, @PathVariable Long id) {
        CabinetDto updated = cabinetService.updateCabinetById(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCabinet(@PathVariable Long id) {
        cabinetService.deleteCabinetById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cabinet supprime avec succes");
    }
}
