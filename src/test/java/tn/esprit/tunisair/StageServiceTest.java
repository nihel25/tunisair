package tn.esprit.tunisair;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.annotation.Order;
import tn.esprit.tunisair.dto.EncadreurDTO;
import tn.esprit.tunisair.dto.StageDTO;
import tn.esprit.tunisair.entity.Encadreur;
import tn.esprit.tunisair.entity.Stage;
import tn.esprit.tunisair.repository.StageRepository;
import tn.esprit.tunisair.service.StageServiceIpm;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



public class StageServiceTest {

    @Mock
    private StageRepository stageRepository;

    @InjectMocks
    private StageServiceIpm stageService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Order(0)
    public void testSave() {
        // Créer un objet StageDTO pour le test
        StageDTO stageDTO = new StageDTO();
        stageDTO.setTypeStage("Type de test");
        stageDTO.setDateDebutStage(new Date());
        stageDTO.setDateFinStage(new Date());
        stageDTO.setDuree(10L);
        stageDTO.setNomService("Service de test");
        EncadreurDTO encadreurDTO = new EncadreurDTO();
        stageDTO.setEncadreurDTO(encadreurDTO);
        when(stageRepository.save(any(Stage.class))).thenAnswer(invocation -> {
            Stage savedStage = invocation.getArgument(0);
            savedStage.setId(1L);
            return savedStage;
        });
        StageDTO savedStageDTO = stageService.save(stageDTO);
        verify(stageRepository, times(1)).save(any(Stage.class));
        assertNotNull(savedStageDTO);
        assertEquals(stageDTO.getTypeStage(), savedStageDTO.getTypeStage());
        assertEquals(stageDTO.getNomService(), savedStageDTO.getNomService());
        assertNotNull(savedStageDTO.getId());
    }


    @Test
    @Order(1)
    public void testRecherch() {

        Stage stage = new Stage();
        stage.setId(7L);
        stage.setTypeStage("PFe");
        stage.setEncadreur(new Encadreur());

        EncadreurDTO encadreurDTO = new EncadreurDTO();
        encadreurDTO.setId(1L);

        encadreurDTO.setNom("mabrouki");
encadreurDTO.setEmail("akrem@gmail.com");
        when(stageRepository.findById(7L)).thenReturn(Optional.of(stage));
        StageDTO foundStageDTO = stageService.recherch(7L);
        assertEquals(stage.getId(), foundStageDTO.getId());
        assertEquals(stage.getTypeStage(), foundStageDTO.getTypeStage());
        assertNotNull(foundStageDTO.getEncadreurDTO());
        assertEquals(encadreurDTO.getPrenom(), foundStageDTO.getEncadreurDTO().getPrenom());

    }





    @Test
    @Order(2)
    public void testFindAllstage() {
        Stage stage1 = new Stage();
        stage1.setId(7L);
        stage1.setTypeStage("PFe");
        Encadreur encadreur = new Encadreur();
        encadreur.setId(1L);
        stage1.setEncadreur(encadreur);
        when(stageRepository.findAll()).thenReturn(Arrays.asList(stage1));
        List<StageDTO> stageDTOList = stageService.findAllstage();
        assertEquals(1, stageDTOList.size());
        assertEquals(stage1.getId(), stageDTOList.get(0).getId());
        assertEquals(stage1.getTypeStage(), stageDTOList.get(0).getTypeStage());
    }
    @Test
    @Order(3)
    public void testDelete() {
        Long salleId = 15L;
        doNothing().when(stageRepository).deleteById(salleId);
        stageService.delete(salleId);
        verify(stageRepository, times(1)).deleteById(salleId);
    }
}
