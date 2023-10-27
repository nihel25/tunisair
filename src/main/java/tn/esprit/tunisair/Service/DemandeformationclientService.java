package tn.esprit.tunisair.Service;

import tn.esprit.tunisair.DTO.DemandeclientDTO;

import java.util.List;

public interface DemandeformationclientService {

    DemandeclientDTO add(DemandeclientDTO f);
    void delete(Long id);

    DemandeclientDTO recherch(Long id);
    List<DemandeclientDTO> findAlldemandeformation();
}
