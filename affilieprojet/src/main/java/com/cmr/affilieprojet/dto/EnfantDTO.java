package com.cmr.affilieprojet.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class EnfantDTO {
    private Long id;
    
    @NotBlank(message = "Le nom est requis")
    private String nom;
    
    @NotBlank(message = "Le prénom est requis")
    private String prenom;
    
    @NotNull(message = "La date de naissance est requise")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;
    
    @NotBlank(message = "Le sexe est requis")
    private String sexe;
    
    @NotBlank(message = "La situation scolaire est requise")
    private String situationScolaire;
    
    @NotNull(message = "Le statut à charge est requis")
    private Boolean estACharge;
    
    private Long affilieId;
    
    // Constructeurs
    public EnfantDTO() {}
    
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
    
    public Long getAffilieId() { return affilieId; }
    public void setAffilieId(Long affilieId) { this.affilieId = affilieId; }
}
