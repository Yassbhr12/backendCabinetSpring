package net.cabinet.backendspring.repository;

import net.cabinet.backendspring.entity.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DossierMedicalRepo extends JpaRepository<DossierMedical, Long> {

    Optional<DossierMedical> findDossierMedicalByPatient_Id(Long patientId);

    Optional<DossierMedical> findDossierMedicalByPatient_Cin(String patientCin);
}
