package com.cmr.affilieprojet.mapper;

import com.cmr.affilieprojet.dto.EnfantDTO;
import com.cmr.affilieprojet.model.Enfant;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnfantMapper {
    
    public EnfantDTO toDTO(Enfant enfant) {
        if (enfant == null) return null;
        
        EnfantDTO dto = new EnfantDTO();
        dto.setId(enfant.getId());
        dto.setNom(enfant.getNom());
        dto.setPrenom(enfant.getPrenom());
        dto.setDateNaissance(enfant.getDateNaissance());
        dto.setSexe(enfant.getSexe());
        dto.setSituationScolaire(enfant.getSituationScolaire());
        dto.setEstACharge(enfant.getEstACharge());
        
        if (enfant.getAffilie() != null) {
            dto.setAffilieId(enfant.getAffilie().getId());
        }
        
        return dto;
    }
    
    public Enfant toEntity(EnfantDTO dto) {
        if (dto == null) return null;
        
        Enfant enfant = new Enfant();
        enfant.setId(dto.getId());
        enfant.setNom(dto.getNom());
        enfant.setPrenom(dto.getPrenom());
        enfant.setDateNaissance(dto.getDateNaissance());
        enfant.setSexe(dto.getSexe());
        enfant.setSituationScolaire(dto.getSituationScolaire());
        enfant.setEstACharge(dto.getEstACharge());
        
        return enfant;
    }
    
    public List<EnfantDTO> toDTOList(List<Enfant> enfants) {
        return enfants.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
}
