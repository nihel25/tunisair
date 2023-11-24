package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.StagiaireController;
import tn.esprit.tunisair.dto.StagiaireDTO;
import tn.esprit.tunisair.service.StagiaireService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StagiaireControllerTests {

    @Mock
    private StagiaireService stagiaireService;

    @InjectMocks
    private StagiaireController stagiaireController;

    @Test
    void testAddStagiaire() {

        StagiaireDTO stagiaireDTO = new StagiaireDTO();
        stagiaireDTO.setNom("John");
        stagiaireDTO.setPrenom("Doe");
        when(stagiaireService.save(stagiaireDTO)).thenReturn(stagiaireDTO);
        ResponseEntity<StagiaireDTO> responseEntity = stagiaireController.addstagiaire(stagiaireDTO);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(stagiaireDTO, responseEntity.getBody());
        verify(stagiaireService, times(1)).save(stagiaireDTO);
    }

    @Test
    void testRecherchStagiaire() {

        Long stagiaireId = 1L;


        StagiaireDTO stagiaireDTO = new StagiaireDTO();
        stagiaireDTO.setId(stagiaireId);
        stagiaireDTO.setNom("John");
        stagiaireDTO.setPrenom("Doe");
        when(stagiaireService.recherch(stagiaireId)).thenReturn(stagiaireDTO);
        StagiaireDTO result = stagiaireController.recherch(stagiaireId);
        assertEquals(stagiaireDTO, result);
        verify(stagiaireService, times(1)).recherch(stagiaireId);
    }

    @Test
    void testDeleteStagiaire() {

        Long stagiaireId = 1L;

        stagiaireController.delete(stagiaireId);
        verify(stagiaireService, times(1)).delete(stagiaireId);
    }

    @Test
    void testListeStagiaire() {

        List<StagiaireDTO> stagiaireDTOList = new ArrayList<>();

        when(stagiaireService.findAll()).thenReturn(stagiaireDTOList);

        List<StagiaireDTO> result = stagiaireController.liste();

        assertEquals(stagiaireDTOList, result);


        verify(stagiaireService, times(1)).findAll();
    }
}
