package tn.esprit.tunisair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.dto.StagiaireDTO;
import tn.esprit.tunisair.service.StagiaireService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("stagiaire")
@CrossOrigin(origins = "http://localhost:4200")
public class StagiaireController {
    @Autowired
    StagiaireService stagiaireService;

    @Secured("recruteur")

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<StagiaireDTO> AjouterRecreteur(@Valid @RequestBody StagiaireDTO stagiaireDTO) {
        StagiaireDTO DTOsaved = stagiaireService.save(stagiaireDTO);
        return new ResponseEntity<StagiaireDTO>(DTOsaved, HttpStatus.CREATED);
    }
    @Secured("recruteur")
    @GetMapping("/recherher/{id}")
    public StagiaireDTO recherch(@PathVariable Long id) {

        return stagiaireService.recherch(id);
    }




    @Secured("recruteur")
    @DeleteMapping("/supprimer/{id}")
    public void delete(@PathVariable Long id) {

        stagiaireService.delete(id);
    }
    @Secured("recruteur")
    @GetMapping("/listerstagiaire")
    public List<StagiaireDTO> liste() {

        return stagiaireService.findAll();
    }





}