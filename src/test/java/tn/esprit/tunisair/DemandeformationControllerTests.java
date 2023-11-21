package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.DemandeformationController;
import tn.esprit.tunisair.dto.DemandeFormationDTO;
import tn.esprit.tunisair.entity.Personnel;
import tn.esprit.tunisair.service.DemandeformationServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DemandeformationControllerTests {

    @Mock
    private DemandeformationServiceImpl demandeFormationService;

    @InjectMocks
    private DemandeformationController demandeformationController;


    @Test
    void testGetAllPersonnel() {
        // Créer une liste simulée de Personnel
        List<Personnel> personnelList = new ArrayList<>();
        // Ajouter des éléments à la liste selon les besoins

        // Configurer le comportement du service mock
        when(demandeFormationService.getAllPersonnel()).thenReturn(personnelList);

        // Appeler la méthode getAllPersonnel du contrôleur
        ResponseEntity<List<Personnel>> result = demandeformationController.getAllPersonnel();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(personnelList, result.getBody());

        // Vérifier si la méthode getAllPersonnel du service a été appelée
        verify(demandeFormationService, times(1)).getAllPersonnel();
    }
    @Test
    void testListeDemande() {
        // Créer une liste simulée de DemandeclientDTO
        List<DemandeFormationDTO> demandeList = new ArrayList<>();
        // Ajouter des demandes à la liste selon les besoins

        // Configurer le comportement du service mock
        when(demandeFormationService.findAllDemandes()).thenReturn(demandeList);

        // Appeler la méthode liste du contrôleur
        List<DemandeFormationDTO> result = demandeformationController.getAllDemandeFormations();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(demandeList, result);

        // Vérifier si la méthode findAlldemandeformation du service a été appelée
        verify(demandeFormationService, times(1)).findAllDemandes();
    }

    @Test
    void testRecherchDemande() {
        // ID de la demande à rechercher
        Long demandeId = 1L;

        // Créer un exemple de DemandeclientDTO
        DemandeFormationDTO demandeclientDTO = new DemandeFormationDTO();
        demandeclientDTO.setId(demandeId);

        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(demandeFormationService.recherch(demandeId)).thenReturn(demandeclientDTO);

        // Appeler la méthode recherch du contrôleur
        DemandeFormationDTO result = demandeformationController.recherch(demandeId);

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(demandeclientDTO, result);

        // Vérifier si la méthode recherch du service a été appelée
        verify(demandeFormationService, times(1)).recherch(demandeId);
    }

    @Test
    void testDeleteDemande() {
        // ID de la demande à supprimer
        Long demandeId = 1L;

        // Appeler la méthode delete du contrôleur
        demandeformationController.delete(demandeId);

        // Vérifier si la méthode delete du service a été appelée
        verify(demandeFormationService, times(1)).delete(demandeId);
    }



}
