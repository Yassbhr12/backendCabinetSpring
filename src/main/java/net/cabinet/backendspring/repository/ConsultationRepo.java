package net.cabinet.backendspring.repository;

import net.cabinet.backendspring.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsultationRepo extends JpaRepository<Consultation , Long> {
    Optional<List<Consultation>> findConsultationByPatient_Id(Long patientId);

    Optional<List<Consultation>> findConsultationByMedecin_Id(Long medecinId);
}
