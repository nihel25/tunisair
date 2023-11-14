package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.SessionController;
import tn.esprit.tunisair.dto.PersonnelDTO;
import tn.esprit.tunisair.entity.Personnel;
import tn.esprit.tunisair.service.FormateurService;
import tn.esprit.tunisair.service.SalleService;
import tn.esprit.tunisair.service.SessionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessionControllerTests {

    @Mock
    private SessionService sessionService;

    @Mock
    private SalleService salleService;

    @Mock
    private FormateurService formateurService;

    @InjectMocks
    private SessionController sessionController;



    @Test
    void testGetAllPersonnel() {
        // Créer une liste simulée de personnel
        List<PersonnelDTO> personnelDTOList = new ArrayList<>();
        // Ajouter des éléments à la liste selon les besoins

        // Configurer le comportement du service mock
        when(sessionService.getAllPersonnel()).thenReturn(new ArrayList<>());

        // Appeler la méthode getAllPersonnel du contrôleur
        ResponseEntity<List<Personnel>> result = sessionController.getAllPersonnel();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(new ArrayList<>(), result.getBody());

        // Vérifier si la méthode getAllPersonnel du service a été appelée
        verify(sessionService, times(1)).getAllPersonnel();
    }

    // Ajoutez d'autres tests en fonction des autres méthodes du contrôleur
}
