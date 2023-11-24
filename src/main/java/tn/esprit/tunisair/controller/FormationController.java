package tn.esprit.tunisair.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tunisair.dto.FormationDTO;
import tn.esprit.tunisair.repository.FormationRepository;
import tn.esprit.tunisair.service.FormationService;
import tn.esprit.tunisair.service.ImageStorage;
import tn.esprit.tunisair.service.ReclamationService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formation")
@CrossOrigin(origins = "http://localhost:4200")
public class FormationController {



    private final FormationService formationService;





    @Autowired
    public FormationController(FormationService formationService) {
        this.formationService = formationService;
    }
    @Autowired
    ReclamationService reclamationService;
    @Autowired
    FormationRepository rep;



   @Secured("COORDINATEURFORMATION")
    @PostMapping("/addformation")
    public ResponseEntity<FormationDTO> add (@Valid @RequestBody FormationDTO formationDTO) {
        FormationDTO saveformation = formationService.save(formationDTO);
        return new ResponseEntity<>(saveformation, HttpStatus.CREATED);
    }



    @Secured("COORDINATEURFORMATION")
    @GetMapping("/recherher/{id}")
    public FormationDTO recherch(@PathVariable Long id) {

        return formationService.recherch(id);
    }





    @Autowired
    private ImageStorage imageStorage;



  @Secured("COORDINATEURFORMATION")
    @DeleteMapping("/supprimer/{id}")
    public void delete(@PathVariable Long id) {
        formationService.delete(id);
    }


   @Secured({"COORDINATEURFORMATION","COORDINATEURENTREPRISE"})
    @GetMapping("/lister")
    public List<FormationDTO> liste() {
        return formationService.findAllFormation();
    }


    @Secured( "COORDINATEURFORMATION")
    @PostMapping("/uploadImage/{id}")
    public FormationDTO uploadImagecatalogue(@PathVariable Long id, MultipartFile image) {
        return formationService.uploadImage(id, image);
    }

    @Secured( "COORDINATEURFORMATION")
    @GetMapping("/downloadImage/{imageName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String imageName, HttpServletRequest request) {
        return this.imageStorage.downloadUserImage(imageName, request);
    }

}