package net.cabinet.backendspring.controller;


import net.cabinet.backendspring.dto.UtilisateurDto;
import net.cabinet.backendspring.dto.authentification.AuthRequest;
import net.cabinet.backendspring.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public ResponseEntity< List<UtilisateurDto> > getAllUtilisateurs(){
        List<UtilisateurDto> utilisateurDtos = utilisateurService.getAllUtilisateurs();
        return ResponseEntity.status(HttpStatus.OK).body(utilisateurDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateur(@PathVariable Long id){
        UtilisateurDto dto = utilisateurService.getUtilisateurById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<UtilisateurDto> createUtilisateur(@RequestBody UtilisateurDto dto , @RequestBody AuthRequest authRequest){
        UtilisateurDto utilisateurDto = utilisateurService.createUtilisateur(dto , authRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDto> updateUtilisateur(@RequestBody UtilisateurDto dto , @PathVariable Long id){
        UtilisateurDto utilisateurDto = utilisateurService.updateUtilisateur(dto,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUtilisateur(@PathVariable Long id){
        utilisateurService.deleteUtilisateurById(id);
        return ResponseEntity.status(HttpStatus.OK).body("L'utilisateur a ete supprime avec succes");
    }

}
