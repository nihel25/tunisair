package tn.esprit.tunisair;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.tunisair.DTO.SalleDTO;
import tn.esprit.tunisair.Repository.SalleRepository;
import tn.esprit.tunisair.Service.SalleServiceImpl;
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
        // Créez une instance de Salle pour le test
        Salle salle = new Salle();
        salle.setNomsalle("Salle de réunion");
        salle.setNombreplace(50L);

        // Appelez la méthode d'ajout de salle
        salleService.ajoutersalle(salle);

        // Vérifiez si la salle a été ajoutée avec succès en interrogeant la base de données
        Salle salleAjoutee = salleRepository.findById(salle.getId()).orElse(null);
        assertNotNull(salleAjoutee);
        assertEquals("Salle de réunion", salleAjoutee.getNomsalle());
        assertEquals(50, salleAjoutee.getNombreplace());
    }

    @Test
    public void testRechercheSalle() {
        // Créez une salle de test et ajoutez-la à la base de données
        Salle salle = new Salle();
        salle.setId(2L);
        salle.setNomsalle("mestir");
        salle.setNombreplace(200L);
        salleRepository.findById(2L);

        // Appelez la méthode de recherche de salle
        SalleDTO salleTrouvee = salleService.recherch(salle.getId());

        // Vérifiez si la salle a été correctement trouvée
        assertNotNull(salleTrouvee);
        assertEquals("mestir", salleTrouvee.getNomsalle());
        assertEquals(200, salleTrouvee.getNombreplace());
    }






    @Test
    public void testFindAllSalle() {
        // Créez quelques salles de test et ajoutez-les à la base de données


        List<SalleDTO> salles = salleService.findAllSalle();
        Assertions.assertFalse(salles.isEmpty());

        // Assurez-vous que le nombre d'éléments dans la liste correspond au nombre d'enregistrements dans la base de données
        int expectedSize = salleRepository.findAll().size();
        Assertions.assertEquals(expectedSize, salles.size());


    }

}

