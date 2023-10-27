package tn.esprit.tunisair.Service;

import tn.esprit.tunisair.DTO.DemandeFormationDTO;
import tn.esprit.tunisair.entity.Demandeformation;
import tn.esprit.tunisair.entity.Personnel;

import java.util.List;

public interface DemandeformationService {

    public List<Personnel> getAllPersonnel();
    void delete(Long id);

    DemandeFormationDTO recherch(Long id);
    public List<DemandeFormationDTO> findAllDemandes();
    public void saveDemandeFormation(Demandeformation demandeFormation);
    public DemandeFormationDTO add(DemandeFormationDTO f);
}
