package tn.esprit.tunisair;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tunisair.dto.SalleDTO;
import tn.esprit.tunisair.entity.Salle;
import tn.esprit.tunisair.repository.SalleRepository;
import tn.esprit.tunisair.service.SalleServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
 class SalleServiceImplTest {

    @Mock
    private SalleRepository salleRepository;

    @InjectMocks
    private SalleServiceImpl salleService;

    @Test
    @Order(1)
    void testSaveSalle() {
//
        SalleDTO salleDTO = new SalleDTO();
        Salle salle = SalleDTO.toentity(salleDTO);

//
        when(salleRepository.save(salle)).thenReturn(salle);

//
        SalleDTO savedSalle = salleService.save(salleDTO);
        assertEquals(salle, savedSalle);

//
            verify(salleRepository, times(1)).save(salle);
   }
   }
//}
//    @Test
//    @Order(1)
//    void testSaveSalle() {

//        SalleDTO salleDTO = new SalleDTO(50L, "jerba1", 200L);
//        Salle salle = SalleDTO.toentity(salleDTO);
//        when(salleRepository.save(salle)).thenReturn(salle);
//        SalleDTO savedSalleDTO = salleService.save(salleDTO);
//        assertEquals(salleDTO.getId(), savedSalleDTO.getId());
//        assertEquals(salleDTO.getNomsalle(), savedSalleDTO.getNomsalle());
//        assertEquals(salleDTO.getNombreplace(), savedSalleDTO.getNombreplace());
//        verify(salleRepository, times(1)).save(any(Salle.class));
//    }
//}