package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.SalleDTO;
import tn.esprit.tunisair.entity.Salle;
import tn.esprit.tunisair.repository.SalleRepository;
import tn.esprit.tunisair.service.SalleServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class SalleServiceImplTests {

    @Mock
    private SalleRepository salleRepository;

    @InjectMocks
    private SalleServiceImpl salleService;

    @Test
    void testSaveSalle() {

        when(salleRepository.save(Mockito.any())).thenAnswer(invocation -> invocation.getArgument(0));

        SalleDTO salleDTO = new SalleDTO();
        salleDTO.setNomsalle("maria");
        salleDTO.setNombreplace(300L);
        SalleDTO savedSalleDTO = salleService.save(salleDTO);
        assertEquals(salleDTO.getNomsalle(), savedSalleDTO.getNomsalle());
        assertEquals(salleDTO.getNombreplace(), savedSalleDTO.getNombreplace());
        verify(salleRepository, times(1)).save(Mockito.any());
    }

    @Test
    void testRecherch() {

        Long salleId = 1L;


        Salle salle = new Salle();
        salle.setId(salleId);
        salle.setNomsalle("jerba");
        salle.setNombreplace(123L);
        Optional<Salle> optionalSalle = Optional.of(salle);
        when(salleRepository.findById(salleId)).thenReturn(optionalSalle);
        SalleDTO foundSalleDTO = salleService.recherch(salleId);
        assertEquals(salle.getId(), foundSalleDTO.getId());
        assertEquals(salle.getNomsalle(), foundSalleDTO.getNomsalle());
        assertEquals(salle.getNombreplace(), foundSalleDTO.getNombreplace());
        verify(salleRepository, times(1)).findById(salleId);
    }


    @Test
    void testDeleteSalle() {

        Long stageId = 1L;


        salleService.delete(stageId);


        verify(salleRepository, times(1)).deleteById(stageId);
    }


    @Test
    void testFindAllStage() {
        Salle stage1 = new Salle();
        Salle stage2 = new Salle();
        List<Salle> stageList = new ArrayList<>();
        stageList.add(stage1);
        stageList.add(stage2);
        when(salleRepository.findAll()).thenReturn(stageList);
        List<SalleDTO> foundStageDTOs = salleService.findAllSalle();
        assertEquals(stageList.size(), foundStageDTOs.size());
    }

}
