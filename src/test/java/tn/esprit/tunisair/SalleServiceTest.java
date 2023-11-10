package tn.esprit.tunisair;


import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tunisair.dto.SalleDTO;
import tn.esprit.tunisair.entity.Salle;
import tn.esprit.tunisair.repository.SalleRepository;
import tn.esprit.tunisair.service.SalleServiceImpl;

@SpringBootTest
public class SalleServiceTest {


    @InjectMocks

    private SalleServiceImpl salleService;

    @Mock
    private SalleRepository salleRepository;

    @Test
    public void testsave() {

        Salle salle = new Salle();
        salle.setNomsalle("Salle de réunion");
        salle.setNombreplace(50L);
        salleService.ajoutersalle(salle);
        Salle salleAjoutee = salleRepository.findById(salle.getId()).orElse(null);
//        assertNotNull(salleAjoutee);
//        assertEquals("Salle de réunion", salleAjoutee.getNomsalle());
//        assertEquals(50, salleAjoutee.getNombreplace());
    }

    @Test
    public void testrecherch() {

        Salle salle = new Salle();
        salle.setId(2L);
        salle.setNomsalle("mestir");
        salle.setNombreplace(200L);
        salleRepository.findById(2L);


        SalleDTO salleTrouvee = salleService.recherch(salle.getId());
      //  assertNotNull(salleTrouvee);
       // assertEquals("mestir", salleTrouvee.getNomsalle());
        //assertEquals(200, salleTrouvee.getNombreplace());
    }
}
