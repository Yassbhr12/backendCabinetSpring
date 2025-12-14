package net.cabinet.backendspring.dto.authentification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationCodeRequest {

    private String login;
    private String validationCode;

    public ValidationCodeRequest() {
    }

    public ValidationCodeRequest(String login, String validationCode) {
        this.login = login;
        this.validationCode = validationCode;
    }
}
