package net.cabinet.backendspring.mapper;


import net.cabinet.backendspring.dto.MedicamentDto;
import net.cabinet.backendspring.entity.Medicament;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicamentMapper {

    public MedicamentDto toDto(Medicament medicament){
        MedicamentDto dto = new MedicamentDto();

        dto.setId(medicament.getId());
        dto.setNom(medicament.getNom());
        dto.setForme(medicament.getForme());
        dto.setDosage(medicament.getDosage());
        dto.setIndication(medicament.getIndication());
        dto.setContrIndication(medicament.getContrIndication());
        dto.setActif(medicament.getActif());

        return dto;
    }

    public List<MedicamentDto> toDtoList(List<Medicament> medicaments){
        return medicaments.stream().map(this::toDto).toList();
    }

    public Medicament toEntity(MedicamentDto dto){
        Medicament medicament = new Medicament();
        medicament.setId(dto.getId());
        medicament.setNom(dto.getNom());
        medicament.setForme(dto.getForme());
        medicament.setDosage(dto.getDosage());
        medicament.setIndication(dto.getIndication());
        medicament.setContrIndication(dto.getContrIndication());
        medicament.setActif(dto.getActif());

        return medicament;
    }

    public void updateEntity(Medicament medicament , MedicamentDto dto){
        medicament.setNom(dto.getNom());
        medicament.setForme(dto.getForme());
        medicament.setDosage(dto.getDosage());
        medicament.setIndication(dto.getIndication());
        medicament.setContrIndication(dto.getContrIndication());
        medicament.setActif(dto.getActif());
    }

}
