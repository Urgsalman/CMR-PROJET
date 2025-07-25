package com.cmr.dossier_retraite.dto;


import com.cmr.dossier_retraite.model.PieceJustificative;
import java.time.LocalDate;

public class PieceDTO {
    private Long id;
    private String nom;
    private PieceJustificative.TypePiece type;
    private String format;
    private LocalDate dateAjout;
    private Boolean obligatoire;
    private String description;
    private Long dossierId;

    // Constructeurs
    public PieceDTO() {}

    public PieceDTO(Long id, String nom, PieceJustificative.TypePiece type, String format,
                   LocalDate dateAjout, Boolean obligatoire, String description, Long dossierId) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.format = format;
        this.dateAjout = dateAjout;
        this.obligatoire = obligatoire;
        this.description = description;
        this.dossierId = dossierId;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public PieceJustificative.TypePiece getType() { return type; }
    public void setType(PieceJustificative.TypePiece type) { this.type = type; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    public LocalDate getDateAjout() { return dateAjout; }
    public void setDateAjout(LocalDate dateAjout) { this.dateAjout = dateAjout; }

    public Boolean getObligatoire() { return obligatoire; }
    public void setObligatoire(Boolean obligatoire) { this.obligatoire = obligatoire; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Long getDossierId() { return dossierId; }
    public void setDossierId(Long dossierId) { this.dossierId = dossierId; }
}

