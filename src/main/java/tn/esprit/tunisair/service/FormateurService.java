package tn.esprit.tunisair.service;

import tn.esprit.tunisair.dto.FormateurDto;

import java.util.List;

public interface FormateurService {



    List<FormateurDto> findAll();



    FormateurDto recherch(Long id);
    FormateurDto save(FormateurDto formateurDto);
    void delete(Long id);



}

