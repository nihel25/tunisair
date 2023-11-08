package tn.esprit.tunisair;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tunisair.dto.CertificatDTO;
import tn.esprit.tunisair.dto.EncadreurDTO;
import tn.esprit.tunisair.dto.StageDTO;
import tn.esprit.tunisair.dto.StagiaireDTO;
import tn.esprit.tunisair.entity.Certificat;
import tn.esprit.tunisair.entity.Encadreur;
import tn.esprit.tunisair.entity.Stage;
import tn.esprit.tunisair.entity.Stagiaire;
import tn.esprit.tunisair.repository.CertificatRepository;
import tn.esprit.tunisair.service.CertificatServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class CertificatTest {



@Mock
    CertificatRepository certificatRepository;
@InjectMocks
    CertificatServiceImpl certificatService;



    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test

    public void testSave() {

        CertificatDTO certificatDTO = new CertificatDTO();
        certificatDTO.setId(1L);

        EncadreurDTO encadreurDTO = new EncadreurDTO();
        encadreurDTO.setId(1L);
        certificatDTO.setEncadreurDTO(encadreurDTO);

        StagiaireDTO stagiaireDTO1 = new StagiaireDTO();
        stagiaireDTO1.setId(8L);
        StageDTO stageDTO = new StageDTO();
        stageDTO.setId(7L);
        stageDTO.setEncadreurDTO(encadreurDTO);
        stagiaireDTO1.setStagedto(stageDTO);
        certificatDTO.setStagiairedto(stagiaireDTO1);
        when(certificatRepository.save(any(Certificat.class))).thenAnswer(invocation -> {
            Certificat savedStage = invocation.getArgument(0);
            savedStage.setId(1L);
            return savedStage;
        });
        CertificatDTO savedStageDTO = certificatService.save(certificatDTO);
        verify(certificatRepository, times(1)).save(any(Certificat.class));
        assertNotNull(savedStageDTO);
        assertEquals(certificatDTO.getId(), savedStageDTO.getId());

        assertNotNull(savedStageDTO.getId());
    }

    @Test

    public void testDelete() {
        Long certifid = 15L;
        doNothing().when(certificatRepository).deleteById(certifid);
        certificatService.delete(certifid);
        verify(certificatRepository, times(1)).deleteById(certifid);
    }



    @Test

    public void testRecherch() {

        Stage stage = new Stage();
        Certificat certificat = new Certificat();
        certificat.setId(2L);
        Stagiaire stagiaire = new Stagiaire();
        stagiaire.setStage(new Stage());
        stagiaire.setStage(stage);
        certificat.setStagiaire(stagiaire);
        Encadreur encadreur = new Encadreur();
        EncadreurDTO encadreurDTO = new EncadreurDTO();
        encadreurDTO.setId(1L);
        encadreurDTO.setNom("mabrouki");
        encadreurDTO.setEmail("akrem@gmail.com");
certificat.setEncadreur(encadreur);
        when(certificatRepository.findById(1L)).thenReturn(Optional.of(certificat));
        CertificatDTO certificatDTO = certificatService.recherch(2L);



    }













    @Test

    public void testFindAllCertificat() {
        Certificat certificat = new Certificat();
        certificat.setId(1L);
Stagiaire stagiaire= new Stagiaire();

        Encadreur encadreur = new Encadreur();

        Stage stage = new Stage();
        stagiaire.setStage(stage);
        stage.setId(7L);
        stage.setEncadreur(encadreur);
        certificat.setEncadreur(encadreur);
certificat.setStagiaire(stagiaire);
        when(certificatRepository.findAll()).thenReturn(Arrays.asList(certificat));
        List<CertificatDTO> stageDTOList = certificatService.findAll();
        assertEquals(1, stageDTOList.size());
        assertEquals(certificat.getId(), stageDTOList.get(0).getId());

    }
}
