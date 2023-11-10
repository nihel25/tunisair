package tn.esprit.tunisair;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tunisair.entity.Salle;
import tn.esprit.tunisair.repository.SalleRepository;
import tn.esprit.tunisair.service.SalleServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SalleServiceImplTest {

    @Autowired
    private SalleRepository salleRepository;

    @InjectMocks
    private SalleServiceImpl salleService;




    @Test
    @Order(1)
    void testSaveSalle() {


            // Create a sample subscription
            Salle salle = new Salle();
            // Mock the behavior of the repository method
            when(salleRepository.save(salle)).thenReturn(salle);

            // Invoke the method and verify the result
        Salle addedPiste = salleService.save(salle);
            assertEquals(salle, addedPiste);

            // Verify that save method of PisteRepository was called once
            verify(salleRepository, times(1)).save(salle);
        }
    }














