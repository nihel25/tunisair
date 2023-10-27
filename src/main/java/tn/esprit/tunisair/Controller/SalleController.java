package tn.esprit.tunisair.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.DTO.SalleDTO;
import tn.esprit.tunisair.Service.SalleService;

import javax.validation.Valid;
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
   // @PreAuthorize("hasAnyRole('coordinateurformation')")
   @Secured("coordinateurformation")
    @GetMapping("/listersalle")
    public List<SalleDTO> liste() {

        return salleService.findAllSalle();

    }



    @Secured("coordinateurformation")
    @PostMapping("/saveOrUpdate")
    public ResponseEntity<SalleDTO> Ajouter(@Valid @RequestBody SalleDTO salleDTO) {
        SalleDTO DTOsaved = salleService.save(salleDTO);
        return new ResponseEntity<SalleDTO>(DTOsaved, HttpStatus.CREATED);
    }

  @Secured("coordinateurformation")
    @GetMapping("/recherhersalle/{id}")
    public SalleDTO recherch(@PathVariable Long id) {

        return salleService.recherch(id);
    }

}
