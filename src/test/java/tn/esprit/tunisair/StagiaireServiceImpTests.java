package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StagiaireServiceImpTests {

    @Mock
    private StagiaireRepository stagiaireRepository;

    @Mock
    private StageRepository stageRepository;

    @InjectMocks
    private StagiaireServiceImp stagiaireService;

    @Test
    void testSaveStagiaire() {
        // Créez un StageDTO avec des valeurs appropriées
        StageDTO stageDTO = new StageDTO();
        // Initialisez les propriétés du stageDTO selon les besoins

        // Créez un StagiaireDTO avec des valeurs appropriées
        StagiaireDTO stagiaireDTO = new StagiaireDTO();
        stagiaireDTO.setNom("John");
        stagiaireDTO.setPrenom("Doe");
        stagiaireDTO.setStagedto(stageDTO);  // Ajoutez le StageDTO

        // Convertissez le DTO en entité
        Stagiaire stagiaire = StagiaireDTO.toEntity(stagiaireDTO);

        // Utilisez doReturn().when() pour configurer le stub dans le mock
        doReturn(stagiaire).when(stagiaireRepository).save(Mockito.any(Stagiaire.class));

        // Appelez la méthode du service
        StagiaireDTO savedStagiaireDTO = stagiaireService.save(stagiaireDTO);

        // Vérifiez si les valeurs sont conformes aux attentes
        assertEquals(stagiaireDTO.getNom(), savedStagiaireDTO.getNom());
        assertEquals(stagiaireDTO.getPrenom(), savedStagiaireDTO.getPrenom());

        // Vérifiez si la méthode save du repository a été appelée
        verify(stagiaireRepository, times(1)).save(Mockito.any(Stagiaire.class));
    }




    @Test
    void testRecherchStagiaire() {
        // ID du stagiaire à rechercher
        Long stagiaireId = 8L;

        // Créer un objet StageDTO avec des valeurs appropriées
        StageDTO stageDTO = new StageDTO();
        // Initialisez les propriétés du stageDTO selon les besoins

        // Créer un objet StagiaireDTO avec des valeurs appropriées, y compris le StageDTO
        StagiaireDTO stagiaireDTO = new StagiaireDTO();
        stagiaireDTO.setId(stagiaireId);
        stagiaireDTO.setNom("riahi");
        stagiaireDTO.setPrenom("ahlem");
        stagiaireDTO.setEmail("ahlem@gmail.com");
        stagiaireDTO.setStagedto(stageDTO);  // Associez le StageDTO au StagiaireDTO

        // Convertir StagiaireDTO en Stagiaire
        Stagiaire stagiaire = StagiaireDTO.toEntity(stagiaireDTO);

        // Créer un Optional<Stagiaire>
        Optional<Stagiaire> optionalStagiaire = Optional.of(stagiaire);

        // Configurer le comportement du repository mock
        when(stagiaireRepository.findById(stagiaireId)).thenReturn(optionalStagiaire);

        // Appeler la méthode recherch du service
        StagiaireDTO foundStagiaireDTO = stagiaireService.recherch(stagiaireId);

        // Vérifier si les valeurs sont conformes aux attentes
        assertEquals(stagiaire.getId(), foundStagiaireDTO.getId());
        assertEquals(stagiaire.getNom(), foundStagiaireDTO.getNom());
        assertEquals(stagiaire.getPrenom(), foundStagiaireDTO.getPrenom());



        // Vérifier si la méthode findById du repository a été appelée
        verify(stagiaireRepository, times(1)).findById(stagiaireId);
    }



    @Test
    void testDeleteStagiaire() {
        // ID du stagiaire à supprimer
        Long stagiaireId = 1L;

        // Appeler la méthode delete du service
        stagiaireService.delete(stagiaireId);

        // Vérifier si la méthode deleteById du repository a été appelée
        verify(stagiaireRepository, times(1)).deleteById(stagiaireId);
    }

    @Test
    void testFindAllStagiaire() {
        // Créer deux stagiaires
        Stagiaire stagiaire1 = new Stagiaire();
        Stagiaire stagiaire2 = new Stagiaire();

        // Assurez-vous que chaque stagiaire a un stage non null
        stagiaire1.setStage(new Stage());
        stagiaire2.setStage(new Stage());

        List<Stagiaire> stagiaireList = new ArrayList<>();
        stagiaireList.add(stagiaire1);
        stagiaireList.add(stagiaire2);

        // Configurer le comportement du repository mock
        when(stagiaireRepository.findAll()).thenReturn(stagiaireList);

        // Appeler la méthode du service
        List<StagiaireDTO> foundStagiaireDTOs = stagiaireService.findAll();

        // Vérifier si le nombre d'éléments retournés correspond au nombre de stagiaires créés
        assertEquals(stagiaireList.size(), foundStagiaireDTOs.size());
    }

}