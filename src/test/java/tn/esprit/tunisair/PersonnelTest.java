package tn.esprit.tunisair;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.tunisair.dto.PersonnelDTO;
import tn.esprit.tunisair.repository.PersonnelRepository;
import tn.esprit.tunisair.service.PersonnelleServiceImpl;
import tn.esprit.tunisair.entity.Personnel;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest

@RunWith(SpringRunner.class)
public class PersonnelTest {




    @InjectMocks
    private PersonnelleServiceImpl personnelleService;

    @Mock
    private PersonnelRepository personnelRepository;  // Injection du repository


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSave() {
        // Créer un objet StageDTO pour le test
        PersonnelDTO personnelDTO = new PersonnelDTO();
        personnelDTO.setNom("riahi");
        personnelDTO.setPrenom("molka");

        when(personnelRepository.save(any(Personnel.class))).thenAnswer(invocation -> {
            Personnel savedStage = invocation.getArgument(0);
            savedStage.setId(1L);  // Simule l'attribution d'un ID lors de l'enregistrement
            return savedStage;
        });

        PersonnelDTO savedStageDTO = personnelleService.save(personnelDTO);
        verify(personnelRepository, times(1)).save(any(Personnel.class));
        assertNotNull(savedStageDTO);
        assertEquals(personnelDTO.getNom(), savedStageDTO.getNom());
        assertEquals(personnelDTO.getPrenom(), savedStageDTO.getPrenom());
        assertNotNull(savedStageDTO.getId());
    }




    @Test
    public void testRecherch() {


        Personnel personnel = new Personnel();
        personnel.setId(20L);
        personnel.setNom("mabrouki");
        personnel.setPrenom("ons");


        when(personnelRepository.findById(20L)).thenReturn(Optional.of(personnel));
        PersonnelDTO foundStageDTO = personnelleService.recherch(20L);

        assertEquals(personnel.getId(), foundStageDTO.getId());
        assertEquals(personnel.getNom(), foundStageDTO.getNom());
        assertEquals(personnel.getPrenom(), foundStageDTO.getPrenom());




    }

















}
