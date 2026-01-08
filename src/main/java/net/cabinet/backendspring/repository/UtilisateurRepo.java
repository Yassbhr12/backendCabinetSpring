package net.cabinet.backendspring.repository;

import net.cabinet.backendspring.entity.Utilisateur;
import net.cabinet.backendspring.helper.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UtilisateurRepo extends JpaRepository<Utilisateur , Long> {
    Optional<Utilisateur> findUtilisateurByLogin(String login);

    List<Utilisateur> findAllByRole(Role role);
}

