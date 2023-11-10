package tn.esprit.tunisair;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tunisair.dto.FormateurDto;
import tn.esprit.tunisair.entity.Formateur;
import tn.esprit.tunisair.repository.FormateurRepository;
import tn.esprit.tunisair.repository.FormationRepository;
import tn.esprit.tunisair.repository.SpecialiteeRepository;
import tn.esprit.tunisair.service.FormateurServiceImpl;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FormateurServiceImplTest {

    @Mock
    private FormateurRepository formateurRepository;

    @Mock
    private FormationRepository formationRepository;

    @Mock
    private SpecialiteeRepository specialiteeRepository;

    @InjectMocks
    private FormateurServiceImpl formateurService;

    @Test
    @Order(1)
    void testSaveFormateur() {
        // Créez un exemple de FormateurDto
        FormateurDto formateurDto = new FormateurDto(/* Initialisez avec les données nécessaires */);

        // Mockez le comportement de la méthode du repository
        when(formateurRepository.save(any(Formateur.class))).thenReturn(new Formateur());

        // Invitez la méthode et vérifiez le résultat
        FormateurDto savedFormateurDto = formateurService.save(formateurDto);
        assertEquals(formateurDto, savedFormateurDto);

        // Vérifiez que la méthode save de FormateurRepository a été appelée une fois
        verify(formateurRepository, times(1)).save(any(Formateur.class));
    }

    @Test
    @Order(2)
    void testRetrieveAllFormateur() {
        // Mockez le comportement de la méthode du repository
        when(formateurRepository.findAll()).thenReturn(Collections.emptyList());

        // Invitez la méthode et vérifiez le résultat
        List<FormateurDto> listFormateurDto = formateurService.findAll();
        assertEquals(0, listFormateurDto.size());

        // Vérifiez que la méthode findAll de FormateurRepository a été appelée une fois
        verify(formateurRepository, times(1)).findAll();
    }

    // Ajoutez d'autres tests pour d'autres méthodes de FormateurServiceImpl au besoin

    @Test
    @Order(3)
    void testDeleteFormateur() {
        // Mockez le comportement de la méthode du repository
        doNothing().when(formateurRepository).deleteById(anyLong());

        // Invitez la méthode et vérifiez le résultat
        formateurService.delete(1L);

        // Vérifiez que la méthode deleteById de FormateurRepository a été appelée une fois
        verify(formateurRepository, times(1)).deleteById(anyLong());
    }
}

