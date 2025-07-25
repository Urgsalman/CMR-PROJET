package com.cmr.dossier_retraite.repository;

import com.cmr.dossier_retraite.model.PieceJustificative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PieceRepository extends JpaRepository<PieceJustificative, Long> {
    
    List<PieceJustificative> findByDossierRetraiteId(Long dossierId);
    
    List<PieceJustificative> findByType(PieceJustificative.TypePiece type);
    
    List<PieceJustificative> findByObligatoire(Boolean obligatoire);
    
    List<PieceJustificative> findByDossierRetraiteIdAndObligatoire(Long dossierId, Boolean obligatoire);
    
    List<PieceJustificative> findByNomContainingIgnoreCase(String nom);
}