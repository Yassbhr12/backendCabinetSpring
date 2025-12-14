package net.cabinet.backendspring.repository;

import net.cabinet.backendspring.entity.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentRepo extends JpaRepository<Medicament , Long> {

}
