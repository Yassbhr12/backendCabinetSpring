package net.cabinet.backendspring.repository;

import net.cabinet.backendspring.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface RendezVousRepo extends JpaRepository<RendezVous , Long> {
    Optional<List<RendezVous>> findRendezVousByPatient_Id(Long patientId);

    Optional<List<RendezVous>> findRendezVousByMedecin_Id(Long medecinId);

    Optional<List<RendezVous>> findRendezVousByMedecin_IdAndDateRdv(Long medecinId, LocalDate dateRdv);

    boolean existsRendezVousByMedecin_IdAndDateRdvAndHeureRdv(Long medecinId, LocalDate dateRdv, LocalTime heureRdv);
}
