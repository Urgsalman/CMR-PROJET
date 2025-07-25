package com.cmr.dossier_retraite.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "dossier_retraite")
public class DossierRetraite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroDossier;

    @Column(nullable = false)
    private String nomDemandeur;

    @Column(nullable = false)
    private String prenomDemandeur;

    @Column(nullable = false)
    private String cin;

    @Column(nullable = false)
    private LocalDate dateNaissance;

    @Column(nullable = false)
    private LocalDate dateDepotDemande;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutDossier statut;

    @Column(columnDefinition = "TEXT")
    private String observations;

    @OneToMany(mappedBy = "dossierRetraite", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PieceJustificative> pieces;

    public enum StatutDossier {
        EN_ATTENTE, EN_COURS, COMPLETE, VALIDEE, REJETEE
    }
    
    // Constructeurs
    public DossierRetraite() {}
    
    public DossierRetraite(String numeroDossier, String nomDemandeur, String prenomDemandeur, 
                          String cin, LocalDate dateNaissance, LocalDate dateDepotDemande, 
                          StatutDossier statut, String observations) {
        this.numeroDossier = numeroDossier;
        this.nomDemandeur = nomDemandeur;
        this.prenomDemandeur = prenomDemandeur;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
        this.dateDepotDemande = dateDepotDemande;
        this.statut = statut;
        this.observations = observations;
    }
    
    // Getters et Setters (identiques à l'original)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNumeroDossier() { return numeroDossier; }
    public void setNumeroDossier(String numeroDossier) { this.numeroDossier = numeroDossier; }
    
    public String getNomDemandeur() { return nomDemandeur; }
    public void setNomDemandeur(String nomDemandeur) { this.nomDemandeur = nomDemandeur; }
    
    public String getPrenomDemandeur() { return prenomDemandeur; }
    public void setPrenomDemandeur(String prenomDemandeur) { this.prenomDemandeur = prenomDemandeur; }
    
    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }
    
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    
    public LocalDate getDateDepotDemande() { return dateDepotDemande; }
    public void setDateDepotDemande(LocalDate dateDepotDemande) { this.dateDepotDemande = dateDepotDemande; }
    
    public StatutDossier getStatut() { return statut; }
    public void setStatut(StatutDossier statut) { this.statut = statut; }
    
    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }
    
    public List<PieceJustificative> getPieces() { return pieces; }
    public void setPieces(List<PieceJustificative> pieces) { this.pieces = pieces; }
}

