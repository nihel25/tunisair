package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.DemandeclientController;
import tn.esprit.tunisair.dto.DemandeclientDTO;
import tn.esprit.tunisair.service.DemandeformationclientService;
import tn.esprit.tunisair.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DemandeclientControllerTests {

    @Mock
    private DemandeformationclientService demandeformationclientService;

    @Mock
    private UserService userService;

    @InjectMocks
    private DemandeclientController demandeclientController;

    @Test
    void testAddDemande() {
        // Créer un exemple de DemandeclientDTO
        DemandeclientDTO demandeclientDTO = new DemandeclientDTO();

        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(demandeformationclientService.add(demandeclientDTO)).thenReturn(demandeclientDTO);

        // Appeler la méthode adddemande du contrôleur
        ResponseEntity<DemandeclientDTO> responseEntity = demandeclientController.adddemande(demandeclientDTO);

        // Vérifier si le statut de la réponse est conforme aux attentes
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(demandeclientDTO, responseEntity.getBody());

        // Vérifier si la méthode add du service a été appelée
        verify(demandeformationclientService, times(1)).add(demandeclientDTO);
    }

    @Test
    void testRecherchDemande() {
        // ID de la demande à rechercher
        Long demandeId = 1L;

        // Créer un exemple de DemandeclientDTO
        DemandeclientDTO demandeclientDTO = new DemandeclientDTO();
        demandeclientDTO.setId(demandeId);

        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(demandeformationclientService.recherch(demandeId)).thenReturn(demandeclientDTO);

        // Appeler la méthode recherch du contrôleur
        DemandeclientDTO result = demandeclientController.recherch(demandeId);

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(demandeclientDTO, result);

        // Vérifier si la méthode recherch du service a été appelée
        verify(demandeformationclientService, times(1)).recherch(demandeId);
    }

    @Test
    void testDeleteDemande() {
        // ID de la demande à supprimer
        Long demandeId = 1L;

        // Appeler la méthode delete du contrôleur
        demandeclientController.delete(demandeId);

        // Vérifier si la méthode delete du service a été appelée
        verify(demandeformationclientService, times(1)).delete(demandeId);
    }

    @Test
    void testListeDemande() {
        // Créer une liste simulée de DemandeclientDTO
        List<DemandeclientDTO> demandeList = new ArrayList<>();
        // Ajouter des demandes à la liste selon les besoins

        // Configurer le comportement du service mock
        when(demandeformationclientService.findAlldemandeformation()).thenReturn(demandeList);

        // Appeler la méthode liste du contrôleur
        List<DemandeclientDTO> result = demandeclientController.liste();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(demandeList, result);

        // Vérifier si la méthode findAlldemandeformation du service a été appelée
        verify(demandeformationclientService, times(1)).findAlldemandeformation();
    }
}
