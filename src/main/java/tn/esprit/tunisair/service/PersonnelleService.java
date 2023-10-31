package tn.esprit.tunisair.service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tunisair.dto.PersonnelDTO;

public interface PersonnelleService {


    PersonnelDTO recherch(Long id);
    void delete(Long id);
     void chargerDonneesCSV(MultipartFile fichierCSV);
     PersonnelDTO save(PersonnelDTO personnelDTO);

}
