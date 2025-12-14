package net.cabinet.backendspring.controller;

import net.cabinet.backendspring.dto.authentification.AuthRequest;
import net.cabinet.backendspring.dto.authentification.ValidationCodeRequest;
import net.cabinet.backendspring.service.UtilisateurService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
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
    public ResponseEntity<?> validateCode(@RequestBody ValidationCodeRequest validationCodeRequest){
        try {
            utilisateurService.validateCode(validationCodeRequest);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message", "Validation successful"
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
        // Invalider le token côté serveur si nécessaire
        return ResponseEntity.ok("Déconnexion réussie");
    }

}
