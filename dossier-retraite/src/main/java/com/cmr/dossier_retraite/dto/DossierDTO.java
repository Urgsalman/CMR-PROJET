package com.cmr.dossier_retraite.dto;

import com.cmr.dossier_retraite.model.DossierRetraite;
import java.time.LocalDate;
import java.util.List;

public class DossierDTO {
    private Long id;
    private String numeroDossier;
    private String nomDemandeur;
    private String prenomDemandeur;
    private String cin;
    private LocalDate dateNaissance;
    private LocalDate dateDepotDemande;
    private DossierRetraite.StatutDossier statut;
    private String observations;
    private List<PieceDTO> pieces;

    // Constructeurs
    public DossierDTO() {}

    public DossierDTO(Long id, String numeroDossier, String nomDemandeur, String prenomDemandeur,
                     String cin, LocalDate dateNaissance, LocalDate dateDepotDemande,
                     DossierRetraite.StatutDossier statut, String observations) {
        this.id = id;
        this.numeroDossier = numeroDossier;
        this.nomDemandeur = nomDemandeur;
        this.prenomDemandeur = prenomDemandeur;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
        this.dateDepotDemande = dateDepotDemande;
        this.statut = statut;
        this.observations = observations;
    }

    // Getters et Setters
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

    public DossierRetraite.StatutDossier getStatut() { return statut; }
    public void setStatut(DossierRetraite.StatutDossier statut) { this.statut = statut; }

    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }

    public List<PieceDTO> getPieces() { return pieces; }
    public void setPieces(List<PieceDTO> pieces) { this.pieces = pieces; }
}
