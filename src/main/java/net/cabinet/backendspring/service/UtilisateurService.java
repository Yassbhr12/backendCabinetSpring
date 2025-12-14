package net.cabinet.backendspring.service;


import net.cabinet.backendspring.dto.UtilisateurDto;
import net.cabinet.backendspring.dto.authentification.AuthRequest;
import net.cabinet.backendspring.dto.authentification.ValidationCodeRequest;
import net.cabinet.backendspring.entity.Cabinet;
import net.cabinet.backendspring.entity.Utilisateur;
import net.cabinet.backendspring.mapper.UtilisateurMapper;
import net.cabinet.backendspring.repository.CabinetRepo;
import net.cabinet.backendspring.repository.UtilisateurRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutionException;

@Service
public class UtilisateurService {

    private final UtilisateurRepo utilisateurRepo;
    private final CabinetRepo cabinetRepo;
    private final EmailService emailService;
    private final UtilisateurMapper mapper;

    public UtilisateurService(UtilisateurRepo utilisateurRepo, CabinetRepo cabinetRepo, EmailService emailService, UtilisateurMapper mapper) {
        this.utilisateurRepo = utilisateurRepo;
        this.cabinetRepo = cabinetRepo;
        this.emailService = emailService;
        this.mapper = mapper;
    }

    private String generateValidationCode() {
        Random rand = new Random();
        return String.format("%06d", rand.nextInt(999999));
    }

    @Transactional
    public UtilisateurDto createUtilisateur(UtilisateurDto utilisateurDto , AuthRequest auth){
        Cabinet cabinet = cabinetRepo.findById(utilisateurDto.getCabinetId()).orElseThrow(()->new RuntimeException("Cabinet Not Found")) ;
        Utilisateur utilisateur = mapper.toEntity(utilisateurDto , cabinet);
        utilisateur.setPwd(auth.getPassword());
        if(auth.getLogin()!=null){
            utilisateur.setLogin(auth.getLogin());
        }

        Utilisateur saved = utilisateurRepo.save(utilisateur);

        return mapper.toDto(saved);
    }

    @Transactional
    public UtilisateurDto updateUtilisateur(UtilisateurDto dto , Long id){
        Utilisateur utilisateur = utilisateurRepo.findById(id).orElseThrow(()->new RuntimeException("Utilisateur Not Found"));
        mapper.updateEntity(utilisateur,dto);

        Utilisateur updated = utilisateurRepo.save(utilisateur);

        return mapper.toDto(updated);
    }

    public UtilisateurDto getUtilisateurById(Long id){
        return utilisateurRepo.findById(id).map(mapper::toDto).orElseThrow(()-> new RuntimeException("Utilisateur Not Found"));
    }

    public List<UtilisateurDto> getAllUtilisateurs(){
        List<Utilisateur> utilisateurs = utilisateurRepo.findAll();
        return mapper.toDtoList(utilisateurs);
    }

    public UtilisateurDto getUtilisateurByLogin(String login){
        Utilisateur utilisateur = utilisateurRepo.findUtilisateurByLogin(login).orElseThrow(()->new RuntimeException("Utilisateur Not Found"));
        return mapper.toDto(utilisateur);
    }

    @Transactional
    public void processLogin(AuthRequest authRequest) throws Exception {
        Utilisateur utilisateur = utilisateurRepo.findUtilisateurByLogin(authRequest.getLogin()).orElseThrow(()->new RuntimeException("Utilisateur Not Found"));

        if(!utilisateur.getActif()){
            throw new Exception("Votre compte est desactive , contactez l'administrateur");
        }

        if(!utilisateur.getPwd().equals(authRequest.getPassword())){
            throw new Exception("Invalid login or password");
        }

        String validationCode = generateValidationCode();
        utilisateur.setValidationCode(validationCode);
        utilisateur.setValidationCodeExpiration(LocalDateTime.now().plusSeconds(180));
        utilisateurRepo.save(utilisateur);
        emailService.sendEmail(
                utilisateur.getLogin(),
                "Code de validation",
                "Votre code de validation est : " + validationCode + "\nCe code expirera après 180 secondes."
        );
    }

    @Transactional
    public void validateCode(ValidationCodeRequest validationCodeRequest) throws Exception {
        Utilisateur utilisateur = utilisateurRepo.findUtilisateurByLogin(validationCodeRequest.getLogin()).orElseThrow(()->new RuntimeException("Utilisateur Not Found"));

        if(utilisateur.getValidationCode() == null || utilisateur.getValidationCodeExpiration().isBefore(LocalDateTime.now())){
            throw new Exception("Validation Code Expired Or Not Found");
        }

        if(!utilisateur.getValidationCode().equals(validationCodeRequest.getValidationCode())){
            throw new Exception("Invalid Validation Code");
        }

        utilisateur.setValidationCode(null);
        utilisateur.setValidationCodeExpiration(null);

        utilisateurRepo.save(utilisateur);

    }



    @Transactional
    public void deleteUtilisateurById(Long id){
        Utilisateur utilisateur = utilisateurRepo.findById(id).orElseThrow(()->new RuntimeException("Utilisateur Not Found"));
        utilisateurRepo.delete(utilisateur);
    }

}
