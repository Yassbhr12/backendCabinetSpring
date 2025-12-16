package net.cabinet.backendspring.controller;

import net.cabinet.backendspring.dto.UtilisateurDto;
import net.cabinet.backendspring.dto.authentification.AuthRequest;
import net.cabinet.backendspring.dto.authentification.AuthResponse;
import net.cabinet.backendspring.dto.authentification.ValidationCodeRequest;
import net.cabinet.backendspring.entity.Utilisateur;
import net.cabinet.backendspring.service.UtilisateurService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "http://localhost:3000")
public class AuthRequestController {
    private final UtilisateurService utilisateurService;

    public AuthRequestController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        try {
            utilisateurService.processLogin(authRequest);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message", "User successfully logged in. Validation code sent.",
                    "login", authRequest.getLogin()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "error", e.getMessage()
            ));
        }

    }

    @PostMapping("/login/validation")
    public ResponseEntity<?> validateCodeRequest(@RequestBody ValidationCodeRequest validationCodeRequest){
        try {
            String token = utilisateurService.validateCode(validationCodeRequest);
            UtilisateurDto dto = utilisateurService.getUtilisateurByLogin(validationCodeRequest.getLogin());
            AuthResponse authResponse = new AuthResponse(
                    token,
                    dto.getId(),
                    dto.getLogin(),
                    dto.getNom(),
                    dto.getPrenom(),
                    dto.getRole()
            );
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "Message", "Validation successful" , "Reponse" , authResponse
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "error" , e.getMessage()
                    )
            );
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {

        return ResponseEntity.ok("Déconnexion réussie");
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "error", "Non authentifié"
            ));
        }

        String login = authentication.getName();
        UtilisateurDto dto = utilisateurService.getUtilisateurByLogin(login);

        return ResponseEntity.ok(dto);
    }

}
