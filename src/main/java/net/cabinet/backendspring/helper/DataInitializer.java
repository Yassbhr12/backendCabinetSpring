package net.cabinet.backendspring.helper;


import net.cabinet.backendspring.entity.Utilisateur;
import net.cabinet.backendspring.helper.enums.Role;
import net.cabinet.backendspring.repository.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// Ou crée un script d'initialisation
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UtilisateurRepo utilisateurRepository;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    @Override
    public void run(String... args) throws Exception {
        // Vérifier si des utilisateurs existent déjà
        if (utilisateurRepository.count() == 0) {
            // Créer un admin
            Utilisateur admin = new Utilisateur();
            admin.setLogin("bahraahmedyassine@gmail.com");
            admin.setPwd(passwordEncoder.encode("Yassbhr12"));
            admin.setNom("Admin");
            admin.setPrenom("Yassine");
            admin.setNumTel("0656889706");
            admin.setRole(Role.ADMIN);
            admin.setActif(true);
            utilisateurRepository.save(admin);

            // Créer un médecin
            Utilisateur medecin = new Utilisateur();
            medecin.setLogin("yassinebhr475@gmail.com");
            medecin.setPwd(passwordEncoder.encode("AlamiDr12"));
            medecin.setNom("Dr.Alami");
            medecin.setPrenom("Mohamed");
            medecin.setNumTel("0600112233");
            medecin.setRole(Role.MEDECIN);
            medecin.setActif(true);
            utilisateurRepository.save(medecin);

            // Créer une secrétaire
            Utilisateur secretaire = new Utilisateur();
            secretaire.setLogin("secretaire");
            secretaire.setPwd(passwordEncoder.encode("secretaire12"));
            secretaire.setNom("Benali");
            secretaire.setPrenom("Fatima");
            secretaire.setNumTel("0600112233");
            secretaire.setRole(Role.SECRETAIRE);
            secretaire.setActif(true);
            utilisateurRepository.save(secretaire);

            System.out.println("✅ Utilisateurs de test créés");
        }
    }
}
