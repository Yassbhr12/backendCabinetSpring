package net.cabinet.backendspring.repository;

import net.cabinet.backendspring.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FactureRepo extends JpaRepository<Facture , Long> {

    Optional<List<Facture>> findFactureByPatient_Id(Long patientId);
}
