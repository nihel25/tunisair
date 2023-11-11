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
import static org.mockito.Mockito.when;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SalleServiceImplTest {

    @Mock
    private SalleRepository salleRepository;

    @InjectMocks
    private SalleServiceImpl salleService;

    @Test
    @Order(1)
    void testAddsalle() {
        // Create a sample subscription
        Salle piste = new Salle();
        // Mock the behavior of the repository method
        when(salleRepository.save(piste)).thenReturn(piste);

        // Invoke the method and verify the result
        Salle addedPiste = salleService.addsalle(piste);
        assertEquals(piste, addedPiste);

        // Verify that save method of PisteRepository was called once


    }
}
//    @Test
//    @Order(1)
//    void testSaveSalle() {

//        SalleDTO salleDTO = new SalleDTO(); // Initialisez avec des valeurs appropriées
//
//        salleDTO.setNomsalle("maria");
//        salleDTO.setNombreplace(300L);
//        Salle salle = SalleDTO.toentity(salleDTO);
//
//
//        when(salleRepository.save(salle)).thenReturn(salle);
//
//
//        SalleDTO savedSalleDTO = salleService.save(salleDTO);
//

//
//        assertEquals(salleDTO.getNomsalle(), savedSalleDTO.getNomsalle());
//        assertEquals(salleDTO.getNombreplace(), savedSalleDTO.getNombreplace());
//
//    }
//
//    @Test
//    @Order(2)
//    void testRecherch() {

//        Long salleId = 1L;
//        Salle salle = new Salle();
       // salle.setId(salleId);
//        salle.setNomsalle("jerba");
//        salle.setNombreplace(123L);
//        SalleDTO expectedSalleDTO = SalleDTO.fromEntity(salle);
//

//        when(salleRepository.findById(salleId)).thenReturn(Optional.of(salle));
//

//        SalleDTO foundSalleDTO = salleService.recherch(salleId);
//

//
       // assertEquals(expectedSalleDTO.getId(), foundSalleDTO.getId());
//        assertEquals(expectedSalleDTO.getNomsalle(), foundSalleDTO.getNomsalle());
//        assertEquals(expectedSalleDTO.getNombreplace(), foundSalleDTO.getNombreplace());
//    }
//
//    @Test
//    @Order(3)
//    void testDelete() {

//        Long salleId = 1L;
//

//        salleService.delete(salleId);
//

//        verify(salleRepository, times(1)).deleteById(salleId);
//    }
//
//    @Test
//    @Order(4)
//    void testFindAllSalle() {

//        Salle salle1 = new Salle();
//        Salle salle2 = new Salle();
//        List<Salle> salleList = new ArrayList<>();
//        salleList.add(salle1);
//        salleList.add(salle2);
//

//        when(salleRepository.findAll()).thenReturn(salleList);
//

//        List<SalleDTO> foundSalleDTOs = salleService.findAllSalle();
//

//        assertEquals(salleList.size(), foundSalleDTOs.size());

//    }
//}
//