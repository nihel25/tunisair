package tn.esprit.tunisair.service;

import tn.esprit.tunisair.dto.StagiaireDTO;

import java.util.List;

public interface StagiaireService {

    public void delete(Long id);

    List<StagiaireDTO> findAll();

    StagiaireDTO save(StagiaireDTO stagiaireDTO);
    StagiaireDTO recherch(Long id);
}
