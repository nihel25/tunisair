package tn.esprit.tunisair.Service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tunisair.DTO.FormationDTO;
import tn.esprit.tunisair.entity.Formation;

import java.util.List;

public interface FormationService {

    FormationDTO save(FormationDTO dto);
    void delete(Long id);

    List<FormationDTO> findAllFormation();


    public List<Formation> getAllFormations();


    public FormationDTO uploadImage(Long id, MultipartFile image);


    FormationDTO recherch(Long id);
}
