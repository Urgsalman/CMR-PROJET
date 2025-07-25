package com.cmr.affilieprojet.controller;

import com.cmr.affilieprojet.dto.AffilieDTO;
import com.cmr.affilieprojet.service.AffilieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/affilies")
@CrossOrigin(origins = "*")
@Validated
public class AffilieController {
    
    private static final Logger logger = LoggerFactory.getLogger(AffilieController.class);
    
    @Autowired
    private AffilieService affilieService;
    
    @PostMapping
    public ResponseEntity<?> createAffilie(@Valid @RequestBody AffilieDTO affilieDTO) {
        logger.info("Réception d'une demande de création d'affilié: {}", affilieDTO.getNumeroAffilie());
        
        try {
            AffilieDTO createdAffilie = affilieService.createAffilie(affilieDTO);
            logger.info("Affilié créé avec succès via API - ID: {}", createdAffilie.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAffilie);
        } catch (IllegalArgumentException e) {
            logger.error("Erreur de validation lors de la création: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Erreur inattendue lors de la création de l'affilié", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création de l'affilié: " + e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getAllAffilies() {
        logger.info("Réception d'une demande de récupération de tous les affiliés");
        
        try {
            List<AffilieDTO> affilies = affilieService.getAllAffilies();
            logger.info("Récupération réussie de {} affiliés via API", affilies.size());
            return ResponseEntity.ok(affilies);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des affiliés", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des affiliés: " + e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getAffilieById(@PathVariable Long id) {
        logger.info("Réception d'une demande de récupération de l'affilié ID: {}", id);
        
        try {
            Optional<AffilieDTO> affilie = affilieService.getAffilieById(id);
            if (affilie.isPresent()) {
                logger.info("Affilié trouvé via API - ID: {}", id);
                return ResponseEntity.ok(affilie.get());
            } else {
                logger.warn("Affilié non trouvé via API - ID: {}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de l'affilié ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération de l'affilié: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAffilie(@PathVariable Long id, @Valid @RequestBody AffilieDTO affilieDTO) {
        logger.info("Réception d'une demande de mise à jour de l'affilié ID: {}", id);
        
        try {
            AffilieDTO updatedAffilie = affilieService.updateAffilie(id, affilieDTO);
            if (updatedAffilie != null) {
                logger.info("Affilié mis à jour avec succès via API - ID: {}", id);
                return ResponseEntity.ok(updatedAffilie);
            } else {
                logger.warn("Affilié non trouvé pour la mise à jour via API - ID: {}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Erreur lors de la mise à jour de l'affilié ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la mise à jour de l'affilié: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAffilie(@PathVariable Long id) {
        logger.info("Réception d'une demande de suppression de l'affilié ID: {}", id);
        
        try {
            boolean deleted = affilieService.deleteAffilie(id);
            if (deleted) {
                logger.info("Affilié supprimé avec succès via API - ID: {}", id);
                return ResponseEntity.noContent().build();
            } else {
                logger.warn("Affilié non trouvé pour la suppression via API - ID: {}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression de l'affilié ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression de l'affilié: " + e.getMessage());
        }
    }
}