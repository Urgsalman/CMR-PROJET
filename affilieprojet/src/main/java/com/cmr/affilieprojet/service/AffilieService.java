package com.cmr.affilieprojet.service;

import com.cmr.affilieprojet.dto.AffilieDTO;
import com.cmr.affilieprojet.mapper.AffilieMapper;
import com.cmr.affilieprojet.model.Affilie;
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
public class AffilieService {
    
    private static final Logger logger = LoggerFactory.getLogger(AffilieService.class);
    
    @Autowired
    private AffilieRepository affilieRepository;
    
    @Autowired
    private AffilieMapper affilieMapper;
    
    public AffilieDTO createAffilie(AffilieDTO affilieDTO) {
        logger.info("Création d'un nouvel affilié avec le numéro: {}", affilieDTO.getNumeroAffilie());
        
        // Validation des contraintes métier
        validateUniqueConstraints(affilieDTO);
        
        Affilie affilie = affilieMapper.toEntity(affilieDTO);
        Affilie savedAffilie = affilieRepository.save(affilie);
        
        logger.info("Affilié créé avec succès - ID: {}", savedAffilie.getId());
        return affilieMapper.toDTO(savedAffilie);
    }
    
    @Transactional(readOnly = true)
    public List<AffilieDTO> getAllAffilies() {
        logger.info("Récupération de tous les affiliés");
        List<Affilie> affilies = affilieRepository.findAll();
        logger.info("Nombre d'affiliés trouvés: {}", affilies.size());
        return affilieMapper.toDTOList(affilies);
    }
    
    @Transactional(readOnly = true)
    public Optional<AffilieDTO> getAffilieById(Long id) {
        logger.info("Recherche de l'affilié avec l'ID: {}", id);
        Optional<Affilie> affilie = affilieRepository.findById(id);
        
        if (affilie.isPresent()) {
            logger.info("Affilié trouvé: {}", affilie.get().getNumeroAffilie());
            return Optional.of(affilieMapper.toDTO(affilie.get()));
        } else {
            logger.warn("Aucun affilié trouvé avec l'ID: {}", id);
            return Optional.empty();
        }
    }
    
    public AffilieDTO updateAffilie(Long id, AffilieDTO affilieDTO) {
        logger.info("Mise à jour de l'affilié avec l'ID: {}", id);
        
        Optional<Affilie> existingAffilie = affilieRepository.findById(id);
        if (existingAffilie.isEmpty()) {
            logger.warn("Affilié non trouvé pour la mise à jour - ID: {}", id);
            return null;
        }
        
        Affilie affilieToUpdate = existingAffilie.get();
        updateAffilieFields(affilieToUpdate, affilieDTO);
        
        Affilie updatedAffilie = affilieRepository.save(affilieToUpdate);
        logger.info("Affilié mis à jour avec succès - ID: {}", updatedAffilie.getId());
        
        return affilieMapper.toDTO(updatedAffilie);
    }
    
    public boolean deleteAffilie(Long id) {
        logger.info("Suppression de l'affilié avec l'ID: {}", id);
        
        if (!affilieRepository.existsById(id)) {
            logger.warn("Affilié non trouvé pour la suppression - ID: {}", id);
            return false;
        }
        
        affilieRepository.deleteById(id);
        logger.info("Affilié supprimé avec succès - ID: {}", id);
        return true;
    }
    
    private void validateUniqueConstraints(AffilieDTO affilieDTO) {
        if (affilieRepository.existsByNumeroAffilie(affilieDTO.getNumeroAffilie())) {
            logger.error("Numéro d'affilié déjà existant: {}", affilieDTO.getNumeroAffilie());
            throw new IllegalArgumentException("Ce numéro d'affilié existe déjà");
        }
        
        if (affilieRepository.existsByCin(affilieDTO.getCin())) {
            logger.error("CIN déjà existant: {}", affilieDTO.getCin());
            throw new IllegalArgumentException("Ce CIN existe déjà");
        }
        
        if (affilieRepository.existsByEmail(affilieDTO.getEmail())) {
            logger.error("Email déjà existant: {}", affilieDTO.getEmail());
            throw new IllegalArgumentException("Cet email existe déjà");
        }
    }
    
    private void updateAffilieFields(Affilie affilieToUpdate, AffilieDTO affilieDTO) {
        if (affilieDTO.getNom() != null) affilieToUpdate.setNom(affilieDTO.getNom());
        if (affilieDTO.getPrenom() != null) affilieToUpdate.setPrenom(affilieDTO.getPrenom());
        if (affilieDTO.getAdresse() != null) affilieToUpdate.setAdresse(affilieDTO.getAdresse());
        if (affilieDTO.getTelephone() != null) affilieToUpdate.setTelephone(affilieDTO.getTelephone());
        if (affilieDTO.getEmail() != null) affilieToUpdate.setEmail(affilieDTO.getEmail());
        if (affilieDTO.getGrade() != null) affilieToUpdate.setGrade(affilieDTO.getGrade());
        if (affilieDTO.getAdministration() != null) affilieToUpdate.setAdministration(affilieDTO.getAdministration());
    }
}
