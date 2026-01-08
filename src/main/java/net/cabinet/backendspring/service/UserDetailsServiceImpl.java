package net.cabinet.backendspring.service;

import net.cabinet.backendspring.entity.Utilisateur;
import net.cabinet.backendspring.repository.UtilisateurRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UtilisateurRepo utilisateurRepo;

    public UserDetailsServiceImpl(UtilisateurRepo utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepo.findUtilisateurByLogin(username).orElseThrow(()-> new UsernameNotFoundException("UserName Not Found "));

        boolean isActif = !Boolean.FALSE.equals(utilisateur.getActif());

        if(!isActif){
            throw new RuntimeException("Compte est desative");
        }

        return User.builder()
                .username(utilisateur.getLogin())
                .password(utilisateur.getPwd())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().name())))
                .accountExpired(false)
                .accountLocked(!isActif)
                .credentialsExpired(false)
                .build();
    }
}
