package tn.esprit.tunisair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.dto.DemandeclientDTO;
import tn.esprit.tunisair.service.DemandeformationclientService;
import tn.esprit.tunisair.service.UserService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("DemandeFormationclient")
@CrossOrigin(origins = "http://localhost:4200")
public class DemandeclientController {



@Autowired
    UserService userService;

    @Autowired
    DemandeformationclientService demandeformationclientService;
















@Secured( "client")
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public ResponseEntity<DemandeclientDTO> Ajouter(@Valid @RequestBody DemandeclientDTO demandeformation)
    {

        DemandeclientDTO DTOsaved   = demandeformationclientService.add(demandeformation);
        return new ResponseEntity<DemandeclientDTO>(DTOsaved, HttpStatus.CREATED);
    }
    @Secured( "coordinateurformation")

    @GetMapping("/recherher/{id}")
    public DemandeclientDTO recherch(@PathVariable Long id) {

        return demandeformationclientService.recherch(id);
    }

    @Secured( "coordinateurformation")
    @GetMapping("/listerdemande")
    public List<DemandeclientDTO> liste() {
        return demandeformationclientService.findAlldemandeformation();

    }
    @Secured( "coordinateurformation")
    @DeleteMapping("/supprimer/{id}")
    public void delete(@PathVariable Long id) {

        demandeformationclientService.delete(id);
    }
}
