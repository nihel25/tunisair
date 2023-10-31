package tn.esprit.tunisair.service;

import tn.esprit.tunisair.dto.SpecialiteeDTO;

import java.util.List;

public interface OptionService {


    public SpecialiteeDTO ajouterOption(SpecialiteeDTO p);

    List<SpecialiteeDTO> findAllspecialite();
}
