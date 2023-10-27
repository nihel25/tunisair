package tn.esprit.tunisair.Service;

import tn.esprit.tunisair.DTO.AvisDto;

import java.util.List;

public interface AvisService {
    public AvisDto recherch(Long id);
    AvisDto save(AvisDto avisDto);
    List<AvisDto> findAll();
}
