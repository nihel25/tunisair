package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.DemandeformationController;
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

    // Ajoutez des tests similaires pour les autres méthodes du contrôleur selon les besoins
}
