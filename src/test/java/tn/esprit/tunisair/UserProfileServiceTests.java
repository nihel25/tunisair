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
    void testGetAllUserProfile() {
        List<UserProfile> userProfileList = new ArrayList<>();

        when(userProfileRepository.findAll()).thenReturn(userProfileList);

        List<UserProfile> result = userProfileService.getAllUserProfiles();

        assertEquals(userProfileList, result);

        verify(userProfileRepository, times(1)).findAll();
    }


}
