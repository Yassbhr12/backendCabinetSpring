package net.cabinet.backendspring.service;

import net.cabinet.backendspring.dto.MedicamentDto;
import net.cabinet.backendspring.entity.Medicament;
import net.cabinet.backendspring.mapper.MedicamentMapper;
import net.cabinet.backendspring.repository.MedicamentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentService {

    private final MedicamentMapper mapper;
    private final MedicamentRepo medicamentRepo;

    public MedicamentService(MedicamentMapper mapper, MedicamentRepo medicamentRepo) {
        this.mapper = mapper;
        this.medicamentRepo = medicamentRepo;
    }

    public MedicamentDto createMedicament(MedicamentDto dto){
        Medicament medicament = mapper.toEntity(dto);
        Medicament saved= medicamentRepo.save(medicament);
        return mapper.toDto(saved);
    }

    public MedicamentDto updateMedicament(MedicamentDto dto , Long id){
        Medicament medicament = medicamentRepo.findById(id).orElseThrow(()->new RuntimeException("Medicament Not Found"));
        mapper.updateEntity(medicament,dto);
        Medicament updated = medicamentRepo.save(medicament);
        return mapper.toDto(updated);
    }

    public MedicamentDto getMedicamentById(Long id){
        return medicamentRepo.findById(id).map(mapper::toDto).orElseThrow(()->new RuntimeException("Medicament Not Found"));
    }

    public List<MedicamentDto> getAllMedicament(){
        return mapper.toDtoList(medicamentRepo.findAll());
    }

    public void deleteMedicament(Long id){
        Medicament medicament = medicamentRepo.findById(id).orElseThrow(()->new RuntimeException("Medicament Not Found"));
        medicamentRepo.delete(medicament);
    }

}
