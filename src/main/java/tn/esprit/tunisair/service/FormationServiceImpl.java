package tn.esprit.tunisair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.tunisair.dto.FormationDTO;
import tn.esprit.tunisair.repository.FormateurRepository;
import tn.esprit.tunisair.repository.FormationRepository;
import tn.esprit.tunisair.repository.UserRepository;
import tn.esprit.tunisair.entity.Formation;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class FormationServiceImpl implements FormationService{

@Autowired
    UserRepository userRepository;

    @Autowired
    FormationRepository formationRepository;
    @Autowired
    FormateurRepository formateurRepository;


    @Autowired
    private ImageStorage imageStorage;






    @Override
    public FormationDTO save(FormationDTO dto) {
       // objectsValidator.validate(dto);



        Formation formation = dto.toEntity(dto);
//dto.setUserDTO(dto.getUserDTO());

        if (formation.getFormateur() == null) {
            throw new IllegalArgumentException("Formateur is required for saving a formation.");
        }

        formationRepository.save(formation);
        FormationDTO DTOsaved = FormationDTO.fromEntity(formation);
        return DTOsaved;
    }

    public ResponseEntity<Formation> findbyId(Long id) {
        if (id == null) {

            return null;
        }
        return ResponseEntity.ok(formationRepository.findById(id).get());
    }


    @Override



    public FormationDTO uploadImage(Long id, MultipartFile image) {
        ResponseEntity<Formation> catalogueResponse = this.findbyId(id);
        String imageName = imageStorage.store(image);
        String fileImageDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/formation/downloadImage/").path(imageName).toUriString();
        Formation formation = catalogueResponse.getBody();
        if (formation != null)
            formation.setImage(fileImageDownloadUrl);
        FormationDTO catalogueDto = FormationDTO.fromEntity(formation); // Conversion from Catalogue to CatalogueDto
        return this.save(catalogueDto);
    }

    @Override
    public List<Formation> getAllFormations() {
        List<Formation> formations = formationRepository.findAll();


        for (Formation formation : formations) {
            formation.setDateformation(formation.getDateformation());
            formation.setFormationtype(formation.getFormationtype());
        }

        return formations;
    }


    @Override
    public void delete(Long id) {
        Formation formation = formationRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
        formationRepository.deleteById(id);
    }

    @Override
    public List<FormationDTO> findAllFormation() {
        return formationRepository.findAll()
                .stream()
                .map(FormationDTO::fromEntity)
                .collect(Collectors.toList());
    }





    @Override
    public FormationDTO recherch(Long id) {
        Optional<Formation> optionalformation =formationRepository.findById(id);
        if (optionalformation.isPresent()) {
            Formation formation=optionalformation.get();
            return FormationDTO.fromEntity(formation);
        }
        else
        {
            return null;
        }
    }


}
