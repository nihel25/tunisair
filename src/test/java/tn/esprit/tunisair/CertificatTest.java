package tn.esprit.tunisair;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.tunisair.entity.Certificat;
import tn.esprit.tunisair.service.CertificatServiceImpl;
import tn.esprit.tunisair.dto.CertificatDTO;
import tn.esprit.tunisair.repository.CertificatRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class CertificatTest {

    @Mock
    private CertificatRepository certificatRepository;

    @InjectMocks
    private CertificatServiceImpl certificatService;

    private Certificat certificat;

    @Before
    public void setup() {
        certificat = new Certificat();
        certificat.setId(1L);

    }

    @Test
    public void testRechercheCertificatParId() {
        when(certificatRepository.findById(1L)).thenReturn(Optional.of(certificat));

        CertificatDTO certificatDTO = certificatService.recherch(1L);

        assertNotNull(certificatDTO);
        assertEquals(certificat.getId(), certificatDTO.getId());

    }

    @Test
    public void testEnregistrementCertificat() {
        CertificatDTO certificatDTO = new CertificatDTO();


        when(certificatRepository.save(any(Certificat.class))).thenReturn(certificat);

        CertificatDTO savedCertificatDTO = certificatService.save(certificatDTO);

        assertNotNull(savedCertificatDTO);
        assertEquals(certificat.getId(), savedCertificatDTO.getId());

    }

    @Test
    public void testSuppressionCertificat() {
        Long certificatId = 1L;
        doNothing().when(certificatRepository).deleteById(certificatId);

        certificatService.delete(certificatId);


        verify(certificatRepository).deleteById(certificatId);
    }

    @Test
    public void testListeCertificats() {
        Certificat certificat1 = new Certificat();
        certificat1.setId(1L);

        when(certificatRepository.findAll()).thenReturn(Arrays.asList(certificat, certificat1));

        List<CertificatDTO> certificatDTOList = certificatService.findAll();

        assertEquals(2, certificatDTOList.size());

    }
}



