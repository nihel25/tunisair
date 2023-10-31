package tn.esprit.tunisair.service;

import tn.esprit.tunisair.dto.DemandeclientDTO;

import java.util.List;

public interface DemandeformationclientService {

    DemandeclientDTO add(DemandeclientDTO f);
    void delete(Long id);

    DemandeclientDTO recherch(Long id);
    List<DemandeclientDTO> findAlldemandeformation();
}
