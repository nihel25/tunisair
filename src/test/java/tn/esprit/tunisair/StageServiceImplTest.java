package tn.esprit.tunisair;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tunisair.repository.StageRepository;
import tn.esprit.tunisair.service.StageServiceIpm;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StageServiceImplTest {



    @Mock
    private StageRepository stageRepository;

    @InjectMocks
    private StageServiceIpm stageServiceIpm;

//    @Test
//    @Order(1)
//    void testAddstage() {
//
//        Stage piste = new Stage();
//
//        when(stageRepository.save(piste)).thenReturn(piste);
//
//
//        Stage addedPiste = stageServiceIpm.addsatge(piste);
//        assertEquals(piste, addedPiste);
//
//
//    }



    @Test
    @Order(3)
    void testDeletePiste() {
        // ID de la piste à supprimer
        Long id = 2L;

        // Appelez la méthode de suppression dans le service
        stageServiceIpm.delete(id);

        // Vérifiez que la méthode deleteById du repository a été appelée avec le bon ID
        verify(stageRepository, times(1)).deleteById(id);
    }
}

