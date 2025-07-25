package com.cmr.dossier_retraite.service;


import com.cmr.dossier_retraite.dto.DossierDTO;
import com.cmr.dossier_retraite.mapper.DossierMapper;
import com.cmr.dossier_retraite.model.DossierRetraite;
import com.cmr.dossier_retraite.repository.DossierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DossierService {

    @Autowired
    private DossierRepository dossierRepository;

    @Autowired
    private DossierMapper dossierMapper;

    public DossierDTO creerDossier(DossierDTO dossierDTO) {
        DossierRetraite entity = dossierMapper.toEntity(dossierDTO);
        DossierRetraite savedEntity = dossierRepository.save(entity);
        return dossierMapper.toDTO(savedEntity);
    }

    public List<DossierDTO> listerTousDossiers() {
        List<DossierRetraite> entities = dossierRepository.findAll();
        return dossierMapper.toDTOList(entities);
    }

    public Optional<DossierDTO> obtenirDossierParId(Long id) {
        Optional<DossierRetraite> entity = dossierRepository.findById(id);
        return entity.map(dossierMapper::toDTO);
    }

    public Optional<DossierDTO> obtenirDossierParNumero(String numeroDossier) {
        Optional<DossierRetraite> entity = dossierRepository.findByNumeroDossier(numeroDossier);
        return entity.map(dossierMapper::toDTO);
    }

    public DossierDTO mettreAJourDossier(Long id, DossierDTO dossierDTO) {
        Optional<DossierRetraite> existingEntity = dossierRepository.findById(id);
        if (existingEntity.isPresent()) {
            DossierRetraite entity = existingEntity.get();
            entity.setNumeroDossier(dossierDTO.getNumeroDossier());
            entity.setNomDemandeur(dossierDTO.getNomDemandeur());
            entity.setPrenomDemandeur(dossierDTO.getPrenomDemandeur());
            entity.setCin(dossierDTO.getCin());
            entity.setDateNaissance(dossierDTO.getDateNaissance());
            entity.setDateDepotDemande(dossierDTO.getDateDepotDemande());
            entity.setStatut(dossierDTO.getStatut());
            entity.setObservations(dossierDTO.getObservations());
            
            DossierRetraite savedEntity = dossierRepository.save(entity);
            return dossierMapper.toDTO(savedEntity);
        }
        return null;
    }

    public boolean supprimerDossier(Long id) {
        if (dossierRepository.existsById(id)) {
            dossierRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<DossierDTO> rechercherParStatut(DossierRetraite.StatutDossier statut) {
        List<DossierRetraite> entities = dossierRepository.findByStatut(statut);
        return dossierMapper.toDTOList(entities);
    }

    public List<DossierDTO> rechercherParNom(String nom) {
        List<DossierRetraite> entities = dossierRepository.findByNomDemandeurContainingIgnoreCase(nom);
        return dossierMapper.toDTOList(entities);
    }
}
