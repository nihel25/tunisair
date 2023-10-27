package tn.esprit.tunisair.Service;

import tn.esprit.tunisair.DTO.AttestationDTO;

import java.util.List;

public interface AttestationService {


    AttestationDTO recherch(Long id);
    AttestationDTO save(AttestationDTO attestationDTO);
    void delete(Long id);
    List<AttestationDTO> findAll();
}
