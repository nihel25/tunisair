package tn.esprit.tunisair.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.dto.AttestationDTO;
import tn.esprit.tunisair.service.AttestationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/attestation")
@CrossOrigin(origins = "http://localhost:4200")
public class AttestationController {


@Autowired
    AttestationService attestationService;


    @Secured("COORDINATEURFORMATION")
    @PostMapping("/saveattestation")
    public ResponseEntity<AttestationDTO> ajout(@Valid @RequestBody AttestationDTO attestationDTO) {
        AttestationDTO saveattesation = attestationService.save(attestationDTO);
        return new ResponseEntity<>(saveattesation, HttpStatus.CREATED);
    }


    @Secured( "COORDINATEURFORMATION")
    @GetMapping("/recherher/{id}")
    public AttestationDTO recherch(@PathVariable Long id) {

        return attestationService.recherch(id);
    }
    @Secured( "COORDINATEURFORMATION")
    @GetMapping("/listeall")

    public List<AttestationDTO> liste() {

        return attestationService.findAll();

    }
    @Secured( "COORDINATEURFORMATION")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id ) {
        attestationService.delete(id);
    }









}
