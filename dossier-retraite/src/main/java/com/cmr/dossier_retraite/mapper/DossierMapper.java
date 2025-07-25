package com.cmr.dossier_retraite.mapper;


import com.cmr.dossier_retraite.dto.DossierDTO;
import com.cmr.dossier_retraite.model.DossierRetraite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DossierMapper {

    @Autowired
    private PieceMapper pieceMapper;

    public DossierDTO toDTO(DossierRetraite entity) {
        if (entity == null) return null;
        
        DossierDTO dto = new DossierDTO();
        dto.setId(entity.getId());
        dto.setNumeroDossier(entity.getNumeroDossier());
        dto.setNomDemandeur(entity.getNomDemandeur());
        dto.setPrenomDemandeur(entity.getPrenomDemandeur());
        dto.setCin(entity.getCin());
        dto.setDateNaissance(entity.getDateNaissance());
        dto.setDateDepotDemande(entity.getDateDepotDemande());
        dto.setStatut(entity.getStatut());
        dto.setObservations(entity.getObservations());
        
        if (entity.getPieces() != null) {
            dto.setPieces(entity.getPieces().stream()
                    .map(pieceMapper::toDTO)
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }

    public DossierRetraite toEntity(DossierDTO dto) {
        if (dto == null) return null;
        
        DossierRetraite entity = new DossierRetraite();
        entity.setId(dto.getId());
        entity.setNumeroDossier(dto.getNumeroDossier());
        entity.setNomDemandeur(dto.getNomDemandeur());
        entity.setPrenomDemandeur(dto.getPrenomDemandeur());
        entity.setCin(dto.getCin());
        entity.setDateNaissance(dto.getDateNaissance());
        entity.setDateDepotDemande(dto.getDateDepotDemande());
        entity.setStatut(dto.getStatut());
        entity.setObservations(dto.getObservations());
        
        return entity;
    }

    public List<DossierDTO> toDTOList(List<DossierRetraite> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
