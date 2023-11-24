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
 class SessionServiceImplTests {

@Mock
    SessionRepository sessionRepository;


        @InjectMocks
    SessionServiceImpl sessionService;





    @Test
    void testDeletSesion() {

        Long id = 1L;


        sessionService.delete(id);


        verify(sessionRepository, times(1)).deleteById(id);
    }



    @Test
    void testRecherchSession() {

        Long sessionId = 1L;
        FormationDTO formationDTO = new FormationDTO();
        UserprofilDTO userprofilDTO =new UserprofilDTO();
        FormateurDto formateurDto = new FormateurDto();
        formationDTO.setFormateurDto(formateurDto);
        formationDTO.setUserprofildto(userprofilDTO);
        SalleDTO salleDTO = new SalleDTO();

        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setId(sessionId);
        sessionDTO.setSalleDTO(salleDTO);
        sessionDTO.setFormateurDto(formateurDto);

sessionDTO.setFormationDTO(formationDTO);

        Session session = SessionDTO.toentity(sessionDTO);
        Optional<Session> optionalSession = Optional.of(session);
        when(sessionRepository.findById(sessionId)).thenReturn(optionalSession);
        SessionDTO foundSessionDTO = sessionService.recherch(sessionId);
        verify(sessionRepository, times(1)).findById(sessionId);
    }

    @Test
    void testFindAllSession() {

        Session session1 = new Session();
        Session session2 = new Session();

        List<Session> sessionList = new ArrayList<>();
        sessionList.add(session1);
        sessionList.add(session2);
        when(sessionRepository.findAll()).thenReturn(sessionList);
        List<SessionDTO> foundSessionDTOs = sessionService.findAllSession();
        assertEquals(sessionList.size(), foundSessionDTOs.size());
    }


    @Test
    void testSaveSession() {
        // Créer un objet Session
        Session session = new Session();
        session.setReference("SessionTest");
        session.setDateDebut(new Date());
        sessionService.savee(session);

        verify(sessionRepository, times(1)).save(session);
    }

}

