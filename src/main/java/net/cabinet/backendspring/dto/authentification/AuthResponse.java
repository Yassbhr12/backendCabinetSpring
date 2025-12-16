package net.cabinet.backendspring.dto.authentification;

import lombok.Getter;
import lombok.Setter;
import net.cabinet.backendspring.helper.enums.Role;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class AuthResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String login;
    private String nom;
    private String prenom;
    private Role role;

    public AuthResponse() {
    }

    public AuthResponse(String token, Long id, String login, String nom, String prenom, Role role) {
        this.token = token;
        this.id = id;
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
    }
}
