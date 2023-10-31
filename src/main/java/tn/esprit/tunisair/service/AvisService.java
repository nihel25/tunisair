package tn.esprit.tunisair.service;

import tn.esprit.tunisair.dto.AvisDto;

import java.util.List;

public interface AvisService {
    public AvisDto recherch(Long id);
    AvisDto save(AvisDto avisDto);
    List<AvisDto> findAll();
}
