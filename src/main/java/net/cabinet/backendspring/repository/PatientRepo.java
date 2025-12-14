package net.cabinet.backendspring.repository;

import net.cabinet.backendspring.entity.Patient;
import net.cabinet.backendspring.helper.enums.Sexe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepo extends JpaRepository<Patient,Long> {
    Optional<Patient> findPatientByCin(String cin);

    Optional<List<Patient>> findPatientBySexe(Sexe sexe);

    Optional<List<Patient>> findPatientByNom(String nom);



}
