package tn.esprit.tunisair.Controller;
//
//

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.DTO.EncadreurDTO;
import tn.esprit.tunisair.Service.EncadreurService;

import javax.validation.Valid;
import java.util.List;

@RestController

@RequestMapping("encadreursatge")
@CrossOrigin(origins = "http://localhost:4200")
public class EncadreurController {
    @Autowired
    EncadreurService encadreurService;


    @Secured("ADMIN")
 @PostMapping("/saveOrUpdatee")
    public ResponseEntity<EncadreurDTO> Ajouter(@Valid @RequestBody EncadreurDTO encadreurDTO) {
        EncadreurDTO encadreurDTOsaved = encadreurService.save(encadreurDTO);
        return new ResponseEntity<>(encadreurDTOsaved, HttpStatus.CREATED);
    }


    @Secured("ADMIN")
    @PostMapping("/save")
    public ResponseEntity<EncadreurDTO> Ajoutererr(@Valid @RequestBody EncadreurDTO encadreurDTO) {
        EncadreurDTO encadreurDTOsaved = encadreurService.save(encadreurDTO);
        return new ResponseEntity<>(encadreurDTOsaved, HttpStatus.CREATED);
    }
    @Secured("ADMIN")
    @DeleteMapping("/supprimer/{id}")
    public void delete(@PathVariable Long id) {

        encadreurService.delete(id);
    }



    @Secured({"ADMIN","recruteur"})
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
