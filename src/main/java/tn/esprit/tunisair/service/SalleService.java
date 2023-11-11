package tn.esprit.tunisair.service;

import tn.esprit.tunisair.dto.SalleDTO;
import tn.esprit.tunisair.entity.Salle;

import java.util.List;

public interface SalleService {

    public void ajoutersalle(Salle s);
    void delete(Long id);

    public Salle addsalle(Salle s);
    List<SalleDTO> findAllSalle();

    public SalleDTO save(SalleDTO salleDTO);

    SalleDTO recherch(Long id);
}
