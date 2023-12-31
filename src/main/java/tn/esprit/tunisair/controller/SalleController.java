package tn.esprit.tunisair.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.dto.SalleDTO;
import tn.esprit.tunisair.service.SalleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/salle")

@CrossOrigin(origins = "http://localhost:4200")
public class SalleController {

   private final SalleService salleService;

    @Autowired
    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id ) {
        salleService.delete(id);
    }

   @Secured("COORDINATEURFORMATION")
    @GetMapping("/listersalle")
    public List<SalleDTO> liste() {

        return salleService.findAllSalle();

    }


    @Secured("COORDINATEURFORMATION")
    @PostMapping("/addsalle")
    public ResponseEntity<SalleDTO> addsalle(@Valid @RequestBody SalleDTO salleDTO) {
        SalleDTO savesalle = salleService.save(salleDTO);
        return new ResponseEntity<>(savesalle, HttpStatus.CREATED);
    }

  @Secured("COORDINATEURFORMATION")
    @GetMapping("/recherhersalle/{id}")
    public SalleDTO recherch(@PathVariable Long id) {

        return salleService.recherch(id);
    }

}
