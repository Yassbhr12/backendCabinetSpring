package net.cabinet.backendspring.dto.authentification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String login;
    private String password;

    public AuthRequest() {
    }

    public AuthRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
