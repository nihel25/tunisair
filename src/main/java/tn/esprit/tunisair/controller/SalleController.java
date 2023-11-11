package tn.esprit.tunisair.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.dto.SalleDTO;
import tn.esprit.tunisair.entity.Salle;
import tn.esprit.tunisair.service.SalleService;

import java.util.List;

@RestController
@RequestMapping("/salle")

@CrossOrigin(origins = "http://localhost:4200")
public class SalleController {
    @Autowired
    SalleService salleService;



    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id ) {
        salleService.delete(id);
    }

   @Secured("COORDINATEURFORMATION")
    @GetMapping("/listersalle")
    public List<SalleDTO> liste() {

        return salleService.findAllSalle();

    }

    @PostMapping("/add-stock")
    @ResponseBody
    public Salle addStock(@RequestBody Salle s) {
        Salle salle = salleService.addsalle(s);
        return salle;
    }

//    @Secured("COORDINATEURFORMATION")
//    @PostMapping("/addsalle")
//    public ResponseEntity<SalleDTO> addsalle(@Valid @RequestBody SalleDTO salleDTO) {
//        SalleDTO savesalle = salleService.save(salleDTO);
//        return new ResponseEntity<>(savesalle, HttpStatus.CREATED);
//    }

  @Secured("COORDINATEURFORMATION")
    @GetMapping("/recherhersalle/{id}")
    public SalleDTO recherch(@PathVariable Long id) {

        return salleService.recherch(id);
    }

}
