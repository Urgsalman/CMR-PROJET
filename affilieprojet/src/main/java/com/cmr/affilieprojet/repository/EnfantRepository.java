package com.cmr.affilieprojet.repository;

import com.cmr.affilieprojet.model.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnfantRepository extends JpaRepository<Enfant, Long> {
    
    List<Enfant> findByAffilieId(Long affilieId);
    
    List<Enfant> findByEstACharge(boolean estACharge);
    
    List<Enfant> findBySexe(String sexe);
    
    List<Enfant> findBySituationScolaire(String situationScolaire);
    
    List<Enfant> findByNomContainingIgnoreCase(String nom);
    
    List<Enfant> findByPrenomContainingIgnoreCase(String prenom);
    
    @Query("SELECT e FROM Enfant e WHERE e.affilie.id = :affilieId AND e.estACharge = true")
    List<Enfant> findEnfantsAChargeByAffilie(@Param("affilieId") Long affilieId);
    
    @Query("SELECT COUNT(e) FROM Enfant e WHERE e.affilie.id = :affilieId")
    long countByAffilieId(@Param("affilieId") Long affilieId);
}
