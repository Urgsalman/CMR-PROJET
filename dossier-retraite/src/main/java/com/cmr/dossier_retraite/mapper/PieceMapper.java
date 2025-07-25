package com.cmr.dossier_retraite.mapper;


import com.cmr.dossier_retraite.dto.PieceDTO;
import com.cmr.dossier_retraite.model.PieceJustificative;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PieceMapper {

    public PieceDTO toDTO(PieceJustificative entity) {
        if (entity == null) return null;
        
        PieceDTO dto = new PieceDTO();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setType(entity.getType());
        dto.setFormat(entity.getFormat());
        dto.setDateAjout(entity.getDateAjout());
        dto.setObligatoire(entity.getObligatoire());
        dto.setDescription(entity.getDescription());
        
        if (entity.getDossierRetraite() != null) {
            dto.setDossierId(entity.getDossierRetraite().getId());
        }
        
        return dto;
    }

    public PieceJustificative toEntity(PieceDTO dto) {
        if (dto == null) return null;
        
        PieceJustificative entity = new PieceJustificative();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setType(dto.getType());
        entity.setFormat(dto.getFormat());
        entity.setDateAjout(dto.getDateAjout());
        entity.setObligatoire(dto.getObligatoire());
        entity.setDescription(dto.getDescription());
        
        return entity;
    }

    public List<PieceDTO> toDTOList(List<PieceJustificative> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

