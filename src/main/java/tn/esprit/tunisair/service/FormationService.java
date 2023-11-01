package tn.esprit.tunisair.service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tunisair.dto.FormationDTO;

import java.util.List;

public interface FormationService {

    FormationDTO save(FormationDTO dto);
    void delete(Long id);

    List<FormationDTO> findAllFormation();




    public FormationDTO uploadImage(Long id, MultipartFile image);


    FormationDTO recherch(Long id);
}
