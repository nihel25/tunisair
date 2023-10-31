package tn.esprit.tunisair.service;

import tn.esprit.tunisair.dto.ReclamationDTO;

import java.util.List;

public interface ReclamationService {







    List<ReclamationDTO> findAllreclamation();

    ReclamationDTO save(ReclamationDTO reclamationDTO);
    ReclamationDTO recherch(Long id);
    void delete(Long id);
}
