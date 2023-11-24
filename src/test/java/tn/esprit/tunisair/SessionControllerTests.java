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
import tn.esprit.tunisair.service.SessionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class SessionControllerTests {

    @Mock
    private SessionService sessionService;



    @InjectMocks
    private SessionController sessionController;



    @Test
    void testGetAllPersonnel() {

        List<PersonnelDTO> personnelDTOList = new ArrayList<>();
        when(sessionService.getAllPersonnel()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Personnel>> result = sessionController.getAllPersonnel();
        assertEquals(new ArrayList<>(), result.getBody());
        verify(sessionService, times(1)).getAllPersonnel();
    }



    @Test
    void testDeleteSession() {

        Long sessionId = 1L;
        sessionController.delete(sessionId);
        verify(sessionService, times(1)).delete(sessionId);
    }





    @Test
    void testRecherchSession() {

        Long sessionId = 1L;
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setId(sessionId);



        when(sessionService.recherch(sessionId)).thenReturn(sessionDTO);
        SessionDTO foundSessionDTO = sessionController.recherch(sessionId);

        assertEquals(sessionDTO.getId(), foundSessionDTO.getId());
        verify(sessionService, times(1)).recherch(sessionId);
    }

    @Test
    void testFindAllSessions() {

        List<SessionDTO> sessionList = new ArrayList<>();
        when(sessionService.findAllSession()).thenReturn(sessionList);
        List<SessionDTO> foundSessionDTOs = sessionController.findAll();
        verify(sessionService, times(1)).findAllSession();
    }

}
