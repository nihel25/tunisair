package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.controller.OptionController;
import tn.esprit.tunisair.dto.SpecialiteeDTO;
import tn.esprit.tunisair.service.OptionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class OptionControllerTests {

    @Mock
    private OptionService optionService;

    @InjectMocks
    private OptionController optionController;



    @Test
    void testListe() {

        List<SpecialiteeDTO> specialiteeDTOList = new ArrayList<>();
        when(optionService.findAllspecialite()).thenReturn(specialiteeDTOList);
        List<SpecialiteeDTO> result = optionController.liste();
        assertEquals(specialiteeDTOList, result);
        verify(optionService, times(1)).findAllspecialite();
    }


    @Test
    void testAddOption() {

        SpecialiteeDTO specialiteeDTO = new SpecialiteeDTO();
        specialiteeDTO.setTypes("Option de test");
        when(optionService.ajouterOption(specialiteeDTO)).thenReturn(specialiteeDTO);
        SpecialiteeDTO resultat = optionController.addoption(specialiteeDTO);
        verify(optionService, times(1)).ajouterOption(specialiteeDTO);
        assertEquals(specialiteeDTO, resultat);
    }
}
