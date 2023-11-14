package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.CertificatController;
import tn.esprit.tunisair.dto.CertificatDTO;
import tn.esprit.tunisair.service.CertificatService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CertificatControllerTests {

    @Mock
    private CertificatService certificatService;

    @InjectMocks
    private CertificatController certificatController;

    @Test
    void testSaveCertificat() {
        // Créer un exemple de CertificatDTO
        CertificatDTO certificatDTO = new CertificatDTO();
        certificatDTO.setId(1L);
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(certificatService.save(certificatDTO)).thenReturn(certificatDTO);

        // Appeler la méthode save du contrôleur
        ResponseEntity<CertificatDTO> responseEntity = certificatController.save(certificatDTO);

        // Vérifier si le statut de la réponse est conforme aux attentes
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(certificatDTO, responseEntity.getBody());

        // Vérifier si la méthode save du service a été appelée
        verify(certificatService, times(1)).save(certificatDTO);
    }

    @Test
    void testRecherchCertificat() {
        // ID du certificat à rechercher
        Long certificatId = 1L;

        // Créer un exemple de CertificatDTO
        CertificatDTO certificatDTO = new CertificatDTO();
        certificatDTO.setId(certificatId);
        certificatDTO.setId(1L);
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du service mock
        when(certificatService.recherch(certificatId)).thenReturn(certificatDTO);

        // Appeler la méthode recherch du contrôleur
        CertificatDTO result = certificatController.recherch(certificatId);

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(certificatDTO, result);

        // Vérifier si la méthode recherch du service a été appelée
        verify(certificatService, times(1)).recherch(certificatId);
    }

    @Test
    void testFindAllCertificat() {
        // Créer une liste d'exemple de CertificatDTO
        List<CertificatDTO> certificatDTOList = new ArrayList<>();
        // Ajouter des éléments à la liste selon vos besoins

        // Configurer le comportement du service mock
        when(certificatService.findAll()).thenReturn(certificatDTOList);

        // Appeler la méthode findAll du contrôleur
        List<CertificatDTO> result = certificatController.liste();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(certificatDTOList, result);

        // Vérifier si la méthode findAll du service a été appelée
        verify(certificatService, times(1)).findAll();
    }

    @Test
    void testDeleteCertificat() {
        // ID du certificat à supprimer
        Long certificatId = 1L;

        // Appeler la méthode delete du contrôleur
        certificatController.delete(certificatId);

        // Vérifier si la méthode delete du service a été appelée
        verify(certificatService, times(1)).delete(certificatId);
    }
}
