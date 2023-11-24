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
 class PersonnelleControllerTests {

    @Mock
    private PersonnelleService personnelleService;

    @InjectMocks
    private PersonnelleController personnelleController;

    @Test
    void testSavePersonnel() {

        PersonnelDTO personnelDTO = new PersonnelDTO();
        personnelDTO.setNom("Nom du personnel");
        when(personnelleService.save(personnelDTO)).thenReturn(personnelDTO);
        ResponseEntity<PersonnelDTO> responseEntity = personnelleController.add(personnelDTO);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(personnelDTO, responseEntity.getBody());
        verify(personnelleService, times(1)).save(personnelDTO);
    }

    @Test
    void testChargerCSV() throws IOException {

        MockMultipartFile fichierCSV = new MockMultipartFile("fichier", "test.csv", MediaType.TEXT_PLAIN_VALUE, "Contenu du fichier".getBytes());
        String result = personnelleController.chargerCSV(fichierCSV);
        verify(personnelleService, times(1)).chargerDonneesCSV(fichierCSV);
    }

    @Test
    void testDeletePersonnel() {

        Long personnelId = 1L;
        personnelleController.delete(personnelId);
        verify(personnelleService, times(1)).delete(personnelId);
    }

    @Test
    void testRecherchPersonnel() {
        Long personnelId = 1L;
        PersonnelDTO personnelDTO = new PersonnelDTO();
        personnelDTO.setId(personnelId);
        personnelDTO.setNom("Nom du personnel");

        when(personnelleService.recherch(personnelId)).thenReturn(personnelDTO);
        PersonnelDTO result = personnelleController.recherch(personnelId);
        assertEquals(personnelDTO, result);
        verify(personnelleService, times(1)).recherch(personnelId);
    }
}
