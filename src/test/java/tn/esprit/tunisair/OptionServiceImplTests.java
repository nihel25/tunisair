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

        SpecialiteeDTO specialiteeDTO = new SpecialiteeDTO();
        Specialitee specialitee = SpecialiteeDTO.toentity(specialiteeDTO);
        doReturn(specialitee).when(specialiteeRepository).save(any(Specialitee.class));
        SpecialiteeDTO savedSpecialiteeDTO = optionService.ajouterOption(specialiteeDTO);
        assertEquals(specialiteeDTO.getTypes(), savedSpecialiteeDTO.getTypes());
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
        when(specialiteeRepository.findAll()).thenReturn(specialiteeList);
        List<SpecialiteeDTO> foundSpecialiteeDTOs = optionService.findAllspecialite();
        assertEquals(specialiteeList.size(), foundSpecialiteeDTOs.size());
    }
}
