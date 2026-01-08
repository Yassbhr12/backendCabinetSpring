package net.cabinet.backendspring.dto;

import net.cabinet.backendspring.dto.authentification.AuthRequest;

public class CreateUtilisateurRequest {

    private UtilisateurDto utilisateur;
    private AuthRequest auth;

    public CreateUtilisateurRequest() {
    }

    public CreateUtilisateurRequest(UtilisateurDto utilisateur, AuthRequest auth) {
        this.utilisateur = utilisateur;
        this.auth = auth;
    }

    public UtilisateurDto getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurDto utilisateur) {
        this.utilisateur = utilisateur;
    }

    public AuthRequest getAuth() {
        return auth;
    }

    public void setAuth(AuthRequest auth) {
        this.auth = auth;
    }
}
