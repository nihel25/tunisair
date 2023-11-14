package tn.esprit.tunisair.service;

import tn.esprit.tunisair.dto.SalleDTO;

import java.util.List;

public interface SalleService {


    void delete(Long id);


    List<SalleDTO> findAllSalle();

    public SalleDTO save(SalleDTO salleDTO);

    SalleDTO recherch(Long id);
}
