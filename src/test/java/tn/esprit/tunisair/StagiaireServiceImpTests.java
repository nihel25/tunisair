package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.StageDTO;
import tn.esprit.tunisair.dto.StagiaireDTO;
import tn.esprit.tunisair.entity.Stage;
import tn.esprit.tunisair.entity.Stagiaire;
import tn.esprit.tunisair.repository.StageRepository;
import tn.esprit.tunisair.repository.StagiaireRepository;
import tn.esprit.tunisair.service.StagiaireServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class StagiaireServiceImpTests {

    @Mock
    private StagiaireRepository stagiaireRepository;

    @Mock
    private StageRepository stageRepository;

    @InjectMocks
    private StagiaireServiceImp stagiaireService;

    @Test
    void testSaveStagaire() {

        StageDTO stageDTO = new StageDTO();

        StagiaireDTO stagiaireDTO = new StagiaireDTO();
        stagiaireDTO.setNom("John");
        stagiaireDTO.setPrenom("Doe");
        stagiaireDTO.setStagedto(stageDTO);


        Stagiaire stagiaire = StagiaireDTO.toEntity(stagiaireDTO);


        doReturn(stagiaire).when(stagiaireRepository).save(Mockito.any(Stagiaire.class));


        StagiaireDTO savedStagiaireDTO = stagiaireService.save(stagiaireDTO);


        assertEquals(stagiaireDTO.getNom(), savedStagiaireDTO.getNom());
        assertEquals(stagiaireDTO.getPrenom(), savedStagiaireDTO.getPrenom());


        verify(stagiaireRepository, times(1)).save(Mockito.any(Stagiaire.class));
    }




    @Test
    void testRecherchStagiaire() {

        Long stagiaireId = 8L;


        StageDTO stageDTO = new StageDTO();

        StagiaireDTO stagiaireDTO = new StagiaireDTO();
        stagiaireDTO.setId(stagiaireId);
        stagiaireDTO.setNom("riahi");
        stagiaireDTO.setPrenom("ahlem");
        stagiaireDTO.setEmail("ahlem@gmail.com");
        stagiaireDTO.setStagedto(stageDTO);  // Associez le StageDTO au StagiaireDTO


        Stagiaire stagiaire = StagiaireDTO.toEntity(stagiaireDTO);


        Optional<Stagiaire> optionalStagiaire = Optional.of(stagiaire);


        when(stagiaireRepository.findById(stagiaireId)).thenReturn(optionalStagiaire);


        StagiaireDTO foundStagiaireDTO = stagiaireService.recherch(stagiaireId);


        assertEquals(stagiaire.getId(), foundStagiaireDTO.getId());
        assertEquals(stagiaire.getNom(), foundStagiaireDTO.getNom());
        assertEquals(stagiaire.getPrenom(), foundStagiaireDTO.getPrenom());




        verify(stagiaireRepository, times(1)).findById(stagiaireId);
    }



    @Test
    void testDeleteStagiaire() {

        Long stagiaireId = 1L;


        stagiaireService.delete(stagiaireId);


        verify(stagiaireRepository, times(1)).deleteById(stagiaireId);
    }

    @Test
    void testFindAllStagiaire() {

        Stagiaire stagiaire1 = new Stagiaire();
        Stagiaire stagiaire2 = new Stagiaire();
        stagiaire1.setStage(new Stage());
        stagiaire2.setStage(new Stage());
        List<Stagiaire> stagiaireList = new ArrayList<>();
        stagiaireList.add(stagiaire1);
        stagiaireList.add(stagiaire2);
        when(stagiaireRepository.findAll()).thenReturn(stagiaireList);
        List<StagiaireDTO> foundStagiaireDTOs = stagiaireService.findAll();
        assertEquals(stagiaireList.size(), foundStagiaireDTOs.size());
    }

}
