package tn.esprit.tunisair.Service;

import tn.esprit.tunisair.DTO.FormateurDto;

import java.util.List;

public interface FormateurService {

    FormateurDto addformateur(FormateurDto b);

    List<FormateurDto> findAll();
   // List<FormateurDto> listerformateurbyoption(String specialite);


    FormateurDto recherch(Long id);
    FormateurDto save(FormateurDto formateurDto);
    void delete(Long id);



}

