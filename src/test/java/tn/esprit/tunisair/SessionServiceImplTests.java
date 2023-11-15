package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.*;
import tn.esprit.tunisair.entity.Session;
import tn.esprit.tunisair.repository.SessionRepository;
import tn.esprit.tunisair.service.SessionServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SessionServiceImplTests {

@Mock
    SessionRepository sessionRepository;


        @InjectMocks
    SessionServiceImpl sessionService;





    @Test
    void testDeletSession() {

        Long id = 1L;


        sessionService.delete(id);


        verify(sessionRepository, times(1)).deleteById(id);
    }



    @Test
    void testRecherchSession() {
        // ID de la session à rechercher
        Long sessionId = 1L;
        FormationDTO formationDTO = new FormationDTO();
        UserprofilDTO userprofilDTO =new UserprofilDTO();
        FormateurDto formateurDto = new FormateurDto();
        formationDTO.setFormateurDto(formateurDto);
        formationDTO.setUserprofildto(userprofilDTO);
        SalleDTO salleDTO = new SalleDTO();
        // Créer un objet SessionDTO avec des valeurs appropriées
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setId(sessionId);
        sessionDTO.setSalleDTO(salleDTO);
        sessionDTO.setFormateurDto(formateurDto);
        // Initialisez les propriétés du sessionDTO selon les besoins
sessionDTO.setFormationDTO(formationDTO);
        // Convertir SessionDTO en Session
        Session session = SessionDTO.toentity(sessionDTO);

        // Créer un Optional<Session>
        Optional<Session> optionalSession = Optional.of(session);

        // Configurer le comportement du repository mock
        when(sessionRepository.findById(sessionId)).thenReturn(optionalSession);

        // Appeler la méthode recherch du service
        SessionDTO foundSessionDTO = sessionService.recherch(sessionId);

        // Vérifier si les valeurs sont conformes aux attentes
        // (ajoutez des assertions appropriées selon les propriétés de l'objet)

        // Vérifier si la méthode findById du repository a été appelée
        verify(sessionRepository, times(1)).findById(sessionId);
    }

    @Test
    void testFindAllSession() {
        // Créer des objets Session pour simuler la liste retournée par le repository
        Session session1 = new Session();
        Session session2 = new Session();
        // Initialisez les propriétés des sessions selon les besoins

        List<Session> sessionList = new ArrayList<>();
        sessionList.add(session1);
        sessionList.add(session2);

        // Configurer le comportement du repository mock
        when(sessionRepository.findAll()).thenReturn(sessionList);

        // Appeler la méthode findAll du service
        List<SessionDTO> foundSessionDTOs = sessionService.findAllSession();

        // Vérifier si la taille de la liste retournée correspond à la taille de la liste simulée
        assertEquals(sessionList.size(), foundSessionDTOs.size());
    }


    @Test
    void testSaveSession() {
        // Créer un objet Session
        Session session = new Session();
        session.setReference("SessionTest");
        session.setDateDebut(new Date());
        // Initialisez les autres propriétés de la session selon vos besoins

        // Appeler la méthode savee du service
        sessionService.savee(session);

        // Vérifier si la méthode save du repository a été appelée
        verify(sessionRepository, times(1)).save(session);
    }

}

