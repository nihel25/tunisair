package tn.esprit.tunisair;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.tunisair.entity.Salleformation;
import tn.esprit.tunisair.repository.SalleformationRepository;
import tn.esprit.tunisair.service.SalleformationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SalleformationServiceTest {


    @Mock
    SalleformationRepository salleformationRepository;

    @InjectMocks
    SalleformationService salleformationService;

    @Test
    @Order(0)
   public void testAddSalle() {
        // Create a sample subscription
        Salleformation piste = new Salleformation();
        // Mock the behavior of the repository method
        when(salleformationRepository.save(piste)).thenReturn(piste);

        // Invoke the method and verify the result
        Salleformation addedPiste = salleformationService.add(piste);
        assertEquals(piste, addedPiste);

        // Verify that save method of PisteRepository was called once
        verify(salleformationRepository, times(1)).save(piste);
    }
}
