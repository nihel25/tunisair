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
 class SalleControllerTests {

    @Mock
    private SalleService salleService;

    @InjectMocks
    private SalleController salleController;

    @Test
    void testDeleteSalle() {

        salleController.delete(1L);


        verify(salleService, times(1)).delete(1L);
    }

    @Test
    void testListeSalle() {

        List<SalleDTO> salleDTOList = new ArrayList<>();


        when(salleService.findAllSalle()).thenReturn(salleDTOList);


        List<SalleDTO> result = salleController.liste();

        assertEquals(salleDTOList, result);
        verify(salleService, times(1)).findAllSalle();
    }

    @Test
    void testAddSalle() {

        SalleDTO salleDTO = new SalleDTO();
        salleDTO.setNomsalle("Test Salle");
        salleDTO.setNombreplace(100L);
        when(salleService.save(salleDTO)).thenReturn(salleDTO);
        ResponseEntity<SalleDTO> responseEntity = salleController.addsalle(salleDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        assertEquals(salleDTO, responseEntity.getBody());
        verify(salleService, times(1)).save(salleDTO);
    }

    @Test
    void testRecherchSalle() {

        Long salleId = 1L;
        SalleDTO salleDTO = new SalleDTO();
        salleDTO.setId(salleId);
        salleDTO.setNomsalle("Test Salle");
        salleDTO.setNombreplace(100L);
        when(salleService.recherch(salleId)).thenReturn(salleDTO);
        SalleDTO result = salleController.recherch(salleId);
        assertEquals(salleDTO, result);
        verify(salleService, times(1)).recherch(salleId);
    }
}

