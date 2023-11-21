package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.SessionController;
import tn.esprit.tunisair.dto.PersonnelDTO;
import tn.esprit.tunisair.dto.SessionDTO;
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



    @Test
    void testDeleteSession() {
        // ID de la session à supprimer
        Long sessionId = 1L;

        // Appeler la méthode du contrôleur
        sessionController.delete(sessionId);

        // Vérifier si la méthode du service a été appelée
        verify(sessionService, times(1)).delete(sessionId);
    }





    @Test
    void testRecherchSession() {
        // ID de la session à rechercher
        Long sessionId = 1L;

        // Créer un objet SessionDTO avec des valeurs appropriées
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setId(sessionId);
        // Initialisez les propriétés de sessionDTO selon les besoins

        // Configurer le comportement du service mock
        when(sessionService.recherch(sessionId)).thenReturn(sessionDTO);

        // Appeler la méthode du contrôleur
        SessionDTO foundSessionDTO = sessionController.recherch(sessionId);

        // Vérifier si les valeurs sont conformes aux attentes
        assertEquals(sessionDTO.getId(), foundSessionDTO.getId());

        // Vérifier si la méthode du service a été appelée
        verify(sessionService, times(1)).recherch(sessionId);
    }

    @Test
    void testFindAllSessions() {
        // Utiliser Mockito pour simuler le service
        List<SessionDTO> sessionList = new ArrayList<>();
        when(sessionService.findAllSession()).thenReturn(sessionList);

        // Appeler la méthode du contrôleur
        List<SessionDTO> foundSessionDTOs = sessionController.findAll();

        // Vérifier si la méthode du service a été appelée
        verify(sessionService, times(1)).findAllSession();
    }

}
