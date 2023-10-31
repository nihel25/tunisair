package tn.esprit.tunisair;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.tunisair.dto.SalleDTO;
import tn.esprit.tunisair.repository.SalleRepository;
import tn.esprit.tunisair.service.SalleServiceImpl;
import tn.esprit.tunisair.entity.Salle;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class salleimpltest {


    @InjectMocks
    private SalleServiceImpl salleService;

    @Mock
    private SalleRepository salleRepository;  // Injection du repository


        @Before
        public void setUp() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void testSave() {
            // Créer un objet StageDTO pour le test
            SalleDTO salleDTO = new SalleDTO();
            salleDTO.setNombreplace(123L);
            salleDTO.setStatut(false);

            when(salleRepository.save(any(Salle.class))).thenAnswer(invocation -> {
                Salle savedStage = invocation.getArgument(0);
                savedStage.setId(1L);  // Simule l'attribution d'un ID lors de l'enregistrement
                return savedStage;
            });

            SalleDTO savedStageDTO = salleService.save(salleDTO);
            verify(salleRepository, times(1)).save(any(Salle.class));
            assertNotNull(savedStageDTO);
            assertEquals(salleDTO.getNombreplace(), savedStageDTO.getNombreplace());
            assertEquals(salleDTO.getStatut(), savedStageDTO.getStatut());
            assertNotNull(savedStageDTO.getId());
        }




    @Test
    public void testRecherch() {


        Salle salle = new Salle();
        salle.setId(1L);
        salle.setNombreplace(123L);
salle.setNomsalle("jerba");


        when(salleRepository.findById(1L)).thenReturn(Optional.of(salle));
        SalleDTO foundStageDTO = salleService.recherch(1L);

        assertEquals(salle.getId(), foundStageDTO.getId());
        assertEquals(salle.getNombreplace(), foundStageDTO.getNombreplace());




    }




    @Test
    public void testRecherchEchec() {
        Salle salle = new Salle();
        salle.setId(20L);

        // Configuration de Mockito pour renvoyer un Optional contenant l'entité lorsque l'ID existe.
        when(salleRepository.findById(20L)).thenReturn(Optional.of(salle));

        SalleDTO foundSalleDTO = salleService.recherch(20L);

        // Assertion pour vérifier que la recherche a échoué (l'ID 1L existe).
        assertNotNull(foundSalleDTO); // Cette assertion doit échouer, car foundSalleDTO ne doit pas être null.
    }











}
