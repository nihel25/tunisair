package tn.esprit.tunisair.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.DTO.CertificatDTO;
import tn.esprit.tunisair.Service.CertificatService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("certificat")
@CrossOrigin(origins = "http://localhost:4200")
public class CertificatController {


@Autowired
    CertificatService certificatService;





 @Secured( "recruteur")
    @PostMapping("/saveOrUpdate")
    public ResponseEntity<CertificatDTO> AjouterRecreteur(@Valid @RequestBody CertificatDTO certificatDTO) {
        CertificatDTO DTOsaved = certificatService.save(certificatDTO);
        return new ResponseEntity<CertificatDTO>(DTOsaved, HttpStatus.CREATED);
    }


   @Secured( "recruteur")
    @GetMapping("/recherher/{id}")
    public CertificatDTO recherch(@PathVariable Long id) {

        return certificatService.recherch(id);
    }
    @Secured( "recruteur")
    @GetMapping("/listecertificat")

    public List<CertificatDTO> liste() {

        return certificatService.findAll();

    }
    @Secured( "recruteur")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id ) {
        certificatService.delete(id);
    }













}
