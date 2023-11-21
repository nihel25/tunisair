package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.StageController;
import tn.esprit.tunisair.dto.StageDTO;
import tn.esprit.tunisair.service.StageService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StageControllerTests {

    @Mock
    private StageService stageService;

    @InjectMocks
    private StageController stageController;

    @Test
    void testAddStage() {
        // Créer un exemple de StageDTO
        StageDTO stageDTO = new StageDTO();
        stageDTO.setTypeStage("PFE");
        when(stageService.save(stageDTO)).thenReturn(stageDTO);
        ResponseEntity<StageDTO> responseEntity = stageController.addstage(stageDTO);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(stageDTO, responseEntity.getBody());
        verify(stageService, times(1)).save(stageDTO);
    }

    @Test
    void testRecherchStage() {
        // ID du stage à rechercher
        Long stageId = 1L;

        // Créer un exemple de StageDTO
        StageDTO stageDTO = new StageDTO();
        stageDTO.setId(stageId);
        stageDTO.setTypeStage("PFE");
        when(stageService.recherch(stageId)).thenReturn(stageDTO);
        StageDTO result = stageController.recherch(stageId);
        assertEquals(stageDTO, result);
        verify(stageService, times(1)).recherch(stageId);
    }

    @Test
    void testDeleteStage() {
        // Appeler la méthode delete du contrôleur
        stageController.delete(1L);

        // Vérifier si la méthode delete du service a été appelée
        verify(stageService, times(1)).delete(1L);
    }

    @Test
    void testFindAllStage() {
        // Créer une liste d'exemple de StageDTO
        List<StageDTO> stageDTOList = new ArrayList<>();
        // Ajouter des éléments à la liste selon vos besoins

        // Configurer le comportement du service mock
        when(stageService.findAllstage()).thenReturn(stageDTOList);

        // Appeler la méthode findAll du contrôleur
        List<StageDTO> result = stageController.findAll();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(stageDTOList, result);

        // Vérifier si la méthode findAllstage du service a été appelée
        verify(stageService, times(1)).findAllstage();
    }
}
