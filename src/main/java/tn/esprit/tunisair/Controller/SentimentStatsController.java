package tn.esprit.tunisair.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.tunisair.DTO.AvisDto;
import tn.esprit.tunisair.Service.AvisService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stat")
@CrossOrigin(origins = "http://localhost:4200")
public class SentimentStatsController {

    @Autowired
    private AvisService avisService;

    @Autowired
    private SentimentAnalyzer sentimentAnalyzer;

    @GetMapping("/sentimentStats")
    public ResponseEntity<Map<String, Integer>> getSentimentStats() {
        List<AvisDto> avisList = avisService.findAll();
        SentimentAnalysisResult analysisResult = sentimentAnalyzer.analyze(avisList);

        Map<String, Integer> stats = new HashMap<>();
        stats.put("positiveCount", analysisResult.getPositiveCount());
        stats.put("negativeCount", analysisResult.getNegativeCount());
        stats.put("neutralCount", analysisResult.getNeutralCount());

        return ResponseEntity.ok(stats);
    }
}

