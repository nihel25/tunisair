package tn.esprit.tunisair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.dto.StageDTO;
import tn.esprit.tunisair.repository.StageRepository;
import tn.esprit.tunisair.service.StageService;

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
    public ResponseEntity<StageDTO> addstage(@Valid @RequestBody StageDTO stageDTO) {
        StageDTO savestage = stageService.save(stageDTO);
        return new ResponseEntity<>(savestage, HttpStatus.CREATED);
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



