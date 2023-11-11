package tn.esprit.tunisair;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tunisair.entity.Stage;
import tn.esprit.tunisair.repository.StageRepository;
import tn.esprit.tunisair.service.StageServiceIpm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StageServiceImplTest {



    @Mock
    private StageRepository stageRepository;

    @InjectMocks
    private StageServiceIpm stageServiceIpm;

    @Test
    @Order(1)
    void testAddstage() {
        // Create a sample subscription
        Stage piste = new Stage();
        // Mock the behavior of the repository method
        when(stageRepository.save(piste)).thenReturn(piste);

        // Invoke the method and verify the result
        Stage addedPiste = stageServiceIpm.addsatge(piste);
        assertEquals(piste, addedPiste);

        // Verify that save method of PisteRepository was called once


    }



    @Test
    @Order(3)
    void testDeletePiste() {
        // ID de la piste à supprimer
        Long id = 2L;

        // Appelez la méthode de suppression dans le service
        stageServiceIpm.deletestage(id);

        // Vérifiez que la méthode deleteById du repository a été appelée avec le bon ID
        verify(stageRepository, times(1)).deleteById(id);
    }
}

