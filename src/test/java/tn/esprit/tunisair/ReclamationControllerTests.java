package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.ReclamationController;
import tn.esprit.tunisair.dto.ReclamationDTO;
import tn.esprit.tunisair.service.ReclamationService;
import tn.esprit.tunisair.service.ReclamationServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReclamationControllerTests {

    @Mock
    private ReclamationService reclamationService;

    @Mock
    private ReclamationServiceImpl reclamationServicee;

    @InjectMocks
    private ReclamationController reclamationController;

    @Test
    void testAjoutReclamation() {

        ReclamationDTO reclamationDTO = new ReclamationDTO();
        reclamationDTO.setTypeReclamation("Sujet de la réclation");

        when(reclamationService.save(reclamationDTO)).thenReturn(reclamationDTO);
        ResponseEntity<ReclamationDTO> responseEntity = reclamationController.ajout(reclamationDTO);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(reclamationDTO, responseEntity.getBody());
        verify(reclamationService, times(1)).save(reclamationDTO);
    }

    @Test
    void testRecherchReclamation() {

        Long reclamationId = 1L;

        ReclamationDTO reclamationDTO = new ReclamationDTO();
        reclamationDTO.setId(reclamationId);
        reclamationDTO.setTypeReclamation("Sujet de la réclamation");

        when(reclamationService.recherch(reclamationId)).thenReturn(reclamationDTO);


        ReclamationDTO result = reclamationController.recherch(reclamationId);


        assertEquals(reclamationDTO, result);


        verify(reclamationService, times(1)).recherch(reclamationId);
    }

    @Test
    void testFindAllReclamation() {

        List<ReclamationDTO> reclamationDTOList = new ArrayList<>();
        when(reclamationServicee.findAllreclamation()).thenReturn(reclamationDTOList);
        List<ReclamationDTO> result = reclamationController.findAllreclamation();
        assertEquals(reclamationDTOList, result);
        verify(reclamationServicee, times(1)).findAllreclamation();
    }

    @Test
    void testDeleteReclamation() {

        Long reclamationId = 1L;


        reclamationController.delete(reclamationId);

        verify(reclamationServicee, times(1)).delete(reclamationId);
    }
}
