package tn.esprit.tunisair.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.dto.FormateurDto;
import tn.esprit.tunisair.service.FormateurService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("formateur")
@CrossOrigin(origins = "http://localhost:4200")
public class FormateurController {


  private final  FormateurService formateurService;

    @Autowired
    public FormateurController(FormateurService formateurService) {
        this.formateurService = formateurService;
    }


    @Secured("ADMIN")
    @PostMapping("/saveformateur")
    public ResponseEntity<FormateurDto> addformateur(@Valid @RequestBody FormateurDto formateurDto) {
        FormateurDto saveformateur = formateurService.save(formateurDto);
        return new ResponseEntity<>(saveformateur, HttpStatus.CREATED);
    }


@Secured({"COORDINATEURFORMATION","ADMIN"})

    @GetMapping("/recherher/{id}")
    public FormateurDto recherch(@PathVariable Long id) {

        return formateurService.recherch(id);
    }

    @Secured({"ADMIN","COORDINATEURFORMATION"} )


    @GetMapping("/listeformateur")

    public List<FormateurDto> liste() {

        return formateurService.findAll();

    }



    @Secured("ADMIN")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id ) {
        formateurService.delete(id);
    }
}
