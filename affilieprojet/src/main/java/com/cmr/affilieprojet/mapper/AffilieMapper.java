package com.cmr.affilieprojet.mapper;

import com.cmr.affilieprojet.dto.AffilieDTO;
import com.cmr.affilieprojet.model.Affilie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AffilieMapper {
    
    @Autowired
    private EnfantMapper enfantMapper;
    
    public AffilieDTO toDTO(Affilie affilie) {
        if (affilie == null) return null;
        
        AffilieDTO dto = new AffilieDTO();
        dto.setId(affilie.getId());
        dto.setNumeroAffilie(affilie.getNumeroAffilie());
        dto.setNom(affilie.getNom());
        dto.setPrenom(affilie.getPrenom());
        dto.setCin(affilie.getCin());
        dto.setDateNaissance(affilie.getDateNaissance());
        dto.setAdresse(affilie.getAdresse());
        dto.setTelephone(affilie.getTelephone());
        dto.setEmail(affilie.getEmail());
        dto.setGrade(affilie.getGrade());
        dto.setAdministration(affilie.getAdministration());
        dto.setDateAffiliation(affilie.getDateAffiliation());
        
        if (affilie.getEnfants() != null) {
            dto.setEnfants(affilie.getEnfants().stream()
                .map(enfantMapper::toDTO)
                .collect(Collectors.toList()));
        }
        
        return dto;
    }
    
    public Affilie toEntity(AffilieDTO dto) {
        if (dto == null) return null;
        
        Affilie affilie = new Affilie();
        affilie.setId(dto.getId());
        affilie.setNumeroAffilie(dto.getNumeroAffilie());
        affilie.setNom(dto.getNom());
        affilie.setPrenom(dto.getPrenom());
        affilie.setCin(dto.getCin());
        affilie.setDateNaissance(dto.getDateNaissance());
        affilie.setAdresse(dto.getAdresse());
        affilie.setTelephone(dto.getTelephone());
        affilie.setEmail(dto.getEmail());
        affilie.setGrade(dto.getGrade());
        affilie.setAdministration(dto.getAdministration());
        affilie.setDateAffiliation(dto.getDateAffiliation());
        
        return affilie;
    }
    
    public List<AffilieDTO> toDTOList(List<Affilie> affilies) {
        return affilies.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
}
