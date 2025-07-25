package com.cmr.dossier_retraite.controller;

import com.cmr.dossier_retraite.dto.DossierDTO;
import com.cmr.dossier_retraite.model.DossierRetraite;
import com.cmr.dossier_retraite.service.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dossiers")
public class DossierController {

    @Autowired
    private DossierService dossierService;

    @PostMapping
    public ResponseEntity<DossierDTO> creerDossier(@RequestBody DossierDTO dossierDTO) {
        try {
            DossierDTO nouveauDossier = dossierService.creerDossier(dossierDTO);
            return ResponseEntity.ok(nouveauDossier);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<DossierDTO> listerDossiers() {
        return dossierService.listerTousDossiers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DossierDTO> obtenirDossier(@PathVariable Long id) {
        Optional<DossierDTO> dossier = dossierService.obtenirDossierParId(id);
        return dossier.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/numero/{numeroDossier}")
    public ResponseEntity<DossierDTO> obtenirDossierParNumero(@PathVariable String numeroDossier) {
        Optional<DossierDTO> dossier = dossierService.obtenirDossierParNumero(numeroDossier);
        return dossier.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DossierDTO> mettreAJourDossier(@PathVariable Long id, @RequestBody DossierDTO dossierDTO) {
        DossierDTO dossierMisAJour = dossierService.mettreAJourDossier(id, dossierDTO);
        return dossierMisAJour != null ? ResponseEntity.ok(dossierMisAJour) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerDossier(@PathVariable Long id) {
        boolean supprime = dossierService.supprimerDossier(id);
        return supprime ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/statut/{statut}")
    public List<DossierDTO> rechercherParStatut(@PathVariable DossierRetraite.StatutDossier statut) {
        return dossierService.rechercherParStatut(statut);
    }

    @GetMapping("/nom/{nom}")
    public List<DossierDTO> rechercherParNom(@PathVariable String nom) {
        return dossierService.rechercherParNom(nom);
    }
}
