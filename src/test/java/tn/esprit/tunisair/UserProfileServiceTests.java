package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.entity.UserProfile;
import tn.esprit.tunisair.repository.UserProfileRepository;
import tn.esprit.tunisair.service.UserProfileService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserProfileServiceTests {

    @Mock
    private UserProfileRepository userProfileRepository;

    @InjectMocks
    private UserProfileService userProfileService;

  

    @Test
    void testGetAllUserProfiles() {
        // Créer une liste simulée de UserProfile
        List<UserProfile> userProfileList = new ArrayList<>();
        // Ajouter des éléments à la liste selon les besoins

        // Configurer le comportement du repository mock
        when(userProfileRepository.findAll()).thenReturn(userProfileList);

        // Appeler la méthode getAllUserProfiles du service
        List<UserProfile> result = userProfileService.getAllUserProfiles();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(userProfileList, result);

        // Vérifier si la méthode findAll du repository a été appelée
        verify(userProfileRepository, times(1)).findAll();
    }

    // Ajoutez d'autres tests en fonction des autres méthodes de la classe UserProfileService
}
