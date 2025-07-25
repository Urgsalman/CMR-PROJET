package com.cmr.dossier_retraite.service;

import com.cmr.dossier_retraite.dto.PieceDTO;
import com.cmr.dossier_retraite.mapper.PieceMapper;
import com.cmr.dossier_retraite.model.DossierRetraite;
import com.cmr.dossier_retraite.model.PieceJustificative;
import com.cmr.dossier_retraite.repository.DossierRepository;
import com.cmr.dossier_retraite.repository.PieceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PieceService {

    @Autowired
    private PieceRepository pieceRepository;

    @Autowired
    private DossierRepository dossierRepository;

    @Autowired
    private PieceMapper pieceMapper;

    public PieceDTO ajouterPiece(PieceDTO pieceDTO) {
        PieceJustificative entity = pieceMapper.toEntity(pieceDTO);
        
        if (pieceDTO.getDossierId() != null) {
            Optional<DossierRetraite> dossier = dossierRepository.findById(pieceDTO.getDossierId());
            if (dossier.isPresent()) {
                entity.setDossierRetraite(dossier.get());
            }
        }
        
        PieceJustificative savedEntity = pieceRepository.save(entity);
        return pieceMapper.toDTO(savedEntity);
    }

    public List<PieceDTO> listerPiecesParDossier(Long dossierId) {
        List<PieceJustificative> entities = pieceRepository.findByDossierRetraiteId(dossierId);
        return pieceMapper.toDTOList(entities);
    }

    public List<PieceDTO> listerPiecesParType(PieceJustificative.TypePiece type) {
        List<PieceJustificative> entities = pieceRepository.findByType(type);
        return pieceMapper.toDTOList(entities);
    }

    public Optional<PieceDTO> obtenirPieceParId(Long id) {
        Optional<PieceJustificative> entity = pieceRepository.findById(id);
        return entity.map(pieceMapper::toDTO);
    }

    public boolean supprimerPiece(Long id) {
        if (pieceRepository.existsById(id)) {
            pieceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<PieceDTO> listerPiecesObligatoires(Long dossierId) {
        List<PieceJustificative> entities = pieceRepository.findByDossierRetraiteIdAndObligatoire(dossierId, true);
        return pieceMapper.toDTOList(entities);
    }
}
