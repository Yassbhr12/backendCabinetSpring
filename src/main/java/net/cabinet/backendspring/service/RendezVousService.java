package net.cabinet.backendspring.service;

import net.cabinet.backendspring.dto.RendezVousDto;
import net.cabinet.backendspring.entity.Patient;
import net.cabinet.backendspring.entity.RendezVous;
import net.cabinet.backendspring.entity.Utilisateur;
import net.cabinet.backendspring.helper.enums.Role;
import net.cabinet.backendspring.helper.enums.Statut;
import net.cabinet.backendspring.mapper.RendezVousMapper;
import net.cabinet.backendspring.repository.PatientRepo;
import net.cabinet.backendspring.repository.RendezVousRepo;
import net.cabinet.backendspring.repository.UtilisateurRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class RendezVousService {

    private final RendezVousRepo rendezVousRepo;
    private final PatientRepo patientRepo;
    private final UtilisateurRepo utilisateurRepo;
    private final RendezVousMapper mapper;

    public RendezVousService(RendezVousRepo rendezVousRepo, PatientRepo patientRepo, UtilisateurRepo utilisateurRepo, RendezVousMapper mapper) {
        this.rendezVousRepo = rendezVousRepo;
        this.patientRepo = patientRepo;
        this.utilisateurRepo = utilisateurRepo;
        this.mapper = mapper;
    }

    @Transactional
    public RendezVousDto createRendezVous(RendezVousDto dto ){
        Patient patient = patientRepo.findById(dto.getPatientId()).orElseThrow(()-> new RuntimeException("Patient Not Found"));
        Utilisateur medecin = utilisateurRepo.findById(dto.getMedecinId()).orElseThrow(()->new RuntimeException("Utilisateur Not Found"));

        if (medecin.getRole() != Role.MEDECIN) {
            throw new IllegalArgumentException("L'utilisateur avec l'ID " + dto.getMedecinId() + " n'est pas un médecin");
        }

        boolean existDeja = rendezVousRepo.existsRendezVousByMedecin_IdAndDateRdvAndHeureRdv(
                dto.getMedecinId(),
                dto.getDateRdv(),
                dto.getHeureRdv()
        );

        if(existDeja){
            throw new IllegalStateException("Un rendez-vous existe déjà à cette date et heure pour ce médecin");
        }

        RendezVous rendezVous = mapper.toEntity(dto , patient,medecin);
        RendezVous saved = rendezVousRepo.save(rendezVous);
        return mapper.toDto(saved);
    }

    @Transactional
    public RendezVousDto updateRendezVousById(RendezVousDto dto , Long id){
        RendezVous rendezVous = rendezVousRepo.findById(id).orElseThrow(()->new RuntimeException("Rendez-vous Not Found"));
        mapper.updateEntity(rendezVous,dto);

        RendezVous saved = rendezVousRepo.save(rendezVous);
        return mapper.toDto(saved);

    }

    @Transactional
    public RendezVousDto annulerRendezVous(Long id , RendezVousDto dto){
        RendezVous rendezVous = rendezVousRepo.findById(id).orElseThrow(()->new RuntimeException("Rendez-vous Not Found"));

        if(dto.getStatut().equals(Statut.ANNULE)){
            rendezVous.setStatut(Statut.ANNULE);
        }

        RendezVous saved = rendezVousRepo.save(rendezVous);

        return mapper.toDto(saved);

    }

    @Transactional
    public RendezVousDto confirmerRendezVous(Long id , RendezVousDto dto){
        RendezVous rendezVous = rendezVousRepo.findById(id).orElseThrow(()->new RuntimeException("Rendez-vous Not Found"));

        if(dto.getStatut().equals(Statut.CONFIRME)){
            rendezVous.setStatut(Statut.CONFIRME);
        }

        RendezVous saved = rendezVousRepo.save(rendezVous);

        return mapper.toDto(saved);
    }

    public RendezVousDto getRendezVousById(Long id){
        return mapper.toDto(rendezVousRepo.findById(id).orElseThrow(()->new RuntimeException("Rendez-vous Not Found")));
    }

    public List<RendezVousDto> getAllRendezVous(){
        return mapper.toDtoList(rendezVousRepo.findAll());
    }

    public List<RendezVousDto> getRendezVousByPatientId(Long patientId){
        return rendezVousRepo.findRendezVousByPatient_Id(patientId).map(mapper::toDtoList).orElseThrow(()->new RuntimeException("Rendez-vous Not Found"));
    }

    public List<RendezVousDto> getRendezVousByMedecinId(Long medecinId){
        return rendezVousRepo.findRendezVousByMedecin_Id(medecinId).map(mapper::toDtoList).orElseThrow(()->new RuntimeException("Rendez-vous Not Found"));
    }

    public List<RendezVousDto> getRendezVousByMedecinIdAndDateRdv(Long medecinId , LocalDate dateRdv ){
        return rendezVousRepo.findRendezVousByMedecin_IdAndDateRdv(medecinId,dateRdv).map(mapper::toDtoList).orElseThrow(()->new RuntimeException("Aucun Rendez Vous Trouve"));
    }

    @Transactional
    public void deleteRendezVousById(Long id){
        RendezVous rendezVous = rendezVousRepo.findById(id).orElseThrow(()->new RuntimeException("Rendez-vous Not Found"));
        rendezVousRepo.delete(rendezVous);
    }


}
