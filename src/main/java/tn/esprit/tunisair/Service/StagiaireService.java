package tn.esprit.tunisair.Service;

import tn.esprit.tunisair.DTO.StagiaireDTO;
import tn.esprit.tunisair.entity.Stagiaire;

import java.util.List;

public interface StagiaireService extends GenericInterface<StagiaireDTO>{


    Stagiaire retrievebyid(Long id);
    List<StagiaireDTO> findAll();

    StagiaireDTO save(StagiaireDTO stagiaireDTO);
    StagiaireDTO recherch(Long id);
}
