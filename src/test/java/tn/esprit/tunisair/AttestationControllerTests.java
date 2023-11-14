package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.AttestationController;
import tn.esprit.tunisair.dto.AttestationDTO;
import tn.esprit.tunisair.service.AttestationService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AttestationControllerTests {

    @Mock
    private AttestationService attestationService;

    @InjectMocks
    private AttestationController attestationController;

    @Test
    void testAjoutAttestation() {
        // Créer un exemple d'AttestationDTO
        AttestationDTO attestationDTO = new AttestationDTO();
        attestationDTO.setId(1L);
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(attestationService.save(attestationDTO)).thenReturn(attestationDTO);

        // Appeler la méthode ajout du contrôleur
        ResponseEntity<AttestationDTO> responseEntity = attestationController.ajout(attestationDTO);

        // Vérifier si le statut de la réponse est conforme aux attentes
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(attestationDTO, responseEntity.getBody());

        // Vérifier si la méthode save du service a été appelée
        verify(attestationService, times(1)).save(attestationDTO);
    }

    @Test
    void testRecherchAttestation() {
        // ID de l'attestation à rechercher
        Long attestationId = 1L;

        // Créer un exemple d'AttestationDTO
        AttestationDTO attestationDTO = new AttestationDTO();
        attestationDTO.setId(attestationId);
        attestationDTO.setId(1L);
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(attestationService.recherch(attestationId)).thenReturn(attestationDTO);

        // Appeler la méthode recherch du contrôleur
        AttestationDTO result = attestationController.recherch(attestationId);

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(attestationDTO, result);

        // Vérifier si la méthode recherch du service a été appelée
        verify(attestationService, times(1)).recherch(attestationId);
    }

    @Test
    void testFindAllAttestation() {
        // Créer une liste d'exemple d'AttestationDTO
        List<AttestationDTO> attestationDTOList = new ArrayList<>();
        // Ajouter des éléments à la liste selon vos besoins

        // Configurer le comportement du service mock
        when(attestationService.findAll()).thenReturn(attestationDTOList);

        // Appeler la méthode findAll du contrôleur
        List<AttestationDTO> result = attestationController.liste();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(attestationDTOList, result);

        // Vérifier si la méthode findAll du service a été appelée
        verify(attestationService, times(1)).findAll();
    }

    @Test
    void testDeleteAttestation() {
        // ID de l'attestation à supprimer
        Long attestationId = 1L;

        // Appeler la méthode delete du contrôleur
        attestationController.delete(attestationId);

        // Vérifier si la méthode delete du service a été appelée
        verify(attestationService, times(1)).delete(attestationId);
    }
}
