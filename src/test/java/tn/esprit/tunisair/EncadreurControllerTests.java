package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.EncadreurController;
import tn.esprit.tunisair.dto.EncadreurDTO;
import tn.esprit.tunisair.service.EncadreurService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EncadreurControllerTests {

    @Mock
    private EncadreurService encadreurService;

    @InjectMocks
    private EncadreurController encadreurController;

    @Test
    void testAddEncadreur() {
        // Créer un exemple d'EncadreurDTO
        EncadreurDTO encadreurDTO = new EncadreurDTO();
        encadreurDTO.setNom("Test Encadreur");
        encadreurDTO.setPrenom("Encadreur");
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(encadreurService.save(encadreurDTO)).thenReturn(encadreurDTO);

        // Appeler la méthode addencadreur du contrôleur
        ResponseEntity<EncadreurDTO> responseEntity = encadreurController.addencadreur(encadreurDTO);

        // Vérifier si le statut de la réponse est conforme aux attentes
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(encadreurDTO, responseEntity.getBody());

        // Vérifier si la méthode save du service a été appelée
        verify(encadreurService, times(1)).save(encadreurDTO);
    }

    @Test
    void testDeleteEncadreur() {
        // Appeler la méthode delete du contrôleur
        encadreurController.delete(1L);

        // Vérifier si la méthode delete du service a été appelée
        verify(encadreurService, times(1)).delete(1L);
    }

    @Test
    void testListeEncadreur() {
        // Créer une liste d'exemple d'EncadreurDTO
        List<EncadreurDTO> encadreurDTOList = new ArrayList<>();
        // Ajouter des éléments à la liste selon vos besoins

        // Configurer le comportement du service mock
        when(encadreurService.findAll()).thenReturn(encadreurDTOList);

        // Appeler la méthode liste du contrôleur
        List<EncadreurDTO> result = encadreurController.liste();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(encadreurDTOList, result);

        // Vérifier si la méthode findAll du service a été appelée
        verify(encadreurService, times(1)).findAll();
    }

    @Test
    void testRecherchEncadreur() {
        // ID de l'encadreur à rechercher
        Long encadreurId = 1L;

        // Créer un exemple d'EncadreurDTO
        EncadreurDTO encadreurDTO = new EncadreurDTO();
        encadreurDTO.setId(encadreurId);
        encadreurDTO.setNom("Test Encadreur");
        encadreurDTO.setPrenom("Encadreur");
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(encadreurService.recherch(encadreurId)).thenReturn(encadreurDTO);

        // Appeler la méthode recherch du contrôleur
        EncadreurDTO result = encadreurController.recherch(encadreurId);

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(encadreurDTO, result);

        // Vérifier si la méthode recherch du service a été appelée
        verify(encadreurService, times(1)).recherch(encadreurId);
    }
}
