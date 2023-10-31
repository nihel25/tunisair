package tn.esprit.tunisair.Service;

import tn.esprit.tunisair.DTO.StagiaireDTO;

import java.util.List;

public interface StagiaireService {

    public void delete(Long id);

    List<StagiaireDTO> findAll();

    StagiaireDTO save(StagiaireDTO stagiaireDTO);
    StagiaireDTO recherch(Long id);
}
