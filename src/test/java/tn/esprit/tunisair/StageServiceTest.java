package tn.esprit.tunisair;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.tunisair.DTO.EncadreurDTO;
import tn.esprit.tunisair.DTO.StageDTO;
import tn.esprit.tunisair.Repository.StageRepository;
import tn.esprit.tunisair.Service.StageServiceIpm;
import tn.esprit.tunisair.entity.Encadreur;
import tn.esprit.tunisair.entity.Stage;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@RunWith(SpringRunner.class)
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
    public void testSave() {
        // Créer un objet StageDTO pour le test
        StageDTO stageDTO = new StageDTO();
        stageDTO.setTypeStage("Type de test");
        stageDTO.setDateDebutStage(new Date());
        stageDTO.setDateFinStage(new Date());
        stageDTO.setDuree(10L);
        stageDTO.setNomService("Service de test");

        // Ajoutez un EncadreurDTO fictif pour le test
        EncadreurDTO encadreurDTO = new EncadreurDTO();
        stageDTO.setEncadreurDTO(encadreurDTO);

        // Configurer le comportement du mock pour le repository
        when(stageRepository.save(any(Stage.class))).thenAnswer(invocation -> {
            Stage savedStage = invocation.getArgument(0);
            savedStage.setId(1L);  // Simule l'attribution d'un ID lors de l'enregistrement
            return savedStage;
        });

        // Appeler la méthode à tester
        StageDTO savedStageDTO = stageService.save(stageDTO);

        // Vérifier que le repository a été appelé avec le bon objet Stage
        verify(stageRepository, times(1)).save(any(Stage.class));

        // Vérifier que l'entité retournée correspond aux attentes
        assertNotNull(savedStageDTO);
        assertEquals(stageDTO.getTypeStage(), savedStageDTO.getTypeStage());
        assertEquals(stageDTO.getNomService(), savedStageDTO.getNomService());

        // Vérifier que l'ID a été attribué (simulé dans le mock)
        assertNotNull(savedStageDTO.getId());
    }


    @Test
    public void testRecherch() {
        // Créer un objet Stage pour le test
        Stage stage = new Stage();
        stage.setId(7L);
        stage.setTypeStage("PFe");
        stage.setEncadreur(new Encadreur());  // Assurez-vous que l'entité Stage a un encadreur

        // Créer un objet EncadreurDTO pour le test
        EncadreurDTO encadreurDTO = new EncadreurDTO();
        encadreurDTO.setId(1L);

        encadreurDTO.setNom("mabrouki");
        // ... autres champs de l'encadreurDTO
encadreurDTO.setEmail("akrem@gmail.com");
        // Configurer le comportement du mock pour le repository
        when(stageRepository.findById(7L)).thenReturn(Optional.of(stage));

        // Appeler la méthode à tester
        StageDTO foundStageDTO = stageService.recherch(7L);

        // Vérifier que la DTO retournée correspond aux attentes
        assertEquals(stage.getId(), foundStageDTO.getId());
        assertEquals(stage.getTypeStage(), foundStageDTO.getTypeStage());

        // Vérifier l'encadreurDTO
        assertNotNull(foundStageDTO.getEncadreurDTO());
        assertEquals(encadreurDTO.getPrenom(), foundStageDTO.getEncadreurDTO().getPrenom());
        // ... autres assertions pour les champs de l'encadreurDTO
    }





    @Test
    public void testFindAllstage() {
        // Créer des objets Stage pour le test
        Stage stage1 = new Stage();
        stage1.setId(7L);
        stage1.setTypeStage("PFe");

        Encadreur encadreur = new Encadreur();
        encadreur.setId(1L);
        // ... configurez les autres propriétés de l'encadreur

        stage1.setEncadreur(encadreur);

        // Configurer le comportement du mock pour le repository
        when(stageRepository.findAll()).thenReturn(Arrays.asList(stage1));

        // Appeler la méthode à tester
        List<StageDTO> stageDTOList = stageService.findAllstage();

        // Vérifier que la liste DTO retournée correspond aux attentes
        assertEquals(1, stageDTOList.size());
        assertEquals(stage1.getId(), stageDTOList.get(0).getId());
        assertEquals(stage1.getTypeStage(), stageDTOList.get(0).getTypeStage());
        // ... Ajoutez des assertions pour vérifier l'encadreurDTO et autres propriétés
    }

}
