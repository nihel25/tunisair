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


  private final  DemandeformationclientService demandeformationclientService;

    @Autowired
    public DemandeclientController(DemandeformationclientService demandeformationclientService) {
        this.demandeformationclientService = demandeformationclientService;
    }














@Secured( "CLIENT")
    @PostMapping("/savedemande")
    @ResponseBody
    public ResponseEntity<DemandeclientDTO> adddemande(@Valid @RequestBody DemandeclientDTO demandeformation)
    {

        DemandeclientDTO savedemande   = demandeformationclientService.add(demandeformation);
        return new ResponseEntity<>(savedemande, HttpStatus.CREATED);
    }
    @Secured( "COORDINATEURFORMATION")

    @GetMapping("/recherher/{id}")
    public DemandeclientDTO recherch(@PathVariable Long id) {

        return demandeformationclientService.recherch(id);
    }

    @Secured( "COORDINATEURFORMATION")
    @GetMapping("/listerdemande")
    public List<DemandeclientDTO> liste() {
        return demandeformationclientService.findAlldemandeformation();

    }
    @Secured( "COORDINATEURFORMATION")
    @DeleteMapping("/supprimer/{id}")
    public void delete(@PathVariable Long id) {

        demandeformationclientService.delete(id);
    }
}
