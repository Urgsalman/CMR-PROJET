package com.cmr.dossier_retraite.repository;

import com.cmr.dossier_retraite.model.DossierRetraite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DossierRepository extends JpaRepository<DossierRetraite, Long> {

    Optional<DossierRetraite> findByNumeroDossier(String numeroDossier);

    List<DossierRetraite> findByStatut(DossierRetraite.StatutDossier statut);

    List<DossierRetraite> findByNomDemandeurContainingIgnoreCase(String nom);

    List<DossierRetraite> findByCin(String cin);

    List<DossierRetraite> findByPrenomDemandeurContainingIgnoreCase(String prenom);

    List<DossierRetraite> findByNomDemandeurContainingIgnoreCaseAndPrenomDemandeurContainingIgnoreCase(String nom, String prenom);
}
