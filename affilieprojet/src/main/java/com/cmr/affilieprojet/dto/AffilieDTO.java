package com.cmr.affilieprojet.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class AffilieDTO {
    private Long id;
    
    @NotBlank(message = "Le numéro d'affilié est requis")
    private String numeroAffilie;
    
    @NotBlank(message = "Le nom est requis")
    private String nom;
    
    @NotBlank(message = "Le prénom est requis")
    private String prenom;
    
    @NotBlank(message = "Le CIN est requis")
    private String cin;
    
    @NotNull(message = "La date de naissance est requise")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;
    
    @NotBlank(message = "L'adresse est requise")
    private String adresse;
    
    @NotBlank(message = "Le téléphone est requis")
    private String telephone;
    
    @NotBlank(message = "L'email est requis")
    @Email(message = "Format d'email invalide")
    private String email;
    
    @NotBlank(message = "Le grade est requis")
    private String grade;
    
    @NotBlank(message = "L'administration est requise")
    private String administration;
    
    @NotNull(message = "La date d'affiliation est requise")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateAffiliation;
    
    private List<EnfantDTO> enfants;
    
    // Constructeurs
    public AffilieDTO() {}
    
    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNumeroAffilie() { return numeroAffilie; }
    public void setNumeroAffilie(String numeroAffilie) { this.numeroAffilie = numeroAffilie; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    
    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }
    
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    
    public String getAdministration() { return administration; }
    public void setAdministration(String administration) { this.administration = administration; }
    
    public LocalDate getDateAffiliation() { return dateAffiliation; }
    public void setDateAffiliation(LocalDate dateAffiliation) { this.dateAffiliation = dateAffiliation; }
    
    public List<EnfantDTO> getEnfants() { return enfants; }
    public void setEnfants(List<EnfantDTO> enfants) { this.enfants = enfants; }
}
