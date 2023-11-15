package tn.esprit.tunisair;



import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tunisair.dto.StageDTO;
import tn.esprit.tunisair.entity.Stage;
import tn.esprit.tunisair.repository.StageRepository;
import tn.esprit.tunisair.repository.UserRepository;
import tn.esprit.tunisair.service.StageServiceIpm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StageServiceIpmTest {

    @Mock
    private StageRepository stageRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private StageServiceIpm stageService;

    @Test
    @Order(1)
    void testSaveStage() {
        // Créez un StageDTO avec des valeurs appropriées
        StageDTO stageDTO = new StageDTO();
        stageDTO.setTypeStage("PFE");


        // Convertissez le DTO en entité
        Stage stage = StageDTO.toEntity(stageDTO);

        // Définissez le comportement du repository mock lorsqu'il est appelé
        when(stageRepository.save(stage)).thenReturn(stage);

        // Appelez la méthode du service
        StageDTO savedStageDTO = stageService.save(stageDTO);

        // Vérifiez si les valeurs sont conformes aux attentes
        assertEquals(stageDTO.getTypeStage(), savedStageDTO.getTypeStage());

    }

    @Test
    @Order(2)
    void testRecherchStage() {
        // ID du stage à rechercher
        Long stageId = 1L;

        // Créez un Stage avec des valeurs appropriées
        Stage stage = new Stage();
        stage.setId(stageId);
        stage.setTypeStage("PFE");


        // Convertissez l'entité en DTO
        StageDTO expectedStageDTO = StageDTO.fromEntity(stage);

        // Définissez le comportement du repository mock lorsqu'il est appelé
        when(stageRepository.findById(stageId)).thenReturn(Optional.of(stage));

        // Appelez la méthode du service
        StageDTO foundStageDTO = stageService.recherch(stageId);

        // Vérifiez si les valeurs sont conformes aux attentes
        assertEquals(expectedStageDTO.getId(), foundStageDTO.getId());
        assertEquals(expectedStageDTO.getTypeStage(), foundStageDTO.getTypeStage());

    }

    @Test
    @Order(3)
    void testDeleteStage() {
        // ID du stage à supprimer
        Long stageId = 1L;

        // Appelez la méthode de suppression dans le service
        stageService.delete(stageId);

        // Vérifiez que la méthode deleteById du repository a été appelée avec le bon ID
        verify(stageRepository, times(1)).deleteById(stageId);
    }

    @Test
    @Order(4)
    void testFindAllStage() {
        // Créez deux stages
        Stage stage1 = new Stage();
        Stage stage2 = new Stage();
        List<Stage> stageList = new ArrayList<>();
        stageList.add(stage1);
        stageList.add(stage2);

        // Définissez le comportement du repository mock lorsqu'il est appelé
        when(stageRepository.findAll()).thenReturn(stageList);

        // Appelez la méthode du service
        List<StageDTO> foundStageDTOs = stageService.findAllstage();

        // Vérifiez si le nombre d'éléments retournés correspond au nombre de stages créés
        assertEquals(stageList.size(), foundStageDTOs.size());
    }
}

