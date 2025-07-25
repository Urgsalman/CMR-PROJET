package com.cmr.dossier_retraite.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "piece_justificative")
public class PieceJustificative {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypePiece type;
    
    @Column(nullable = false)
    private String format;
    
    @Column(nullable = false)
    private LocalDate dateAjout;
    
    @Column(nullable = false)
    private Boolean obligatoire;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dossier_id", nullable = false)
    @JsonBackReference
    private DossierRetraite dossierRetraite;
    
    // Enum pour le type de pièce
    public enum TypePiece {
        COPIE_CIN,
        CERTIFICAT_TRAVAIL,
        ATTESTATION_SALAIRE,
        ACTE_NAISSANCE,
        JUSTIFICATIF_DOMICILE,
        PHOTO_IDENTITE,
        RELEVÉ_BANCAIRE,
        AUTRE
    }
    
    // Constructeurs
    public PieceJustificative() {}
    
    public PieceJustificative(String nom, TypePiece type, String format, 
                             LocalDate dateAjout, Boolean obligatoire, String description) {
        this.nom = nom;
        this.type = type;
        this.format = format;
        this.dateAjout = dateAjout;
        this.obligatoire = obligatoire;
        this.description = description;
    }
    
    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public TypePiece getType() { return type; }
    public void setType(TypePiece type) { this.type = type; }
    
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
    
    public LocalDate getDateAjout() { return dateAjout; }
    public void setDateAjout(LocalDate dateAjout) { this.dateAjout = dateAjout; }
    
    public Boolean getObligatoire() { return obligatoire; }
    public void setObligatoire(Boolean obligatoire) { this.obligatoire = obligatoire; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public DossierRetraite getDossierRetraite() { return dossierRetraite; }
    public void setDossierRetraite(DossierRetraite dossierRetraite) { this.dossierRetraite = dossierRetraite; }
}
