package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.FormationController;
import tn.esprit.tunisair.dto.FormationDTO;
import tn.esprit.tunisair.service.FormationService;
import tn.esprit.tunisair.service.ImageStorage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FormationControllerTests {

    @Mock
    private FormationService formationService;

    @Mock
    private ImageStorage imageStorage;

    @InjectMocks
    private FormationController formationController;

    @Test
    void testAddFormation() {
        // Créer un exemple de FormationDTO
        FormationDTO formationDTO = new FormationDTO();
        formationDTO.setRef("Nom de la formation");
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(formationService.save(formationDTO)).thenReturn(formationDTO);

        // Appeler la méthode add du contrôleur
        ResponseEntity<FormationDTO> responseEntity = formationController.add(formationDTO);

        // Vérifier si le statut de la réponse est conforme aux attentes
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(formationDTO, responseEntity.getBody());

        // Vérifier si la méthode save du service a été appelée
        verify(formationService, times(1)).save(formationDTO);
    }

    @Test
    void testRecherchFormation() {
        // ID de la formation à rechercher
        Long formationId = 1L;

        // Créer un exemple de FormationDTO
        FormationDTO formationDTO = new FormationDTO();
        formationDTO.setId(formationId);
        formationDTO.setRef("Nom de la formation");
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(formationService.recherch(formationId)).thenReturn(formationDTO);

        // Appeler la méthode recherch du contrôleur
        FormationDTO result = formationController.recherch(formationId);

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(formationDTO, result);

        // Vérifier si la méthode recherch du service a été appelée
        verify(formationService, times(1)).recherch(formationId);
    }

    @Test
    void testDeleteFormation() {
        // ID de la formation à supprimer
        Long formationId = 1L;

        // Appeler la méthode delete du contrôleur
        formationController.delete(formationId);

        // Vérifier si la méthode delete du service a été appelée
        verify(formationService, times(1)).delete(formationId);
    }

    @Test
    void testListeFormation() {
        // Créer une liste simulée de FormationDTO
        List<FormationDTO> formationList = new ArrayList<>();
        // Ajouter des formations à la liste selon les besoins

        // Configurer le comportement du service mock
        when(formationService.findAllFormation()).thenReturn(formationList);

        // Appeler la méthode liste du contrôleur
        List<FormationDTO> result = formationController.liste();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(formationList, result);

        // Vérifier si la méthode findAllFormation du service a été appelée
        verify(formationService, times(1)).findAllFormation();
    }


}

