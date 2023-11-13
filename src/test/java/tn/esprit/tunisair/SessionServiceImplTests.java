package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.repository.SessionRepository;
import tn.esprit.tunisair.service.SessionServiceImpl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class SessionServiceImplTests {

@Mock
    SessionRepository sessionRepository;


        @InjectMocks
    SessionServiceImpl sessionService;
    @Test
    void testDeleteSession() {

        Long id = 1L;


        sessionService.delete(id);


        verify(sessionRepository, times(1)).deleteById(id);
    }
}
