package tn.esprit.tunisair.service;

import tn.esprit.tunisair.dto.EncadreurDTO;

import java.util.List;

public interface EncadreurService {

    EncadreurDTO recherch(Long id);
    EncadreurDTO save(EncadreurDTO encadreurDTO);
    void delete(Long id);
    List<EncadreurDTO> findAll();



}
