package tn.esprit.tunisair;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tunisair.dto.StageDTO;
import tn.esprit.tunisair.dto.StagiaireDTO;
import tn.esprit.tunisair.entity.Stage;
import tn.esprit.tunisair.entity.Stagiaire;
import tn.esprit.tunisair.repository.StageRepository;
import tn.esprit.tunisair.repository.StagiaireRepository;
import tn.esprit.tunisair.service.StagiaireServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StagiaireServiceImpTest {

    @Mock
    private StagiaireRepository stagiaireRepository;

    @Mock
    private StageRepository stageRepository;

    @InjectMocks
    private StagiaireServiceImp stagiaireService;

    @Test
    @Order(1)
    void testSaveStagiaire() {
        // Créez un StagiaireDTO avec des valeurs appropriées
        StagiaireDTO stagiaireDTO = new StagiaireDTO();
        stagiaireDTO.setNom("John");
        stagiaireDTO.setPrenom("Doe");

        // Créez un StageDTO avec des valeurs appropriées
        StageDTO stageDTO = new StageDTO();
        stageDTO.setTypeStage("PFE");

        // Associez le StageDTO au StagiaireDTO
        stagiaireDTO.setStagedto(stageDTO);

        // Convertissez le DTO en entité
        Stagiaire stagiaire = StagiaireDTO.toEntity(stagiaireDTO);

        // Définissez le comportement du repository mock lorsqu'il est appelé
        when(stagiaireRepository.save(stagiaire)).thenReturn(stagiaire);

        // Définissez le comportement du repository mock pour le stage
        when(stageRepository.save(any(Stage.class))).thenReturn(new Stage());

        // Appelez la méthode du service
        StagiaireDTO savedStagiaireDTO = stagiaireService.save(stagiaireDTO);

        // Vérifiez si les valeurs sont conformes aux attentes
        assertEquals(stagiaireDTO.getNom(), savedStagiaireDTO.getNom());
        assertEquals(stagiaireDTO.getPrenom(), savedStagiaireDTO.getPrenom());

        // Vérifiez si la méthode save a été appelée pour le stage
        verify(stagiaireRepository, times(1)).save(any(Stagiaire.class));

    }


    @Test
    @Order(2)
    void testRechercheStagiaire() {
        // ID du stagiaire à rechercher
        Long stagiaireId = 1L;

        // Créez un Stage avec des valeurs appropriées
        Stage stage = new Stage();
        stage.setId(1L);
        stage.setTypeStage("PFE");

        // Créez un Stagiaire avec des valeurs appropriées
        Stagiaire stagiaire = new Stagiaire();
        stagiaire.setId(stagiaireId);
        stagiaire.setNom("John");
        stagiaire.setPrenom("Doe");
        stagiaire.setStage(stage);  // Définissez la référence au stage

        // Convertissez l'entité en DTO
        StagiaireDTO expectedStagiaireDTO = StagiaireDTO.fromEntity(stagiaire);

        // Définissez le comportement du repository mock lorsqu'il est appelé
        when(stagiaireRepository.findById(stagiaireId)).thenReturn(Optional.of(stagiaire));

        // Appelez la méthode du service
        StagiaireDTO foundStagiaireDTO = stagiaireService.recherch(stagiaireId);

        // Vérifiez si les valeurs sont conformes aux attentes
        assertEquals(expectedStagiaireDTO.getId(), foundStagiaireDTO.getId());
        assertEquals(expectedStagiaireDTO.getNom(), foundStagiaireDTO.getNom());
        assertEquals(expectedStagiaireDTO.getPrenom(), foundStagiaireDTO.getPrenom());

        // Vérifiez si la référence au stage est également conforme aux attentes
        assertNotNull(foundStagiaireDTO.getStagedto());
        assertEquals(stage.getTypeStage(), foundStagiaireDTO.getStagedto().getTypeStage());
        // Ajoutez d'autres vérifications spécifiques au stage si nécessaire
    }


    // Ajoutez d'autres tests selon vos besoins


    @Test
    @Order(3)
    void testDeleteStagiaire() {
        // ID du stagiaire à supprimer
        Long stagiaireId = 1L;

        // Appelez la méthode de suppression dans le service
        stagiaireService.delete(stagiaireId);

        // Vérifiez que la méthode deleteById du repository a été appelée avec le bon ID
        verify(stagiaireRepository, times(1)).deleteById(stagiaireId);
    }

    @Test
    @Order(4)
    void testFindAllStagiaire() {
        // Créez deux stages
        Stage stage1 = new Stage();
        Stage stage2 = new Stage();
        stage1.setId(1L);
        stage1.setTypeStage("PFE");
        stage2.setId(2L);
        stage2.setTypeStage("Stage2");

        // Créez deux stagiaires avec des valeurs appropriées
        Stagiaire stagiaire1 = new Stagiaire();
        stagiaire1.setId(1L);
        stagiaire1.setNom("John");
        stagiaire1.setPrenom("Doe");
        stagiaire1.setStage(stage1);

        Stagiaire stagiaire2 = new Stagiaire();
        stagiaire2.setId(2L);
        stagiaire2.setNom("Jane");
        stagiaire2.setPrenom("Doe");
        stagiaire2.setStage(stage2);

        List<Stagiaire> stagiaireList = new ArrayList<>();
        stagiaireList.add(stagiaire1);
        stagiaireList.add(stagiaire2);

        // Définissez le comportement du repository mock lorsqu'il est appelé
        when(stagiaireRepository.findAll()).thenReturn(stagiaireList);

        // Appelez la méthode du service
        List<StagiaireDTO> foundStagiaireDTOs = stagiaireService.findAll();

        // Vérifiez si le nombre d'éléments retournés correspond au nombre de stagiaires créés
        assertEquals(stagiaireList.size(), foundStagiaireDTOs.size());

        // Vérifiez si la référence au stage est également conforme aux attentes pour chaque stagiaire
        for (int i = 0; i < stagiaireList.size(); i++) {
            assertEquals(stagiaireList.get(i).getStage().getTypeStage(), foundStagiaireDTOs.get(i).getStagedto().getTypeStage());
            // Ajoutez d'autres vérifications spécifiques au stage si nécessaire
        }
    }

}

