package tn.esprit.tunisair;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.tunisair.dto.EncadreurDTO;
import tn.esprit.tunisair.dto.StageDTO;
import tn.esprit.tunisair.repository.StageRepository;
import tn.esprit.tunisair.service.StageService;
import tn.esprit.tunisair.entity.Stage;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StgaServiceIntegrationTest {

@Autowired
StageService stageServiceIpm;
@Autowired
    StageRepository stageRepository;


    @Test
    public void testSauvegarderstage() {
        // Créez un objet FormateurDto pour le test
        StageDTO stageDTO = new StageDTO();
        EncadreurDTO encadreurDTO =new EncadreurDTO();
        encadreurDTO.setId(1L);
        stageDTO.setEncadreurDTO(encadreurDTO);
        stageDTO.setDuree(90L);
        stageDTO.setTypeStage("PFE");
        stageDTO.setNomService("Gestion");
        stageDTO.setDateDebutStage(new Date());
        stageDTO.setDateFinStage(new Date());
        StageDTO savedstage = stageServiceIpm.save(stageDTO);
        Stage stage = stageRepository.findById(savedstage.getId()).orElse(null);
        assertNotNull(stage);
        assertEquals("PFE", stage.getTypeStage());
        assertEquals("Gestion", stage.getNomService());

    }

    @Test
    public void testRecherchestage() {

        Stage stage = new Stage();
        stage.setId(11L);
        stage.setNomService("Gestion");
        stage.setTypeStage("PFE");
        stageRepository.findById(11L);
        StageDTO stageDTO = stageServiceIpm.recherch(stage.getId());
        Assertions.assertNotNull(stageDTO);
        Assertions.assertEquals("Gestion", stageDTO.getNomService());
        Assertions.assertEquals("PFE", stageDTO.getTypeStage());
    }


    @Test
    public void testFindstage() {


        List<StageDTO> formateurDtos = stageServiceIpm.findAllstage();
        Assertions.assertFalse(formateurDtos.isEmpty());
        int expectedSize = stageRepository.findAll().size();
        Assertions.assertEquals(expectedSize, formateurDtos.size());


    }
}
