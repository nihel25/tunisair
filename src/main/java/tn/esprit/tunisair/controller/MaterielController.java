package tn.esprit.tunisair.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.dto.MaterielDTO;
import tn.esprit.tunisair.service.MaterielService;
import tn.esprit.tunisair.service.MaterielServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("Materiel")
@CrossOrigin(origins = "http://localhost:4200")
public class MaterielController {


@Autowired
MaterielService materielService;
    @Autowired
    MaterielServiceImpl materielServices;
    @Secured("COORDINATEURFORMATION")
    @PostMapping("/addmateriel")
    public ResponseEntity<MaterielDTO> addmateriel(@Valid @RequestBody MaterielDTO materielDTO) {
        MaterielDTO savemateriel = materielService.save(materielDTO);
        return new ResponseEntity<>(savemateriel, HttpStatus.CREATED);
    }










    @Secured("COORDINATEURFORMATION")
    @GetMapping("/recherher/{id}")
    public MaterielDTO recherch(@PathVariable Long id) {

        return materielService.recherch(id);
    }
   // @PreAuthorize("hasAnyRole('coordinateurformation')")
   @Secured("COORDINATEURFORMATION")
    @DeleteMapping("/deletemateriel/{id}")
    public void delete(@PathVariable Long id) {
        materielService.delete(id);
    }

    @Secured("COORDINATEURFORMATION")
    @GetMapping("/listermateriel")
    public List<MaterielDTO> liste() {
        return materielService.findAllreclamation();
    }

}
