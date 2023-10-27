package tn.esprit.tunisair.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.DTO.FormateurDto;
import tn.esprit.tunisair.Service.FormateurService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("formateur")
@CrossOrigin(origins = "http://localhost:4200")
public class FormateurController {

@Autowired
    FormateurService formateurService;




    @Secured("ADMIN")
    @PostMapping("/saveOrUpdate")
    public ResponseEntity<FormateurDto> Ajouter(@Valid @RequestBody FormateurDto formateurDto) {
        FormateurDto DTOsaved = formateurService.save(formateurDto);
        return new ResponseEntity<FormateurDto>(DTOsaved, HttpStatus.CREATED);
    }


@Secured({"coordinateurformation","ADMIN"})

    @GetMapping("/recherher/{id}")
    public FormateurDto recherch(@PathVariable Long id) {

        return formateurService.recherch(id);
    }

    @Secured({"ADMIN","coordinateurformation"} )


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
