package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.SalleController;
import tn.esprit.tunisair.dto.SalleDTO;
import tn.esprit.tunisair.service.SalleService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SalleControllerTests {

    @Mock
    private SalleService salleService;

    @InjectMocks
    private SalleController salleController;

    @Test
    void testDeleteSalle() {
        // Appeler la méthode delete du contrôleur
        salleController.delete(1L);

        // Vérifier si la méthode delete du service a été appelée
        verify(salleService, times(1)).delete(1L);
    }

    @Test
    void testListeSalle() {
        // Créer une liste d'exemple de SalleDTO
        List<SalleDTO> salleDTOList = new ArrayList<>();
        // Ajouter des éléments à la liste selon vos besoins

        // Configurer le comportement du service mock
        when(salleService.findAllSalle()).thenReturn(salleDTOList);

        // Appeler la méthode liste du contrôleur
        List<SalleDTO> result = salleController.liste();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(salleDTOList, result);

        // Vérifier si la méthode findAllSalle du service a été appelée
        verify(salleService, times(1)).findAllSalle();
    }

    @Test
    void testAddSalle() {
        // Créer un exemple de SalleDTO
        SalleDTO salleDTO = new SalleDTO();
        salleDTO.setNomsalle("Test Salle");
        salleDTO.setNombreplace(100L);

        // Configurer le comportement du service mock
        when(salleService.save(salleDTO)).thenReturn(salleDTO);

        // Appeler la méthode addsalle du contrôleur
        ResponseEntity<SalleDTO> responseEntity = salleController.addsalle(salleDTO);

        // Vérifier si le statut de la réponse est conforme aux attentes
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(salleDTO, responseEntity.getBody());

        // Vérifier si la méthode save du service a été appelée
        verify(salleService, times(1)).save(salleDTO);
    }

    @Test
    void testRecherchSalle() {
        // ID de la salle à rechercher
        Long salleId = 1L;

        // Créer un exemple de SalleDTO
        SalleDTO salleDTO = new SalleDTO();
        salleDTO.setId(salleId);
        salleDTO.setNomsalle("Test Salle");
        salleDTO.setNombreplace(100L);

        // Configurer le comportement du service mock
        when(salleService.recherch(salleId)).thenReturn(salleDTO);

        // Appeler la méthode recherch du contrôleur
        SalleDTO result = salleController.recherch(salleId);

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(salleDTO, result);

        // Vérifier si la méthode recherch du service a été appelée
        verify(salleService, times(1)).recherch(salleId);
    }
}

