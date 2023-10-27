package tn.esprit.tunisair.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.DTO.AttestationDTO;
import tn.esprit.tunisair.Service.AttestationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/attestation")
@CrossOrigin(origins = "http://localhost:4200")
public class AttestationController {


@Autowired
    AttestationService attestationService;


    @Secured( "coordinateurformation")
    @PostMapping("/saveOrUpdate")
    public ResponseEntity<AttestationDTO> AjouterAttestation(@Valid @RequestBody AttestationDTO attestationDTO) {
        AttestationDTO DTOsaved = attestationService.save(attestationDTO);
        return new ResponseEntity<AttestationDTO>(DTOsaved, HttpStatus.CREATED);
    }

   @Secured( "coordinateurformation")
    @GetMapping("/recherher/{id}")
    public AttestationDTO recherch(@PathVariable Long id) {

        return attestationService.recherch(id);
    }
    @Secured( "coordinateurformation")
    @GetMapping("/listeall")

    public List<AttestationDTO> liste() {

        return attestationService.findAll();

    }
    @Secured( "coordinateurformation")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id ) {
        attestationService.delete(id);
    }









}
