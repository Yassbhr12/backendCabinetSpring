package net.cabinet.backendspring.controller;

import net.cabinet.backendspring.dto.RendezVousDto;
import net.cabinet.backendspring.service.RendezVousService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rendez-vous")
public class RendezVousController {

    private final RendezVousService rendezVousService;

    public RendezVousController(RendezVousService rendezVousService) {
        this.rendezVousService = rendezVousService;
    }

    @GetMapping
    public ResponseEntity<List<RendezVousDto>> getAllRendezVous(){
        List<RendezVousDto> rendezVousDtos = rendezVousService.getAllRendezVous();
        return ResponseEntity.status(HttpStatus.OK).body(rendezVousDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RendezVousDto> getRendezVousById(@PathVariable Long id){
        RendezVousDto rendezVousDto = rendezVousService.getRendezVousById(id);
        return ResponseEntity.status(HttpStatus.OK).body(rendezVousDto);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<RendezVousDto>> getRendezVousByPatient(@PathVariable Long patientId){
        List<RendezVousDto> rendezVousDtos = rendezVousService.getRendezVousByPatientId(patientId);
        return ResponseEntity.status(HttpStatus.OK).body(rendezVousDtos);
    }

    @GetMapping("/medecin/{medecinId}")
    public ResponseEntity<List<RendezVousDto>> getRendezVousByMedecin(@PathVariable Long medecinId){
        List<RendezVousDto> rendezVousDtos = rendezVousService.getRendezVousByMedecinId(medecinId);
        return ResponseEntity.status(HttpStatus.OK).body(rendezVousDtos);
    }

    @GetMapping("/medecin/{medecinId}/aujourdhui")
    public ResponseEntity<List<RendezVousDto>> getRendezVousDuJour(@PathVariable Long medecinId){
        List<RendezVousDto> rendezVousDtos = rendezVousService.getRendezVousByMedecinIdAndDateRdv(medecinId, LocalDate.now());
        return ResponseEntity.status(HttpStatus.OK).body(rendezVousDtos);
    }

    @PostMapping
    public ResponseEntity<RendezVousDto> createRendezVous(@RequestBody RendezVousDto dto){
        RendezVousDto rendezVousDto = rendezVousService.createRendezVous(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(rendezVousDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RendezVousDto> updateRendezVous(@PathVariable Long id , @RequestBody RendezVousDto dto){
        RendezVousDto rendezVousDto = rendezVousService.updateRendezVousById(dto,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(rendezVousDto);
    }

    @PatchMapping("/{id}/annuler")
    public ResponseEntity<RendezVousDto> annulerRendezVous(@PathVariable Long id , @RequestBody RendezVousDto dto){
        RendezVousDto rendezVousDto = rendezVousService.annulerRendezVous(id ,dto );
        return ResponseEntity.status(HttpStatus.CREATED).body(rendezVousDto);
    }

    @PatchMapping("/{id}/confirmer")
    public ResponseEntity<RendezVousDto> confirmerRendezVous(@PathVariable Long id , @RequestBody RendezVousDto dto){
        RendezVousDto rendezVousDto = rendezVousService.confirmerRendezVous(id ,dto );
        return ResponseEntity.status(HttpStatus.CREATED).body(rendezVousDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRendezVous(@PathVariable Long id){
        rendezVousService.deleteRendezVousById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Le Rendez-vous a ete supprime avec succes");
    }


}
