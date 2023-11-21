package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tunisair.controller.ProfiluserController;
import tn.esprit.tunisair.entity.UserProfile;
import tn.esprit.tunisair.service.UserProfileService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfiluserControllerTests {

    @Mock
    private UserProfileService userProfileService;

    @InjectMocks
    private ProfiluserController profiluserController;

    @Test
    void testChargerCSV() {
        // Créer un fichier multipart simulé
        MultipartFile file = new MockMultipartFile("fichier", "test.csv", MediaType.TEXT_PLAIN_VALUE, "CSV data".getBytes());

        // Configurer le comportement du service mock
        doNothing().when(userProfileService).chargerDonneesCSV(file);

        // Appeler la méthode chargerCSV du contrôleur
        String result = profiluserController.chargerCSV(file);

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(null, result);

        // Vérifier si la méthode chargerDonneesCSV du service a été appelée
        verify(userProfileService, times(1)).chargerDonneesCSV(file);
    }

    @Test
    void testGetAllUserProfiles() {
        // Créer une liste simulée de UserProfile
        List<UserProfile> userProfileList = new ArrayList<>();
        // Ajouter des éléments à la liste selon les besoins

        // Configurer le comportement du service mock
        when(userProfileService.getAllUserProfiles()).thenReturn(userProfileList);

        // Appeler la méthode getAllUserProfiles du contrôleur
        List<UserProfile> result = profiluserController.getAllUserProfiles();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(userProfileList, result);

        // Vérifier si la méthode getAllUserProfiles du service a été appelée
        verify(userProfileService, times(1)).getAllUserProfiles();
    }

    // Ajoutez d'autres tests en fonction des autres méthodes du contrôleur
}

