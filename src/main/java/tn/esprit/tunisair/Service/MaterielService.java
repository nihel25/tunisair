package tn.esprit.tunisair.Service;

import tn.esprit.tunisair.DTO.MaterielDTO;

import java.util.List;

public interface MaterielService {

    List<MaterielDTO> findAllreclamation();

    MaterielDTO save(MaterielDTO materielDTO);
    MaterielDTO recherch(Long id);
    void delete(Long id);
}
