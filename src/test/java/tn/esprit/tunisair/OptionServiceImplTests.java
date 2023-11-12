package tn.esprit.tunisair;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.SpecialiteeDTO;
import tn.esprit.tunisair.entity.Specialitee;
import tn.esprit.tunisair.repository.SpecialiteeRepository;
import tn.esprit.tunisair.service.OptionServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OptionServiceImplTests {

    @Mock
    private SpecialiteeRepository specialiteeRepository;

    @InjectMocks
    private OptionServiceImpl optionService;

    @Test
    void testAjouterOption() {
        // Créez un SpecialiteeDTO avec des valeurs appropriées
        SpecialiteeDTO specialiteeDTO = new SpecialiteeDTO();
        // Initialisez les propriétés du specialiteeDTO selon les besoins

        // Créez un Specialitee avec des valeurs appropriées
        Specialitee specialitee = SpecialiteeDTO.toentity(specialiteeDTO);

        // Utilisez doReturn().when() pour configurer le stub dans le mock
        doReturn(specialitee).when(specialiteeRepository).save(any(Specialitee.class));

        // Appelez la méthode du service
        SpecialiteeDTO savedSpecialiteeDTO = optionService.ajouterOption(specialiteeDTO);

        // Vérifiez si les valeurs sont conformes aux attentes
        assertEquals(specialiteeDTO.getTypes(), savedSpecialiteeDTO.getTypes());

        // Vérifiez si la méthode save du repository a été appelée
        verify(specialiteeRepository, times(1)).save(any(Specialitee.class));
    }

    @Test
    void testFindAllSpecialite() {
        // Créer deux specialites
        Specialitee specialitee1 = new Specialitee();
        Specialitee specialitee2 = new Specialitee();
        List<Specialitee> specialiteeList = new ArrayList<>();
        specialiteeList.add(specialitee1);
        specialiteeList.add(specialitee2);

        // Configurer le comportement du repository mock
        when(specialiteeRepository.findAll()).thenReturn(specialiteeList);

        // Appeler la méthode du service
        List<SpecialiteeDTO> foundSpecialiteeDTOs = optionService.findAllspecialite();

        // Vérifier si le nombre d'éléments retournés correspond au nombre de specialites créées
        assertEquals(specialiteeList.size(), foundSpecialiteeDTOs.size());
    }
}
