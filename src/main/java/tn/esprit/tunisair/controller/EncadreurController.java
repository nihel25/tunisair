package tn.esprit.tunisair.controller;
//
//

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.dto.EncadreurDTO;
import tn.esprit.tunisair.service.EncadreurService;

import javax.validation.Valid;
import java.util.List;

@RestController

@RequestMapping("encadreursatge")
@CrossOrigin(origins = "http://localhost:4200")
public class EncadreurController {
    @Autowired
    EncadreurService encadreurService;


    @Secured("ADMIN")
 @PostMapping("/saveencadreur")
    public ResponseEntity<EncadreurDTO> addencadreur(@Valid @RequestBody EncadreurDTO encadreurDTO) {
        EncadreurDTO encadreurDTOsaved = encadreurService.save(encadreurDTO);
        return new ResponseEntity<>(encadreurDTOsaved, HttpStatus.CREATED);
    }



    @Secured("ADMIN")
    @DeleteMapping("/supprimer/{id}")
    public void delete(@PathVariable Long id) {

        encadreurService.delete(id);
    }



    @Secured({"ADMIN","RECRUTEUR"})
    @GetMapping("/lister")
    public List<EncadreurDTO> liste() {

        return encadreurService.findAll();
    }


    @Secured("ADMIN")
    @GetMapping("/recherher/{id}")
    public EncadreurDTO recherch(@PathVariable Long id) {

        return encadreurService.recherch(id);
    }














}
