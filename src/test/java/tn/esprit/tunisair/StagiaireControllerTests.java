package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.StagiaireController;
import tn.esprit.tunisair.dto.StagiaireDTO;
import tn.esprit.tunisair.service.StagiaireService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StagiaireControllerTests {

    @Mock
    private StagiaireService stagiaireService;

    @InjectMocks
    private StagiaireController stagiaireController;

    @Test
    void testAddStagiaire() {
        // Créer un exemple de StagiaireDTO
        StagiaireDTO stagiaireDTO = new StagiaireDTO();
        stagiaireDTO.setNom("John");
        stagiaireDTO.setPrenom("Doe");
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(stagiaireService.save(stagiaireDTO)).thenReturn(stagiaireDTO);

        // Appeler la méthode addstagiaire du contrôleur
        ResponseEntity<StagiaireDTO> responseEntity = stagiaireController.addstagiaire(stagiaireDTO);

        // Vérifier si le statut de la réponse est conforme aux attentes
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(stagiaireDTO, responseEntity.getBody());

        // Vérifier si la méthode save du service a été appelée
        verify(stagiaireService, times(1)).save(stagiaireDTO);
    }

    @Test
    void testRecherchStagiaire() {
        // ID du stagiaire à rechercher
        Long stagiaireId = 1L;

        // Créer un exemple de StagiaireDTO
        StagiaireDTO stagiaireDTO = new StagiaireDTO();
        stagiaireDTO.setId(stagiaireId);
        stagiaireDTO.setNom("John");
        stagiaireDTO.setPrenom("Doe");
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(stagiaireService.recherch(stagiaireId)).thenReturn(stagiaireDTO);

        // Appeler la méthode recherch du contrôleur
        StagiaireDTO result = stagiaireController.recherch(stagiaireId);

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(stagiaireDTO, result);

        // Vérifier si la méthode recherch du service a été appelée
        verify(stagiaireService, times(1)).recherch(stagiaireId);
    }

    @Test
    void testDeleteStagiaire() {
        // ID du stagiaire à supprimer
        Long stagiaireId = 1L;

        // Appeler la méthode delete du contrôleur
        stagiaireController.delete(stagiaireId);

        // Vérifier si la méthode delete du service a été appelée
        verify(stagiaireService, times(1)).delete(stagiaireId);
    }

    @Test
    void testListeStagiaire() {
        // Créer une liste d'exemple de StagiaireDTO
        List<StagiaireDTO> stagiaireDTOList = new ArrayList<>();
        // Ajouter des éléments à la liste selon vos besoins

        // Configurer le comportement du service mock
        when(stagiaireService.findAll()).thenReturn(stagiaireDTOList);

        // Appeler la méthode liste du contrôleur
        List<StagiaireDTO> result = stagiaireController.liste();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(stagiaireDTOList, result);

        // Vérifier si la méthode findAll du service a été appelée
        verify(stagiaireService, times(1)).findAll();
    }
}
