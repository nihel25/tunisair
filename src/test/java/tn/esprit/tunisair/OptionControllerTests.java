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
public class OptionControllerTests {

    @Mock
    private OptionService optionService;

    @InjectMocks
    private OptionController optionController;



    @Test
    void testListe() {
        // Créer une liste simulée de SpecialiteeDTO
        List<SpecialiteeDTO> specialiteeDTOList = new ArrayList<>();
        // Ajouter des éléments à la liste selon les besoins

        // Configurer le comportement du service mock
        when(optionService.findAllspecialite()).thenReturn(specialiteeDTOList);

        // Appeler la méthode liste du contrôleur
        List<SpecialiteeDTO> result = optionController.liste();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(specialiteeDTOList, result);

        // Vérifier si la méthode findAllspecialite du service a été appelée
        verify(optionService, times(1)).findAllspecialite();
    }
}
