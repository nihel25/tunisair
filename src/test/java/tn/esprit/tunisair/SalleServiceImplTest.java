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
    void testSaveSalle() {

        SalleDTO salleDTO = new SalleDTO();
        Salle salle = SalleDTO.toentity(salleDTO);
        when(salleRepository.save(salle)).thenReturn(salle);
        SalleDTO addedPiste = salleService.save(salleDTO);
       //assertEquals(salle, addedPiste);
       // verify(salleRepository, times(1)).save(salle);
    }














}
