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


    @Test
    void testAddOption() {
        // Créez un exemple de SpecialiteeDTO pour simuler la requête
        SpecialiteeDTO specialiteeDTO = new SpecialiteeDTO();
        specialiteeDTO.setTypes("Option de test");

        // Configurez le comportement du service mock
        when(optionService.ajouterOption(specialiteeDTO)).thenReturn(specialiteeDTO);

        // Appelez la méthode addoption du contrôleur
        SpecialiteeDTO resultat = optionController.addoption(specialiteeDTO);

        // Vérifiez si la méthode ajouterOption du service a été appelée avec le bon argument
        verify(optionService, times(1)).ajouterOption(specialiteeDTO);

        // Vérifiez si le résultat renvoyé par la méthode correspond à l'objet mocké
        assertEquals(specialiteeDTO, resultat);
    }
}
