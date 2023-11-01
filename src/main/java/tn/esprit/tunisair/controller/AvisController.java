package tn.esprit.tunisair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.BadWordFilter;
import tn.esprit.tunisair.dto.AvisDto;
import tn.esprit.tunisair.service.AvisService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("avis")
@CrossOrigin(origins = "http://localhost:4200")
public class AvisController {


    @Autowired
    AvisService avisService;

    @Autowired
    private SentimentAnalyzer sentimentAnalyzer;

@Secured("CLIENT")
    @PostMapping("/saveavis")
    public ResponseEntity<AvisDto> addavis(@Valid @RequestBody AvisDto avisDto) {
        avisDto.setText( BadWordFilter.getCensoredText(avisDto.getText() ));
        AvisDto avissave = avisService.save(avisDto);
        return new ResponseEntity<>(avissave, HttpStatus.CREATED);
    }


    @Secured("CLIENT")
    @GetMapping("/recherher/{id}")
    public AvisDto recherch(@PathVariable Long id) {

        return avisService.recherch(id);
    }



@Secured("CLIENT")
    @GetMapping("/analyzeSentiments")
    public SentimentAnalysisResult analyzeSentiments() {
        List<AvisDto> avisList = avisService.findAll();
        return sentimentAnalyzer.analyze(avisList);
    }








    @GetMapping("/listeavis")
//
    public List<AvisDto> liste() {

        return avisService.findAll();

    }
}
