package tn.esprit.tunisair.Service;

import tn.esprit.tunisair.DTO.EncadreurDTO;

import java.util.List;

public interface EncadreurService {

    EncadreurDTO recherch(Long id);
    EncadreurDTO save(EncadreurDTO encadreurDTO);
    void delete(Long id);
    List<EncadreurDTO> findAll();



}
