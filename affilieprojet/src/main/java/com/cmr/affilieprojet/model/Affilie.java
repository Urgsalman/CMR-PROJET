package com.cmr.affilieprojet.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "affilies")
public class Affilie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numero_affilie", unique = true, nullable = false)
    private String numeroAffilie;
    
    @Column(name = "nom", nullable = false)
    private String nom;
    
    @Column(name = "prenom", nullable = false)
    private String prenom;
    
    @Column(name = "cin", unique = true, nullable = false)
    private String cin;
    
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;
    
    @Column(name = "adresse", nullable = false)
    private String adresse;
    
    @Column(name = "telephone", nullable = false)
    private String telephone;
    
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    @Column(name = "grade", nullable = false)
    private String grade;
    
    @Column(name = "administration", nullable = false)
    private String administration;
    
    @Column(name = "date_affiliation", nullable = false)
    private LocalDate dateAffiliation;
    
    @OneToMany(mappedBy = "affilie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enfant> enfants;
    
    // Constructeurs
    public Affilie() {}
    
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
    
    public List<Enfant> getEnfants() { return enfants; }
    public void setEnfants(List<Enfant> enfants) { this.enfants = enfants; }
}