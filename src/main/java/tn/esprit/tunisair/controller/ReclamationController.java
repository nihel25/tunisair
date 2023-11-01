package tn.esprit.tunisair.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.dto.ReclamationDTO;
import tn.esprit.tunisair.service.ReclamationService;
import tn.esprit.tunisair.service.ReclamationServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("Reclamation")
@CrossOrigin(origins = "http://localhost:4200")
public class ReclamationController {
    @Autowired
    ReclamationService reclamationService;
    @Autowired
    ReclamationServiceImpl reclamationServicee;


    @Secured("CLIENT")
    @PostMapping("/savereclamation")
    public ResponseEntity<ReclamationDTO> ajout(@Valid @RequestBody ReclamationDTO reclamationDTO) {


        ReclamationDTO reclamationsave = reclamationService.save(reclamationDTO);
        return new ResponseEntity<>(reclamationsave, HttpStatus.CREATED);
    }
    @Secured("COORDINATEURFORMATION")
    @GetMapping("/recherher/{id}")
    public ReclamationDTO recherch(@PathVariable Long id) {
        return reclamationService.recherch(id);
    }




    @Secured("COORDINATEURFORMATION")
    @GetMapping("/findAllreclamation")
    public List<ReclamationDTO> findAllreclamation() {

        return reclamationServicee.findAllreclamation();

    }

    @Secured("COORDINATEURFORMATION")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        reclamationServicee.delete(id);
    }
}
