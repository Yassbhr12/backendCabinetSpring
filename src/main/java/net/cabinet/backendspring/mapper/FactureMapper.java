package net.cabinet.backendspring.mapper;


import net.cabinet.backendspring.dto.FactureDto;
import net.cabinet.backendspring.entity.Consultation;
import net.cabinet.backendspring.entity.Facture;
import net.cabinet.backendspring.entity.Patient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FactureMapper {

    public FactureDto toDto(Facture facture){
        FactureDto dto = new FactureDto();

        dto.setId(facture.getIdFacture());
        dto.setMontant(facture.getMontant());
        dto.setModePaiement(facture.getModePaiement());
        dto.setDateFacture(facture.getDateFacture());
        dto.setStatutFacture(facture.getStatut());

        dto.setConsultationId(facture.getConsultation().getIdConsultation());
        dto.setTypeConsultation(facture.getConsultation().getTypeConsultation());
        dto.setObservations(facture.getConsultation().getObservations());

        dto.setPatientId(facture.getPatient().getId());
        dto.setCin(facture.getPatient().getCin());
        dto.setNom(facture.getPatient().getNom());

        return dto;
    }

    public List<FactureDto> toDtoList(List<Facture> factures){
        return factures.stream().map(this::toDto).toList();
    }

    public Facture toEntity(FactureDto dto , Consultation consultation , Patient patient){
        Facture facture = new Facture();

        facture.setIdFacture(dto.getId());
        facture.setMontant(dto.getMontant());
        facture.setModePaiement(dto.getModePaiement());
        facture.setDateFacture(dto.getDateFacture());
        facture.setStatut(dto.getStatutFacture());

        facture.setConsultation(consultation);

        facture.setPatient(patient);

        return facture;
    }

    public void updateEntity(Facture facture , FactureDto dto){

        facture.setMontant(dto.getMontant());
        facture.setModePaiement(dto.getModePaiement());
        facture.setDateFacture(dto.getDateFacture());
        facture.setStatut(dto.getStatutFacture());

    }

}
