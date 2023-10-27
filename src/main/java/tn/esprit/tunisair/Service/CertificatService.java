package tn.esprit.tunisair.Service;

import tn.esprit.tunisair.DTO.CertificatDTO;

import java.util.List;

public interface CertificatService {


    CertificatDTO recherch(Long id);
    CertificatDTO save(CertificatDTO certificatDTO);
    void delete(Long id);
    List<CertificatDTO> findAll();
}
