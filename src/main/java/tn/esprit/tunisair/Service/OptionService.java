package tn.esprit.tunisair.Service;

import tn.esprit.tunisair.DTO.SpecialiteeDTO;

import java.util.List;

public interface OptionService {


    public SpecialiteeDTO ajouterOption(SpecialiteeDTO p);

    List<SpecialiteeDTO> findAllspecialite();
}
