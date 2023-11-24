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
 class ProfiluserControllerTests {

    @Mock
    private UserProfileService userProfileService;

    @InjectMocks
    private ProfiluserController profiluserController;

    @Test
    void testChargerCSV() {

        MultipartFile file = new MockMultipartFile("fichier", "test.csv", MediaType.TEXT_PLAIN_VALUE, "CSV data".getBytes());

        doNothing().when(userProfileService).chargerDonneesCSV(file);
        String result = profiluserController.chargerCSV(file);
        assertEquals(null, result);
        verify(userProfileService, times(1)).chargerDonneesCSV(file);
    }

    @Test
    void testGetAllUserProfiles() {
        List<UserProfile> userProfileList = new ArrayList<>();
        when(userProfileService.getAllUserProfiles()).thenReturn(userProfileList);
        List<UserProfile> result = profiluserController.getAllUserProfiles();
        assertEquals(userProfileList, result);
        verify(userProfileService, times(1)).getAllUserProfiles();
    }


}

