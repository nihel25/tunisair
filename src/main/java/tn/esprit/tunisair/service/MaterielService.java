package tn.esprit.tunisair.service;

import tn.esprit.tunisair.dto.MaterielDTO;

import java.util.List;

public interface MaterielService {

    List<MaterielDTO> findAllreclamation();

    MaterielDTO save(MaterielDTO materielDTO);
    MaterielDTO recherch(Long id);
    void delete(Long id);
}
