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
 class EncadreurControllerTests {

    @Mock
    private EncadreurService encadreurService;

    @InjectMocks
    private EncadreurController encadreurController;

    @Test
    void testAddEncadreur() {

        EncadreurDTO encadreurDTO = new EncadreurDTO();
        encadreurDTO.setNom("Test Encadreur");
        encadreurDTO.setPrenom("Encadreur");
        when(encadreurService.save(encadreurDTO)).thenReturn(encadreurDTO);
        ResponseEntity<EncadreurDTO> responseEntity = encadreurController.addencadreur(encadreurDTO);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(encadreurDTO, responseEntity.getBody());
        verify(encadreurService, times(1)).save(encadreurDTO);
    }

    @Test
    void testDeleteEncadreur() {

        encadreurController.delete(1L);
        verify(encadreurService, times(1)).delete(1L);
    }

    @Test
    void testListeEncadreur() {

        List<EncadreurDTO> encadreurDTOList = new ArrayList<>();

        when(encadreurService.findAll()).thenReturn(encadreurDTOList);
        List<EncadreurDTO> result = encadreurController.liste();
        assertEquals(encadreurDTOList, result);
        verify(encadreurService, times(1)).findAll();
    }

    @Test
    void testRecherchEncadreur() {

        Long encadreurId = 1L;

        EncadreurDTO encadreurDTO = new EncadreurDTO();
        encadreurDTO.setId(encadreurId);
        encadreurDTO.setNom("Test Encadreur");
        encadreurDTO.setPrenom("Encadreur");
        when(encadreurService.recherch(encadreurId)).thenReturn(encadreurDTO);
        EncadreurDTO result = encadreurController.recherch(encadreurId);
        assertEquals(encadreurDTO, result);
        verify(encadreurService, times(1)).recherch(encadreurId);
    }
}
