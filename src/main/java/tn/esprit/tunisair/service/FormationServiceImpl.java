package tn.esprit.tunisair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.tunisair.entity.Formation;
import tn.esprit.tunisair.repository.FormateurRepository;
import tn.esprit.tunisair.repository.FormationRepository;
import tn.esprit.tunisair.dto.FormationDTO;
import tn.esprit.tunisair.repository.UserRepository;

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
    public FormationDTO save(FormationDTO formationDTO) {




        Formation formation = FormationDTO.toEntity(formationDTO);

        if (formation.getFormateur() == null) {
            throw new IllegalArgumentException("Formateur is required for saving a formation.");
        }

        formationRepository.save(formation);



       return FormationDTO.fromEntity(formation);

    }

    public ResponseEntity<Formation> findbyId(Long id) {
        if (id == null) {
            return ResponseEntity.notFound().build();
        }

        Optional<Formation> formationOptional = formationRepository.findById(id);

        if (formationOptional.isPresent()) {
            return ResponseEntity.ok(formationOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @Override



    public FormationDTO uploadImage(Long id, MultipartFile image) {
        ResponseEntity<Formation> catalogueResponse = this.findbyId(id);
        String imageName = imageStorage.store(image);
        String fileImageDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/formation/downloadImage/").path(imageName).toUriString();
        Formation formation = catalogueResponse.getBody();
        if (formation != null)
            formation.setImage(fileImageDownloadUrl);
        FormationDTO catalogueDto = FormationDTO.fromEntity(formation);
        return this.save(catalogueDto);
    }



    @Override
    public void delete(Long id) {

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
