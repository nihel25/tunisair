package tn.esprit.tunisair.service;

import tn.esprit.tunisair.dto.CertificatDTO;

import java.util.List;

public interface CertificatService {


    CertificatDTO recherch(Long id);
    CertificatDTO save(CertificatDTO certificatDTO);
    void delete(Long id);
    List<CertificatDTO> findAll();
}
