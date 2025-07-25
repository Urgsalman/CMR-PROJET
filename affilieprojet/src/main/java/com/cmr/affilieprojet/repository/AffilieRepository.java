package com.cmr.affilieprojet.repository;

import com.cmr.affilieprojet.model.Affilie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AffilieRepository extends JpaRepository<Affilie, Long> {
    
    boolean existsByNumeroAffilie(String numeroAffilie);
    
    boolean existsByCin(String cin);
    
    boolean existsByEmail(String email);
    
    Optional<Affilie> findByNumeroAffilie(String numeroAffilie);
    
    Optional<Affilie> findByCin(String cin);
    
    Optional<Affilie> findByEmail(String email);
    
    List<Affilie> findByNomContainingIgnoreCase(String nom);
    
    List<Affilie> findByPrenomContainingIgnoreCase(String prenom);
    
    List<Affilie> findByAdministration(String administration);
    
    List<Affilie> findByGrade(String grade);
    
    @Query("SELECT a FROM Affilie a WHERE a.nom LIKE %:nom% AND a.prenom LIKE %:prenom%")
    List<Affilie> findByNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);
}
