package com.cmr.dossier_retraite.controller;

import com.cmr.dossier_retraite.dto.PieceDTO;
import com.cmr.dossier_retraite.model.PieceJustificative;
import com.cmr.dossier_retraite.service.PieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pieces")
public class PieceController {

    @Autowired
    private PieceService pieceService;

    @PostMapping
    public ResponseEntity<PieceDTO> ajouterPiece(@RequestBody PieceDTO pieceDTO) {
        try {
            PieceDTO nouvellePiece = pieceService.ajouterPiece(pieceDTO);
            return ResponseEntity.ok(nouvellePiece);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/dossier/{dossierId}")
    public List<PieceDTO> listerPiecesParDossier(@PathVariable Long dossierId) {
        return pieceService.listerPiecesParDossier(dossierId);
    }

    @GetMapping("/type/{type}")
    public List<PieceDTO> listerPiecesParType(@PathVariable PieceJustificative.TypePiece type) {
        return pieceService.listerPiecesParType(type);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PieceDTO> obtenirPiece(@PathVariable Long id) {
        Optional<PieceDTO> piece = pieceService.obtenirPieceParId(id);
        return piece.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerPiece(@PathVariable Long id) {
        boolean supprime = pieceService.supprimerPiece(id);
        return supprime ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/dossier/{dossierId}/obligatoires")
    public List<PieceDTO> listerPiecesObligatoires(@PathVariable Long dossierId) {
        return pieceService.listerPiecesObligatoires(dossierId);
    }
}
