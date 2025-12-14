package net.cabinet.backendspring.repository;

import net.cabinet.backendspring.entity.Cabinet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabinetRepo extends JpaRepository<Cabinet , Long> {
}
