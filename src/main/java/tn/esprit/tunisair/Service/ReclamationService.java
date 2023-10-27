package tn.esprit.tunisair.Service;

import tn.esprit.tunisair.DTO.ReclamationDTO;

import java.util.List;

public interface ReclamationService {







    List<ReclamationDTO> findAllreclamation();

    ReclamationDTO save(ReclamationDTO reclamationDTO);
    ReclamationDTO recherch(Long id);
    void delete(Long id);
}
