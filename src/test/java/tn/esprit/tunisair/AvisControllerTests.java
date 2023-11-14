package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.AvisController;
import tn.esprit.tunisair.dto.AvisDto;
import tn.esprit.tunisair.service.AvisService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AvisControllerTests {

    @Mock
    private AvisService avisService;



    @InjectMocks
    private AvisController avisController;

    @Test
    void testAddAvis() {
        // Créer un exemple d'AvisDto
        AvisDto avisDto = new AvisDto();
        avisDto.setText("Ceci est un avis de test");
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(avisService.save(avisDto)).thenReturn(avisDto);

        // Appeler la méthode addavis du contrôleur
        ResponseEntity<AvisDto> responseEntity = avisController.addavis(avisDto);

        // Vérifier si le statut de la réponse est conforme aux attentes
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(avisDto, responseEntity.getBody());

        // Vérifier si la méthode save du service a été appelée
        verify(avisService, times(1)).save(avisDto);
    }

    @Test
    void testRecherchAvis() {
        // ID de l'avis à rechercher
        Long avisId = 1L;

        // Créer un exemple d'AvisDto
        AvisDto avisDto = new AvisDto();
        avisDto.setId(avisId);
        avisDto.setText("Ceci est un avis de test");
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(avisService.recherch(avisId)).thenReturn(avisDto);

        // Appeler la méthode recherch du contrôleur
        AvisDto result = avisController.recherch(avisId);

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(avisDto, result);

        // Vérifier si la méthode recherch du service a été appelée
        verify(avisService, times(1)).recherch(avisId);
    }



    @Test
    void testListeAvis() {
        // Créer une liste d'exemple d'AvisDto
        List<AvisDto> avisDtoList = new ArrayList<>();
        // Ajouter des éléments à la liste selon vos besoins

        // Configurer le comportement du service mock
        when(avisService.findAll()).thenReturn(avisDtoList);

        // Appeler la méthode liste du contrôleur
        List<AvisDto> result = avisController.liste();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(avisDtoList, result);

        // Vérifier si la méthode findAll du service a été appelée
        verify(avisService, times(1)).findAll();
    }
}
