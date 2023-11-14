package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.controller.AuthenticationController;
import tn.esprit.tunisair.service.UserService;
import tn.esprit.tunisair.service.UserServiceimpl;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTests {

    @Mock
    private UserService service;

    @Mock
    private UserServiceimpl userServiceimpl;

    @InjectMocks
    private AuthenticationController authenticationController;



    @Test
    void testDeleteUser() {
        // Configurer le comportement du service mock
        doNothing().when(service).delete(Mockito.anyLong());

        // Appeler la méthode delete du contrôleur
        authenticationController.delete(1L);

        // Vérifier si la méthode delete du service a été appelée
        verify(service, times(1)).delete(Mockito.anyLong());
    }

 
}
