package net.cabinet.backendspring.repository;

import net.cabinet.backendspring.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UtilisateurRepo extends JpaRepository<Utilisateur , Long> {
    Optional<Utilisateur> findUtilisateurByLogin(String login);
}

