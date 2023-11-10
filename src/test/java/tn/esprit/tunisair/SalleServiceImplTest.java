package tn.esprit.tunisair;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tunisair.entity.Salle;
import tn.esprit.tunisair.repository.SalleRepository;
import tn.esprit.tunisair.service.SalleServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SalleServiceImplTest {

    @Mock
    private SalleRepository salleRepository;

    @InjectMocks
    private SalleServiceImpl salleService;




    @Test
    @Order(1)
    void testSaveSalle() {


            // Create a sample subscription
            Salle salle = new Salle(50L,"jerbe",20L);
            // Mock the behavior of the repository method
            when(salleRepository.save(salle)).thenReturn(salle);


        Salle addedPiste = salleService.save(salle);
            assertEquals(salle, addedPiste);


            verify(salleRepository, times(1)).save(salle);
        }
    }














