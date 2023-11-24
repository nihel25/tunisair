package tn.esprit.tunisair.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tunisair.dto.PersonnelDTO;
import tn.esprit.tunisair.service.PersonnelleService;

import javax.validation.Valid;

@RestController
@RequestMapping("Personnel")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonnelleController {



  private final  PersonnelleService personnelleService;
    @Autowired
    public PersonnelleController(PersonnelleService personnelleService) {
        this.personnelleService = personnelleService;
    }

    @Secured("COORDINATEURENTREPRISE")
    @PostMapping("/saveOrUpdate")
    public ResponseEntity<PersonnelDTO> add(@Valid @RequestBody PersonnelDTO personneldto) {
        PersonnelDTO savepersonnelle = personnelleService.save(personneldto);
        return new ResponseEntity<>(savepersonnelle, HttpStatus.CREATED);
    }




    @Secured("COORDINATEURENTREPRISE")

    @PostMapping(value = "/charger-csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String chargerCSV(@RequestPart("fichier") MultipartFile fichierCSV) {
        personnelleService.chargerDonneesCSV(fichierCSV);
        return null;
    }








    @Secured("COORDINATEURENTREPRISE")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id ) {
        personnelleService.delete(id);
    }




@Secured({"COORDINATEURENTREPRISE","COORDINATEURFORMATION"})
    @GetMapping("/recherher/{id}")
    public PersonnelDTO recherch(@PathVariable Long id) {

        return personnelleService.recherch(id);
    }


}
