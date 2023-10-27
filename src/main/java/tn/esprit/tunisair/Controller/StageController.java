package tn.esprit.tunisair.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.DTO.StageDTO;
import tn.esprit.tunisair.Repository.StageRepository;
import tn.esprit.tunisair.Service.StageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("stage")
@CrossOrigin(origins = "http://localhost:4200")
public class StageController {
    @Autowired
    StageService stageService;
    @Autowired
    private StageRepository stageRepository;
    @Secured("recruteur")
    @PostMapping("/savestage")
    public ResponseEntity<StageDTO> Ajouter(@Valid @RequestBody StageDTO stageDTO) {
        StageDTO DTOsaved = stageService.save(stageDTO);
        return new ResponseEntity<StageDTO>(DTOsaved, HttpStatus.CREATED);
    }
    @Secured("recruteur")
    @GetMapping("/recherher/{id}")
    public StageDTO recherch(@PathVariable Long id) {

        return stageService.recherch(id);
    }



    @Secured("recruteur")
    @DeleteMapping("/supprimer/{id}")
    public void delete(@PathVariable Long id) {

        stageService.delete(id);
    }



    @Secured("recruteur")
    @GetMapping("/listestage")
    public List<StageDTO> findAll() {
        return stageService.findAllstage();
    }

}



