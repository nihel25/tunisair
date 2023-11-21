package tn.esprit.tunisair;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.CertificatDTO;
import tn.esprit.tunisair.entity.Certificat;
import tn.esprit.tunisair.repository.CertificatRepository;
import tn.esprit.tunisair.service.CertificatServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CertificatServiceImplTests {

    @Mock
    private CertificatRepository certificatRepository;

    @InjectMocks
    private CertificatServiceImpl certificatService;

    @Test
    void testSaveCertificat() {

        CertificatDTO certificatDTO = new CertificatDTO();
        Certificat certificat = CertificatDTO.toentity(certificatDTO);
        doReturn(certificat).when(certificatRepository).save(Mockito.any(Certificat.class));
        CertificatDTO savedCertificatDTO = certificatService.save(certificatDTO);
        verify(certificatRepository, times(1)).save(Mockito.any(Certificat.class));
    }

    @Test
    void testRecherchCertificat() {
        // ID du certificat à rechercher
        Long certificatId = 1L;
        CertificatDTO certificatDTO = new CertificatDTO();
        certificatDTO.setId(certificatId);
        Certificat certificat = CertificatDTO.toentity(certificatDTO);
        Optional<Certificat> optionalCertificat = Optional.of(certificat);
        when(certificatRepository.findById(certificatId)).thenReturn(optionalCertificat);
        CertificatDTO foundCertificatDTO = certificatService.recherch(certificatId);
        verify(certificatRepository, times(1)).findById(certificatId);
    }

    @Test
    void testDeleteCertificat() {

        Long certificatId = 1L;
        certificatService.delete(certificatId);
        verify(certificatRepository, times(1)).deleteById(certificatId);
    }

    @Test
    void testFindAllCertificat() {

        Certificat certificat1 = new Certificat();
        Certificat certificat2 = new Certificat();
        List<Certificat> certificatList = new ArrayList<>();
        certificatList.add(certificat1);
        certificatList.add(certificat2);
        when(certificatRepository.findAll()).thenReturn(certificatList);
        List<CertificatDTO> foundCertificatDTOs = certificatService.findAll();
        assertEquals(certificatList.size(), foundCertificatDTOs.size());
    }
}
