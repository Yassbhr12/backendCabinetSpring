package net.cabinet.backendspring.controller;

import net.cabinet.backendspring.dto.UtilisateurDto;
import net.cabinet.backendspring.helper.enums.Role;
import net.cabinet.backendspring.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/medecins")
public class MedecinController {

    private final UtilisateurService utilisateurService;

    public MedecinController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurDto>> getAllMedecins() {
        List<UtilisateurDto> medecins = utilisateurService.getUtilisateursByRole(Role.MEDECIN);
        return ResponseEntity.status(HttpStatus.OK).body(medecins);
    }
}
