package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.FormationController;
import tn.esprit.tunisair.dto.FormationDTO;
import tn.esprit.tunisair.service.FormationService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class FormationControllerTests {

    @Mock
    private FormationService formationService;


    @InjectMocks
    private FormationController formationController;

    @Test
    void testAddFormation() {

        FormationDTO formationDTO = new FormationDTO();
        formationDTO.setRef("Nom de la formation");

        when(formationService.save(formationDTO)).thenReturn(formationDTO);
        ResponseEntity<FormationDTO> responseEntity = formationController.add(formationDTO);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(formationDTO, responseEntity.getBody());
        verify(formationService, times(1)).save(formationDTO);
    }

    @Test
    void testRecherchFormation() {

        Long formationId = 1L;
        FormationDTO formationDTO = new FormationDTO();
        formationDTO.setId(formationId);
        formationDTO.setRef("Nom de la formation");
        when(formationService.recherch(formationId)).thenReturn(formationDTO);
        FormationDTO result = formationController.recherch(formationId);
        assertEquals(formationDTO, result);
        verify(formationService, times(1)).recherch(formationId);
    }

    @Test
    void testDeleteFormation() {

        Long formationId = 1L;
        formationController.delete(formationId);
        verify(formationService, times(1)).delete(formationId);
    }

    @Test
    void testListeFormation() {

        List<FormationDTO> formationList = new ArrayList<>();
        when(formationService.findAllFormation()).thenReturn(formationList);
        List<FormationDTO> result = formationController.liste();
        assertEquals(formationList, result);
        verify(formationService, times(1)).findAllFormation();
    }


}

