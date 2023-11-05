package tn.esprit.tunisair;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.tunisair.dto.SalleDTO;
import tn.esprit.tunisair.repository.SalleRepository;
import tn.esprit.tunisair.service.SalleServiceImpl;
import tn.esprit.tunisair.entity.Salle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SalleServiceImplIntegrationTest {

    @Autowired
    private SalleServiceImpl salleService;

    @Autowired
    private SalleRepository salleRepository;

    @Test
    public void testAjouterSalle() {

        Salle salle = new Salle();
        salle.setNomsalle("Salle de réunion");
        salle.setNombreplace(50L);
        salleService.ajoutersalle(salle);
        Salle salleAjoutee = salleRepository.findById(salle.getId()).orElse(null);
        assertNotNull(salleAjoutee);
        assertEquals("Salle de réunion", salleAjoutee.getNomsalle());
        assertEquals(50, salleAjoutee.getNombreplace());
    }

    @Test
    public void testRechercheSalle() {

        Salle salle = new Salle();
        salle.setId(2L);
        salle.setNomsalle("mestir");
        salle.setNombreplace(200L);
        salleRepository.findById(2L);


        SalleDTO salleTrouvee = salleService.recherch(salle.getId());
        assertNotNull(salleTrouvee);
        assertEquals("mestir", salleTrouvee.getNomsalle());
        assertEquals(200, salleTrouvee.getNombreplace());
    }






    @Test
    public void testFindAllSalle() {

        List<SalleDTO> salles = salleService.findAllSalle();
        Assertions.assertFalse(salles.isEmpty());
        int expectedSize = salleRepository.findAll().size();
        Assertions.assertEquals(expectedSize, salles.size());


    }

}