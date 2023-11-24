package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.DemandeformationController;
import tn.esprit.tunisair.dto.DemandeFormationDTO;
import tn.esprit.tunisair.entity.Personnel;
import tn.esprit.tunisair.service.DemandeformationServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class DemandeformationControllerTests {

    @Mock
    private DemandeformationServiceImpl demandeFormationService;

    @InjectMocks
    private DemandeformationController demandeformationController;


    @Test
    void testGetAllPersonnel() {

        List<Personnel> personnelList = new ArrayList<>();
        when(demandeFormationService.getAllPersonnel()).thenReturn(personnelList);
        ResponseEntity<List<Personnel>> result = demandeformationController.getAllPersonnel();
        assertEquals(personnelList, result.getBody());
        verify(demandeFormationService, times(1)).getAllPersonnel();
    }
    @Test
    void testListeDemande() {

        List<DemandeFormationDTO> demandeList = new ArrayList<>();
        when(demandeFormationService.findAllDemandes()).thenReturn(demandeList);
        List<DemandeFormationDTO> result = demandeformationController.getAllDemandeFormations();
        assertEquals(demandeList, result);

        verify(demandeFormationService, times(1)).findAllDemandes();
    }

    @Test
    void testRecherchDemande() {

        Long demandeId = 1L;
        DemandeFormationDTO demandeclientDTO = new DemandeFormationDTO();
        demandeclientDTO.setId(demandeId);
        when(demandeFormationService.recherch(demandeId)).thenReturn(demandeclientDTO);
        DemandeFormationDTO result = demandeformationController.recherch(demandeId);
        assertEquals(demandeclientDTO, result);
        verify(demandeFormationService, times(1)).recherch(demandeId);
    }

    @Test
    void testDeleteDemande() {

        Long demandeId = 1L;

        demandeformationController.delete(demandeId);

        verify(demandeFormationService, times(1)).delete(demandeId);
    }



}
