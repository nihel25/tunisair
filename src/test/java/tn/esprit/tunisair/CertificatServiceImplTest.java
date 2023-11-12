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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CertificatServiceImplTest {

    @Mock
    private CertificatRepository certificatRepository;

    @InjectMocks
    private CertificatServiceImpl certificatService;

    @Test
    void testRecherch() {
        // ID du certificat à rechercher
        Long id = 1L;

        // Créer un certificat simulé
        Certificat certificatEntity = new Certificat();
        certificatEntity.setId(id);
        // Simuler la recherche dans le repository
        when(certificatRepository.findById(id)).thenReturn(Optional.of(certificatEntity));

        // Appeler la méthode de service pour rechercher le certificat
        CertificatDTO certificatDTO = certificatService.recherch(id);

        // Vérifier si les données sont correctes
        assertEquals(certificatEntity.getId(), certificatDTO.getId());
        // Continuez avec d'autres assertions selon les propriétés de votre entité Certificat
    }

    @Test
    void testSaveCertificat() {
        // Créer un CertificatDTO simulé
        CertificatDTO certificatDTO = new CertificatDTO();
        // Ajoutez des propriétés au certificatDTO selon votre besoin

        // Créer un Certificat simulé (entité)
        Certificat certificatEntity = CertificatDTO.toentity(certificatDTO);

        // Simuler la sauvegarde dans le repository
        when(certificatRepository.save(Mockito.any(Certificat.class))).thenReturn(certificatEntity);

        // Appeler la méthode du service pour sauvegarder le certificat
        CertificatDTO savedCertificatDTO = certificatService.save(certificatDTO);

        // Vérifier si les données sont correctes
        // Ajoutez des assertions selon les propriétés de votre entité Certificat
        // Assurez-vous également de vérifier si la méthode save du repository a été appelée
        verify(certificatRepository, times(1)).save(Mockito.any(Certificat.class));
    }

    @Test
    void testDeleteCertificat() {
        // ID du certificat à supprimer
        Long id = 1L;

        // Appeler la méthode delete du service
        certificatService.delete(id);

        // Vérifier si la méthode deleteById du repository a été appelée
        verify(certificatRepository, times(1)).deleteById(id);
    }




}
