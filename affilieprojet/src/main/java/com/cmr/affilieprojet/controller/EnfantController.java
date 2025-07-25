package com.cmr.affilieprojet.controller;

import com.cmr.affilieprojet.dto.EnfantDTO;
import com.cmr.affilieprojet.service.EnfantService;
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
@RequestMapping("/api/enfants")
@CrossOrigin(origins = "*")
@Validated
public class EnfantController {
    
    private static final Logger logger = LoggerFactory.getLogger(EnfantController.class);
    
    @Autowired
    private EnfantService enfantService;
    
    @PostMapping("/{affilieId}")
    public ResponseEntity<?> createEnfant(@PathVariable Long affilieId, @Valid @RequestBody EnfantDTO enfantDTO) {
        logger.info("Réception d'une demande de création d'enfant pour l'affilié ID: {}", affilieId);
        
        try {
            EnfantDTO createdEnfant = enfantService.createEnfant(affilieId, enfantDTO);
            logger.info("Enfant créé avec succès via API - ID: {}", createdEnfant.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEnfant);
        } catch (IllegalArgumentException e) {
            logger.error("Erreur de validation lors de la création d'enfant: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Erreur inattendue lors de la création de l'enfant", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création de l'enfant: " + e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getAllEnfants() {
        logger.info("Réception d'une demande de récupération de tous les enfants");
        
        try {
            List<EnfantDTO> enfants = enfantService.getAllEnfants();
            logger.info("Récupération réussie de {} enfants via API", enfants.size());
            return ResponseEntity.ok(enfants);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des enfants", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des enfants: " + e.getMessage());
        }
    }
    
    @GetMapping("/affilie/{affilieId}")
    public ResponseEntity<?> getEnfantsByAffilie(@PathVariable Long affilieId) {
        logger.info("Réception d'une demande de récupération des enfants pour l'affilié ID: {}", affilieId);
        
        try {
            List<EnfantDTO> enfants = enfantService.getEnfantsByAffilie(affilieId);
            logger.info("Récupération réussie de {} enfants pour l'affilié ID: {}", enfants.size(), affilieId);
            return ResponseEntity.ok(enfants);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des enfants pour l'affilié ID: {}", affilieId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des enfants: " + e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getEnfantById(@PathVariable Long id) {
        logger.info("Réception d'une demande de récupération de l'enfant ID: {}", id);
        
        try {
            Optional<EnfantDTO> enfant = enfantService.getEnfantById(id);
            if (enfant.isPresent()) {
                logger.info("Enfant trouvé via API - ID: {}", id);
                return ResponseEntity.ok(enfant.get());
            } else {
                logger.warn("Enfant non trouvé via API - ID: {}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de l'enfant ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération de l'enfant: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEnfant(@PathVariable Long id, @Valid @RequestBody EnfantDTO enfantDTO) {
        logger.info("Réception d'une demande de mise à jour de l'enfant ID: {}", id);
        
        try {
            EnfantDTO updatedEnfant = enfantService.updateEnfant(id, enfantDTO);
            if (updatedEnfant != null) {
                logger.info("Enfant mis à jour avec succès via API - ID: {}", id);
                return ResponseEntity.ok(updatedEnfant);
            } else {
                logger.warn("Enfant non trouvé pour la mise à jour via API - ID: {}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Erreur lors de la mise à jour de l'enfant ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la mise à jour de l'enfant: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnfant(@PathVariable Long id) {
        logger.info("Réception d'une demande de suppression de l'enfant ID: {}", id);
        
        try {
            boolean deleted = enfantService.deleteEnfant(id);
            if (deleted) {
                logger.info("Enfant supprimé avec succès via API - ID: {}", id);
                return ResponseEntity.noContent().build();
            } else {
                logger.warn("Enfant non trouvé pour la suppression via API - ID: {}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression de l'enfant ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression de l'enfant: " + e.getMessage());
        }
    }
}