package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.DemandeclientController;
import tn.esprit.tunisair.dto.DemandeclientDTO;
import tn.esprit.tunisair.service.DemandeformationclientService;
import tn.esprit.tunisair.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class DemandeclientControllerTests {

    @Mock
    private DemandeformationclientService demandeformationclientService;

    @Mock
    private UserService userService;

    @InjectMocks
    private DemandeclientController demandeclientController;

    @Test
    void testAddDemande() {

        DemandeclientDTO demandeclientDTO = new DemandeclientDTO();
        when(demandeformationclientService.add(demandeclientDTO)).thenReturn(demandeclientDTO);
        ResponseEntity<DemandeclientDTO> responseEntity = demandeclientController.adddemande(demandeclientDTO);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(demandeclientDTO, responseEntity.getBody());
        verify(demandeformationclientService, times(1)).add(demandeclientDTO);
    }

    @Test
    void testRecherchDemande() {

        Long demandeId = 1L;
        DemandeclientDTO demandeclientDTO = new DemandeclientDTO();
        demandeclientDTO.setId(demandeId);
        when(demandeformationclientService.recherch(demandeId)).thenReturn(demandeclientDTO);
        DemandeclientDTO result = demandeclientController.recherch(demandeId);
        assertEquals(demandeclientDTO, result);
        verify(demandeformationclientService, times(1)).recherch(demandeId);
    }

    @Test
    void testDeleteDemande() {
        Long demandeId = 1L;
        demandeclientController.delete(demandeId);
        verify(demandeformationclientService, times(1)).delete(demandeId);
    }

    @Test
    void testListeDemande() {

        List<DemandeclientDTO> demandeList = new ArrayList<>();
        when(demandeformationclientService.findAlldemandeformation()).thenReturn(demandeList);
        List<DemandeclientDTO> result = demandeclientController.liste();
        assertEquals(demandeList, result);
        verify(demandeformationclientService, times(1)).findAlldemandeformation();
    }
}
