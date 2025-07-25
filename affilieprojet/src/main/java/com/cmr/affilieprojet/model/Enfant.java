package com.cmr.affilieprojet.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "enfants")
public class Enfant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nom", nullable = false)
    private String nom;
    
    @Column(name = "prenom", nullable = false)
    private String prenom;
    
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;
    
    @Column(name = "sexe", nullable = false)
    private String sexe;
    
    @Column(name = "situation_scolaire", nullable = false)
    private String situationScolaire;
    
    @Column(name = "est_a_charge", nullable = false)
    private Boolean estACharge;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "affilie_id", nullable = false)
    private Affilie affilie;
    
    // Constructeurs
    public Enfant() {}
    
    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    
    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }
    
    public String getSituationScolaire() { return situationScolaire; }
    public void setSituationScolaire(String situationScolaire) { this.situationScolaire = situationScolaire; }
    
    public Boolean getEstACharge() { return estACharge; }
    public void setEstACharge(Boolean estACharge) { this.estACharge = estACharge; }
    
    public Affilie getAffilie() { return affilie; }
    public void setAffilie(Affilie affilie) { this.affilie = affilie; }
}