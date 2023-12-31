package tn.esprit.tunisair.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.dto.CertificatDTO;
import tn.esprit.tunisair.service.CertificatService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("certificat")
@CrossOrigin(origins = "http://localhost:4200")
public class CertificatController {



  private final  CertificatService certificatService;

    @Autowired
    public CertificatController(CertificatService certificatService) {
        this.certificatService = certificatService;
    }



 @Secured( "RECRUTEUR")
    @PostMapping("/savecertif")
    public ResponseEntity<CertificatDTO> save (@Valid @RequestBody CertificatDTO certificatDTO) {
        CertificatDTO savecertif = certificatService.save(certificatDTO);
        return new ResponseEntity<>(savecertif, HttpStatus.CREATED);
    }


   @Secured( "RECRUTEUR")
    @GetMapping("/recherher/{id}")
    public CertificatDTO recherch(@PathVariable Long id) {

        return certificatService.recherch(id);
    }
    @Secured( "RECRUTEUR")
    @GetMapping("/listecertificat")

    public List<CertificatDTO> liste() {

        return certificatService.findAll();

    }
    @Secured( "RECRUTEUR")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id ) {
        certificatService.delete(id);
    }













}
