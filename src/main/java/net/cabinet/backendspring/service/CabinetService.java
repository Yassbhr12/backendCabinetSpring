package net.cabinet.backendspring.service;


import net.cabinet.backendspring.dto.CabinetDto;
import net.cabinet.backendspring.entity.Cabinet;
import net.cabinet.backendspring.mapper.CabinetMapper;
import net.cabinet.backendspring.repository.CabinetRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CabinetService {

    private final CabinetRepo cabinetRepo;
    private final CabinetMapper mapper;

    public CabinetService(CabinetRepo cabinetRepo, CabinetMapper mapper) {
        this.cabinetRepo = cabinetRepo;
        this.mapper = mapper;
    }

    @Transactional
    public CabinetDto createCabinet(CabinetDto dto){

        Cabinet cabinet = mapper.toEntity(dto);
        Cabinet saved = cabinetRepo.save(cabinet);

        return mapper.toDto(saved);
    }

    public CabinetDto getCabinetById(Long id){
        return cabinetRepo.findById(id).map(mapper::toDto).orElseThrow(()-> new RuntimeException("Cabinet Not Found"));
    }

    @Transactional
    public CabinetDto updateCabinetById(CabinetDto dto , Long id ){
        Cabinet cabinet = cabinetRepo.findById(id).orElseThrow(()->new RuntimeException("Cabinet Not Found"));
        mapper.updateEntity(cabinet,dto);
        Cabinet updated = cabinetRepo.save(cabinet);
        return mapper.toDto(updated);
    }

    @Transactional
    public void deleteCabinetById(Long id){
        Cabinet cabinet = cabinetRepo.findById(id).orElseThrow(()->new RuntimeException("Cabinet Not Found"));
        cabinetRepo.delete(cabinet);
    }
}
