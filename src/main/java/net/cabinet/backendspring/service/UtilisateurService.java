package net.cabinet.backendspring.service;


import net.cabinet.backendspring.dto.UtilisateurDto;
import net.cabinet.backendspring.dto.authentification.AuthRequest;
import net.cabinet.backendspring.dto.authentification.ValidationCodeRequest;
import net.cabinet.backendspring.entity.Cabinet;
import net.cabinet.backendspring.entity.Utilisateur;
import net.cabinet.backendspring.helper.security.JwtUtils;
import net.cabinet.backendspring.mapper.UtilisateurMapper;
import net.cabinet.backendspring.repository.CabinetRepo;
import net.cabinet.backendspring.repository.UtilisateurRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    private final JwtUtils jwtUtils;

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    public UtilisateurService(UtilisateurRepo utilisateurRepo, CabinetRepo cabinetRepo, EmailService emailService, UtilisateurMapper mapper, JwtUtils jwtUtils) {
        this.utilisateurRepo = utilisateurRepo;
        this.cabinetRepo = cabinetRepo;
        this.emailService = emailService;
        this.mapper = mapper;
        this.jwtUtils = jwtUtils;
    }

    private String generateValidationCode() {
        Random rand = new Random();
        return String.format("%06d", rand.nextInt(999999));
    }

    @Transactional
    public UtilisateurDto createUtilisateur(UtilisateurDto utilisateurDto , AuthRequest auth){
        Cabinet cabinet = cabinetRepo.findById(utilisateurDto.getCabinetId()).orElseThrow(()->new RuntimeException("Cabinet Not Found")) ;
        Utilisateur utilisateur = mapper.toEntity(utilisateurDto , cabinet);
        utilisateur.setPwd(encoder.encode(auth.getPassword()));
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
        Utilisateur utilisateur = utilisateurRepo.findUtilisateurByLogin(authRequest.getLogin())
                .orElseThrow(() -> new RuntimeException("Utilisateur Not Found"));

        if (!utilisateur.getActif()) {
            throw new Exception("Votre compte est désactivé, contactez l'administrateur");
        }

        if (!encoder.matches(authRequest.getPassword(), utilisateur.getPwd())) {
            throw new Exception("Invalid login or password");
        }

        String validationCode = generateValidationCode();

        LocalDateTime expiration = LocalDateTime.now()
                .plusSeconds(180)
                .truncatedTo(ChronoUnit.SECONDS);

        utilisateur.setValidationCode(validationCode);
        utilisateur.setValidationCodeExpiration(expiration);
        utilisateurRepo.save(utilisateur);

        emailService.sendEmail(
                utilisateur.getLogin(),
                "Code de validation",
                "Votre code de validation est : " + validationCode +
                        "\nCe code expirera après 3 minutes."
        );
    }

    @Transactional
    public String validateCode(ValidationCodeRequest validationCodeRequest) throws Exception {
        Utilisateur utilisateur = utilisateurRepo.findUtilisateurByLogin(validationCodeRequest.getLogin())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        System.out.println("=== DÉBOGAGE VALIDATION ===");
        System.out.println("Login: " + validationCodeRequest.getLogin());
        System.out.println("Code reçu: '" + validationCodeRequest.getValidationCode() + "'");
        System.out.println("Code en DB: '" + utilisateur.getValidationCode() + "'");
        System.out.println("Expiration en DB: " + utilisateur.getValidationCodeExpiration());

        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        System.out.println("Heure actuelle: " + now);


        if (utilisateur.getValidationCode() == null) {
            throw new Exception("Aucun code de validation trouvé. Veuillez vous reconnecter.");
        }

        if (utilisateur.getValidationCodeExpiration() == null) {
            throw new Exception("Code de validation expiré. Veuillez vous reconnecter.");
        }

        LocalDateTime expiration = utilisateur.getValidationCodeExpiration().truncatedTo(ChronoUnit.SECONDS);

        if (now.isAfter(expiration)) {
            long secondesEcoulees = java.time.Duration.between(expiration, now).getSeconds();
            throw new Exception("Le code a expiré il y a " + secondesEcoulees + " secondes. Veuillez vous reconnecter.");
        }

        String codeRecu = validationCodeRequest.getValidationCode().trim();
        String codeStocke = utilisateur.getValidationCode().trim();

        if (!codeStocke.equals(codeRecu)) {
            System.out.println("Codes différents:");
            System.out.println("   Reçu: '" + codeRecu + "' (longueur: " + codeRecu.length() + ")");
            System.out.println("   Stocké: '" + codeStocke + "' (longueur: " + codeStocke.length() + ")");
            throw new Exception("Code de validation incorrect");
        }

        System.out.println("Validation réussie !");

        utilisateur.setValidationCode(null);
        utilisateur.setValidationCodeExpiration(null);
        utilisateurRepo.save(utilisateur);
        
        return jwtUtils.generateToken(utilisateur.getLogin(), utilisateur.getRole().name());
    }



    @Transactional
    public void deleteUtilisateurById(Long id){
        Utilisateur utilisateur = utilisateurRepo.findById(id).orElseThrow(()->new RuntimeException("Utilisateur Not Found"));
        utilisateurRepo.delete(utilisateur);
    }

}
