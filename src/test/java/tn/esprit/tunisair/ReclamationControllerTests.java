package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.ReclamationController;
import tn.esprit.tunisair.dto.ReclamationDTO;
import tn.esprit.tunisair.service.ReclamationService;
import tn.esprit.tunisair.service.ReclamationServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReclamationControllerTests {

    @Mock
    private ReclamationService reclamationService;

    @Mock
    private ReclamationServiceImpl reclamationServicee;

    @InjectMocks
    private ReclamationController reclamationController;

    @Test
    void testAjoutReclamation() {
        // Créer un exemple de ReclamationDTO
        ReclamationDTO reclamationDTO = new ReclamationDTO();
        reclamationDTO.setTypeReclamation("Sujet de la réclamation");

        when(reclamationService.save(reclamationDTO)).thenReturn(reclamationDTO);

        // Appeler la méthode ajout du contrôleur
        ResponseEntity<ReclamationDTO> responseEntity = reclamationController.ajout(reclamationDTO);

        // Vérifier si le statut de la réponse est conforme aux attentes
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(reclamationDTO, responseEntity.getBody());

        // Vérifier si la méthode save du service a été appelée
        verify(reclamationService, times(1)).save(reclamationDTO);
    }

    @Test
    void testRecherchReclamation() {
        // ID de la réclamation à rechercher
        Long reclamationId = 1L;

        // Créer un exemple de ReclamationDTO
        ReclamationDTO reclamationDTO = new ReclamationDTO();
        reclamationDTO.setId(reclamationId);
        reclamationDTO.setTypeReclamation("Sujet de la réclamation");

        when(reclamationService.recherch(reclamationId)).thenReturn(reclamationDTO);


        ReclamationDTO result = reclamationController.recherch(reclamationId);


        assertEquals(reclamationDTO, result);


        verify(reclamationService, times(1)).recherch(reclamationId);
    }

    @Test
    void testFindAllReclamation() {
        // Créer une liste d'exemple de ReclamationDTO
        List<ReclamationDTO> reclamationDTOList = new ArrayList<>();
        // Ajouter des éléments à la liste selon vos besoins

        // Configurer le comportement du service mock
        when(reclamationServicee.findAllreclamation()).thenReturn(reclamationDTOList);

        // Appeler la méthode findAllreclamation du contrôleur
        List<ReclamationDTO> result = reclamationController.findAllreclamation();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(reclamationDTOList, result);

        // Vérifier si la méthode findAllreclamation du service a été appelée
        verify(reclamationServicee, times(1)).findAllreclamation();
    }

    @Test
    void testDeleteReclamation() {
        // ID de la réclamation à supprimer
        Long reclamationId = 1L;

        // Appeler la méthode delete du contrôleur
        reclamationController.delete(reclamationId);

        // Vérifier si la méthode delete du service a été appelée
        verify(reclamationServicee, times(1)).delete(reclamationId);
    }
}
