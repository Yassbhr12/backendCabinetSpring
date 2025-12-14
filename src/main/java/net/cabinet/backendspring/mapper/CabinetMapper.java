package net.cabinet.backendspring.mapper;


import net.cabinet.backendspring.dto.CabinetDto;
import net.cabinet.backendspring.entity.Cabinet;
import org.springframework.stereotype.Component;

@Component
public class CabinetMapper {

    public CabinetDto toDto(Cabinet cabinet){
        CabinetDto dto = new CabinetDto();
        dto.setId(cabinet.getId());
        dto.setNom(cabinet.getNom());
        dto.setSpecialite(cabinet.getSpecialite());
        dto.setAdresse(cabinet.getAdresse());
        dto.setNumTel(cabinet.getNumTel());
        dto.setLogo(cabinet.getLogo());
        return dto;
    }

    public Cabinet toEntity(CabinetDto  dto){
        Cabinet cabinet = new Cabinet();
        cabinet.setId(dto.getId());
        cabinet.setLogo(dto.getLogo());
        cabinet.setNom(dto.getNom());
        cabinet.setSpecialite(dto.getSpecialite());
        cabinet.setAdresse(dto.getAdresse());
        cabinet.setNumTel(dto.getNumTel());
        cabinet.setActif(dto.getActif());
        return cabinet;
    }

    public void updateEntity(Cabinet cabinet , CabinetDto dto){
        cabinet.setLogo(dto.getLogo());
        cabinet.setNom(dto.getNom());
        cabinet.setSpecialite(dto.getSpecialite());
        cabinet.setAdresse(dto.getAdresse());
        cabinet.setNumTel(dto.getNumTel());
        cabinet.setActif(dto.getActif());
    }
}
