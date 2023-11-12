package tn.esprit.tunisair;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.StageDTO;
import tn.esprit.tunisair.entity.Stage;
import tn.esprit.tunisair.repository.StageRepository;
import tn.esprit.tunisair.service.StageServiceIpm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StageServiceIpmTests {

    @Mock
    private StageRepository stageRepository;

    @InjectMocks
    private StageServiceIpm stageService;

    @Test
    void testSaveStage() {
        // Configurer le comportement du repository mock
        when(stageRepository.save(Mockito.any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Créer un objet StageDTO
        StageDTO stageDTO = new StageDTO();
        stageDTO.setTypeStage("PFE");

        // Appeler la méthode save du service
        StageDTO savedStageDTO = stageService.save(stageDTO);

        // Vérifier si les valeurs sont conformes aux attentes
        assertEquals(stageDTO.getTypeStage(), savedStageDTO.getTypeStage());

        // Vérifier si la méthode save du repository a été appelée
        verify(stageRepository, times(1)).save(Mockito.any());
    }

    @Test
    void testRecherchStage() {
        // ID du stage à rechercher
        Long stageId = 1L;

        // Créer un objet Stage
        Stage stage = new Stage();
        stage.setId(stageId);
        stage.setTypeStage("PFE");

        // Créer un Optional<Stage>
        Optional<Stage> optionalStage = Optional.of(stage);

        // Configurer le comportement du repository mock
        when(stageRepository.findById(stageId)).thenReturn(optionalStage);

        // Appeler la méthode recherch du service
        StageDTO foundStageDTO = stageService.recherch(stageId);

        // Vérifier si les valeurs sont conformes aux attentes
        assertEquals(stage.getId(), foundStageDTO.getId());
        assertEquals(stage.getTypeStage(), foundStageDTO.getTypeStage());

        // Vérifier si la méthode findById du repository a été appelée
        verify(stageRepository, times(1)).findById(stageId);
    }

    @Test
    void testDeleteStagee() {
        // ID du stage à supprimer
        Long stageId = 1L;

        // Appeler la méthode delete du service
        stageService.delete(stageId);

        // Vérifier si la méthode deleteById du repository a été appelée
        verify(stageRepository, times(1)).deleteById(stageId);
    }

    @Test
    void testFindAllStage() {
        // Créer deux stages
        Stage stage1 = new Stage();
        Stage stage2 = new Stage();
        List<Stage> stageList = new ArrayList<>();
        stageList.add(stage1);
        stageList.add(stage2);

        // Configurer le comportement du repository mock
        when(stageRepository.findAll()).thenReturn(stageList);

        // Appeler la méthode du service
        List<StageDTO> foundStageDTOs = stageService.findAllstage();

        // Vérifier si le nombre d'éléments retournés correspond au nombre de stages créés
        assertEquals(stageList.size(), foundStageDTOs.size());
    }

    // Ajoutez d'autres tests si nécessaire
}

