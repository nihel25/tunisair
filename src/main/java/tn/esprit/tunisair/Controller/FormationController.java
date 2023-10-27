package tn.esprit.tunisair.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tunisair.DTO.FormationDTO;
import tn.esprit.tunisair.Repository.FormationRepository;
import tn.esprit.tunisair.Service.FormationService;
import tn.esprit.tunisair.Service.ImageStorage;
import tn.esprit.tunisair.Service.ReclamationServiceImpl;
import tn.esprit.tunisair.entity.Formation;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formation")
@CrossOrigin(origins = "http://localhost:4200")
public class FormationController {


    @Autowired
    FormationService formationService;
    @Autowired
    ReclamationServiceImpl reclamationService;
    @Autowired
    FormationRepository rep;



   @Secured("coordinateurformation")
    @PostMapping("/saveOrUpdate")
    public ResponseEntity<FormationDTO> Ajouter(@Valid @RequestBody FormationDTO formationDTO) {
        FormationDTO DTOsaved = formationService.save(formationDTO);
        return new ResponseEntity<FormationDTO>(DTOsaved, HttpStatus.CREATED);
    }


    @Secured("client")
    @GetMapping("/formationbydateandtype")
    public List<Formation> getAllFormations() {
        return formationService.getAllFormations();
    }

    @Secured("coordinateurformation")
    @GetMapping("/recherher/{id}")
    public FormationDTO recherch(@PathVariable Long id) {

        return formationService.recherch(id);
    }





    @Autowired
    private ImageStorage imageStorage;


  //  @PreAuthorize("hasAnyRole('coordinateurformation')")
  @Secured("coordinateurformation")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        formationService.delete(id);
    }

   // @PreAuthorize("hasAnyRole('coordinateurformation')")
   @Secured({"coordinateurformation","coordinateurformation"})
    @GetMapping("/lister")
    public List<FormationDTO> liste() {
        return formationService.findAllFormation();
    }


    @Secured( "coordinateurformation")
    @PostMapping("/uploadImage/{id}")
    public FormationDTO uploadImagecatalogue(@PathVariable Long id, MultipartFile image) {
        return formationService.uploadImage(id, image);
    }

    @Secured( "coordinateurformation")
    @GetMapping("/downloadImage/{imageName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String imageName, HttpServletRequest request) {
        return this.imageStorage.downloadUserImage(imageName, request);
    }

}