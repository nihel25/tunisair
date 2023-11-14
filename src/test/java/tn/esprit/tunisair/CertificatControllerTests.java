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
        CertificatDTO certificatDTO = new CertificatDTO();
        certificatDTO.setId(1L);

        when(certificatService.save(certificatDTO)).thenReturn(certificatDTO);

        ResponseEntity<CertificatDTO> responseEntity = certificatController.save(certificatDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(certificatDTO, responseEntity.getBody());
        verify(certificatService, times(1)).save(certificatDTO);
    }

    @Test
    void testRecherchCertificat() {
        Long certificatId = 1L;
        CertificatDTO certificatDTO = new CertificatDTO();
        certificatDTO.setId(certificatId);
        certificatDTO.setId(1L);

        when(certificatService.recherch(certificatId)).thenReturn(certificatDTO);

        CertificatDTO result = certificatController.recherch(certificatId);

        assertEquals(certificatDTO, result);
        verify(certificatService, times(1)).recherch(certificatId);
    }

    @Test
    void testFindAllCertificat() {
        List<CertificatDTO> certificatDTOList = new ArrayList<>();

        when(certificatService.findAll()).thenReturn(certificatDTOList);

        List<CertificatDTO> result = certificatController.liste();

        assertEquals(certificatDTOList, result);
        verify(certificatService, times(1)).findAll();
    }

    @Test
    void testDeleteCertificat() {
        Long certificatId = 1L;

        certificatController.delete(certificatId);

        verify(certificatService, times(1)).delete(certificatId);
    }
}
