package com.cmr.affilieprojet.service;

import com.cmr.affilieprojet.dto.EnfantDTO;
import com.cmr.affilieprojet.mapper.EnfantMapper;
import com.cmr.affilieprojet.model.Enfant;
import com.cmr.affilieprojet.model.Affilie;
import com.cmr.affilieprojet.repository.EnfantRepository;
import com.cmr.affilieprojet.repository.AffilieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EnfantService {
    
    private static final Logger logger = LoggerFactory.getLogger(EnfantService.class);
    
    @Autowired
    private EnfantRepository enfantRepository;
    
    @Autowired
    private AffilieRepository affilieRepository;
    
    @Autowired
    private EnfantMapper enfantMapper;
    
    public EnfantDTO createEnfant(Long affilieId, EnfantDTO enfantDTO) {
        logger.info("Création d'un nouvel enfant pour l'affilié ID: {}", affilieId);
        
        Optional<Affilie> affilie = affilieRepository.findById(affilieId);
        if (affilie.isEmpty()) {
            logger.error("Affilié non trouvé pour l'ID: {}", affilieId);
            throw new IllegalArgumentException("Affilié non trouvé");
        }
        
        Enfant enfant = enfantMapper.toEntity(enfantDTO);
        enfant.setAffilie(affilie.get());
        
        Enfant savedEnfant = enfantRepository.save(enfant);
        logger.info("Enfant créé avec succès - ID: {}", savedEnfant.getId());
        
        return enfantMapper.toDTO(savedEnfant);
    }
    
    @Transactional(readOnly = true)
    public List<EnfantDTO> getAllEnfants() {
        logger.info("Récupération de tous les enfants");
        List<Enfant> enfants = enfantRepository.findAll();
        logger.info("Nombre d'enfants trouvés: {}", enfants.size());
        return enfantMapper.toDTOList(enfants);
    }
    
    @Transactional(readOnly = true)
    public List<EnfantDTO> getEnfantsByAffilie(Long affilieId) {
        logger.info("Récupération des enfants pour l'affilié ID: {}", affilieId);
        List<Enfant> enfants = enfantRepository.findByAffilieId(affilieId);
        logger.info("Nombre d'enfants trouvés pour l'affilié {}: {}", affilieId, enfants.size());
        return enfantMapper.toDTOList(enfants);
    }
    
    @Transactional(readOnly = true)
    public Optional<EnfantDTO> getEnfantById(Long id) {
        logger.info("Recherche de l'enfant avec l'ID: {}", id);
        Optional<Enfant> enfant = enfantRepository.findById(id);
        
        if (enfant.isPresent()) {
            logger.info("Enfant trouvé: {} {}", enfant.get().getNom(), enfant.get().getPrenom());
            return Optional.of(enfantMapper.toDTO(enfant.get()));
        } else {
            logger.warn("Aucun enfant trouvé avec l'ID: {}", id);
            return Optional.empty();
        }
    }
    
    public EnfantDTO updateEnfant(Long id, EnfantDTO enfantDTO) {
        logger.info("Mise à jour de l'enfant avec l'ID: {}", id);
        
        Optional<Enfant> existingEnfant = enfantRepository.findById(id);
        if (existingEnfant.isEmpty()) {
            logger.warn("Enfant non trouvé pour la mise à jour - ID: {}", id);
            return null;
        }
        
        Enfant enfantToUpdate = existingEnfant.get();
        updateEnfantFields(enfantToUpdate, enfantDTO);
        
        Enfant updatedEnfant = enfantRepository.save(enfantToUpdate);
        logger.info("Enfant mis à jour avec succès - ID: {}", updatedEnfant.getId());
        
        return enfantMapper.toDTO(updatedEnfant);
    }
    
    public boolean deleteEnfant(Long id) {
        logger.info("Suppression de l'enfant avec l'ID: {}", id);
        
        if (!enfantRepository.existsById(id)) {
            logger.warn("Enfant non trouvé pour la suppression - ID: {}", id);
            return false;
        }
        
        enfantRepository.deleteById(id);
        logger.info("Enfant supprimé avec succès - ID: {}", id);
        return true;
    }
    
    private void updateEnfantFields(Enfant enfantToUpdate, EnfantDTO enfantDTO) {
        if (enfantDTO.getNom() != null) enfantToUpdate.setNom(enfantDTO.getNom());
        if (enfantDTO.getPrenom() != null) enfantToUpdate.setPrenom(enfantDTO.getPrenom());
        if (enfantDTO.getSexe() != null) enfantToUpdate.setSexe(enfantDTO.getSexe());
        if (enfantDTO.getSituationScolaire() != null) enfantToUpdate.setSituationScolaire(enfantDTO.getSituationScolaire());
        if (enfantDTO.getEstACharge() != null) enfantToUpdate.setEstACharge(enfantDTO.getEstACharge());
    }
}
