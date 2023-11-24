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

        when(stageRepository.save(Mockito.any())).thenAnswer(invocation -> invocation.getArgument(0));
        StageDTO stageDTO = new StageDTO();
        stageDTO.setTypeStage("PFE");
        StageDTO savedStageDTO = stageService.save(stageDTO);
        assertEquals(stageDTO.getTypeStage(), savedStageDTO.getTypeStage());

        verify(stageRepository, times(1)).save(Mockito.any());
    }

    @Test
    void testRecherchStage() {

        Long stageId = 1L;


        Stage stage = new Stage();
        stage.setId(stageId);
        stage.setTypeStage("PFE");


        Optional<Stage> optionalStage = Optional.of(stage);
        when(stageRepository.findById(stageId)).thenReturn(optionalStage);
        StageDTO foundStageDTO = stageService.recherch(stageId);
        assertEquals(stage.getId(), foundStageDTO.getId());
        assertEquals(stage.getTypeStage(), foundStageDTO.getTypeStage());
        verify(stageRepository, times(1)).findById(stageId);
    }

    @Test
    void testDeleteStagee() {

        Long stageId = 1L;
        stageService.delete(stageId);
        verify(stageRepository, times(1)).deleteById(stageId);
    }

    @Test
    void testFindAllStage() {

        Stage stage1 = new Stage();
        Stage stage2 = new Stage();
        List<Stage> stageList = new ArrayList<>();
        stageList.add(stage1);
        stageList.add(stage2);
        when(stageRepository.findAll()).thenReturn(stageList);
        List<StageDTO> foundStageDTOs = stageService.findAllstage();
        assertEquals(stageList.size(), foundStageDTOs.size());
    }


}

