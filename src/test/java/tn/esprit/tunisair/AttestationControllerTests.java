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
        AttestationDTO attestationDTO = new AttestationDTO();
        attestationDTO.setId(1L);

        when(attestationService.save(attestationDTO)).thenReturn(attestationDTO);

        ResponseEntity<AttestationDTO> responseEntity = attestationController.ajout(attestationDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(attestationDTO, responseEntity.getBody());
        verify(attestationService, times(1)).save(attestationDTO);
    }

    @Test
    void testRecherchAttestation() {
        Long attestationId = 1L;
        AttestationDTO attestationDTO = new AttestationDTO();
        attestationDTO.setId(attestationId);

        when(attestationService.recherch(attestationId)).thenReturn(attestationDTO);

        AttestationDTO result = attestationController.recherch(attestationId);

        assertEquals(attestationDTO, result);
        verify(attestationService, times(1)).recherch(attestationId);
    }

    @Test
    void testFindAllAttestation() {
        List<AttestationDTO> attestationDTOList = new ArrayList<>();

        when(attestationService.findAll()).thenReturn(attestationDTOList);

        List<AttestationDTO> result = attestationController.liste();

        assertEquals(attestationDTOList, result);
        verify(attestationService, times(1)).findAll();
    }

    @Test
    void testDeleteAttestation() {
        Long attestationId = 1L;

        attestationController.delete(attestationId);

        verify(attestationService, times(1)).delete(attestationId);
    }
}
