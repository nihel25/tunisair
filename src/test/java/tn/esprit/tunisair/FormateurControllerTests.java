package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.FormateurController;
import tn.esprit.tunisair.dto.FormateurDto;
import tn.esprit.tunisair.service.FormateurService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FormateurControllerTests {

    @Mock
    private FormateurService formateurService;

    @InjectMocks
    private FormateurController formateurController;

    @Test
    void testAddFormateur() {
        // Créer un exemple de FormateurDto
        FormateurDto formateurDto = new FormateurDto();
        formateurDto.setNom("Nom du formateur");
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(formateurService.save(formateurDto)).thenReturn(formateurDto);

        // Appeler la méthode save du contrôleur
        ResponseEntity<FormateurDto> responseEntity = formateurController.addformateur(formateurDto);

        // Vérifier si le statut de la réponse est conforme aux attentes
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(formateurDto, responseEntity.getBody());

        // Vérifier si la méthode save du service a été appelée
        verify(formateurService, times(1)).save(formateurDto);
    }

    @Test
    void testRecherchFormateur() {
        // ID du formateur à rechercher
        Long formateurId = 1L;

        // Créer un exemple de FormateurDto
        FormateurDto formateurDto = new FormateurDto();
        formateurDto.setId(formateurId);
        formateurDto.setNom("Nom du formateur");
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(formateurService.recherch(formateurId)).thenReturn(formateurDto);

        // Appeler la méthode recherch du contrôleur
        FormateurDto result = formateurController.recherch(formateurId);

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(formateurDto, result);

        // Vérifier si la méthode recherch du service a été appelée
        verify(formateurService, times(1)).recherch(formateurId);
    }

    @Test
    void testDeleteFormateur() {
        // ID du formateur à supprimer
        Long formateurId = 1L;

        // Appeler la méthode delete du contrôleur
        formateurController.delete(formateurId);

        // Vérifier si la méthode delete du service a été appelée
        verify(formateurService, times(1)).delete(formateurId);
    }

    @Test
    void testListeFormateur() {
        // Créer une liste simulée de FormateurDto
        List<FormateurDto> formateurList = new ArrayList<>();
        // Ajouter des formateurs à la liste selon les besoins

        // Configurer le comportement du service mock
        when(formateurService.findAll()).thenReturn(formateurList);

        // Appeler la méthode liste du contrôleur
        List<FormateurDto> result = formateurController.liste();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(formateurList, result);

        // Vérifier si la méthode findAll du service a été appelée
        verify(formateurService, times(1)).findAll();
    }
}
