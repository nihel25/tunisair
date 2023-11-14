package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import tn.esprit.tunisair.controller.PersonnelleController;
import tn.esprit.tunisair.dto.PersonnelDTO;
import tn.esprit.tunisair.service.PersonnelleService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonnelleControllerTests {

    @Mock
    private PersonnelleService personnelleService;

    @InjectMocks
    private PersonnelleController personnelleController;

    @Test
    void testSavePersonnel() {
        // Créer un exemple de PersonnelDTO
        PersonnelDTO personnelDTO = new PersonnelDTO();
        personnelDTO.setNom("Nom du personnel");
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(personnelleService.save(personnelDTO)).thenReturn(personnelDTO);

        // Appeler la méthode save du contrôleur
        ResponseEntity<PersonnelDTO> responseEntity = personnelleController.add(personnelDTO);

        // Vérifier si le statut de la réponse est conforme aux attentes
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(personnelDTO, responseEntity.getBody());

        // Vérifier si la méthode save du service a été appelée
        verify(personnelleService, times(1)).save(personnelDTO);
    }

    @Test
    void testChargerCSV() throws IOException {
        // Créer un fichier CSV simulé
        MockMultipartFile fichierCSV = new MockMultipartFile("fichier", "test.csv", MediaType.TEXT_PLAIN_VALUE, "Contenu du fichier".getBytes());

        // Appeler la méthode chargerCSV du contrôleur
        String result = personnelleController.chargerCSV(fichierCSV);

        // Vérifier si la méthode chargerDonneesCSV du service a été appelée
        verify(personnelleService, times(1)).chargerDonneesCSV(fichierCSV);
    }

    @Test
    void testDeletePersonnel() {
        // ID du personnel à supprimer
        Long personnelId = 1L;

        // Appeler la méthode delete du contrôleur
        personnelleController.delete(personnelId);

        // Vérifier si la méthode delete du service a été appelée
        verify(personnelleService, times(1)).delete(personnelId);
    }

    @Test
    void testRecherchPersonnel() {
        // ID du personnel à rechercher
        Long personnelId = 1L;

        // Créer un exemple de PersonnelDTO
        PersonnelDTO personnelDTO = new PersonnelDTO();
        personnelDTO.setId(personnelId);
        personnelDTO.setNom("Nom du personnel");
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(personnelleService.recherch(personnelId)).thenReturn(personnelDTO);

        // Appeler la méthode recherch du contrôleur
        PersonnelDTO result = personnelleController.recherch(personnelId);

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(personnelDTO, result);

        // Vérifier si la méthode recherch du service a été appelée
        verify(personnelleService, times(1)).recherch(personnelId);
    }
}
