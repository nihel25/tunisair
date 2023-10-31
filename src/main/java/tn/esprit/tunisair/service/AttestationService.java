package tn.esprit.tunisair.service;

import tn.esprit.tunisair.dto.AttestationDTO;

import java.util.List;

public interface AttestationService {


    AttestationDTO recherch(Long id);
    AttestationDTO save(AttestationDTO attestationDTO);
    void delete(Long id);
    List<AttestationDTO> findAll();
}
