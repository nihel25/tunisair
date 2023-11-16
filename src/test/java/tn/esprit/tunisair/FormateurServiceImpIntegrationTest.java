package tn.esprit.tunisair;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.tunisair.dto.FormateurDto;
import tn.esprit.tunisair.entity.Formateur;
import tn.esprit.tunisair.repository.FormateurRepository;
import tn.esprit.tunisair.service.FormateurService;

import java.util.List;
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FormateurServiceImpIntegrationTest {




    @Autowired
    private FormateurRepository formateurRepository;

    @Autowired
    FormateurService formateurService;

   










    @Test
    public void testRechercheFormateur() {
//
        Formateur salle = new Formateur();
        salle.setId(2L);
        salle.setNom("riahi");
        salle.setPrenom("amin");
        formateurRepository.findById(2L);

//
        FormateurDto salleTrouvee = formateurService.recherch(salle.getId());

//
        Assertions.assertNotNull(salleTrouvee);
        Assertions.assertEquals("riahi", salleTrouvee.getNom());
        Assertions.assertEquals("amin", salleTrouvee.getPrenom());
    }


//




    @Test
    public void testFindFormateur() {


        List<FormateurDto> formateurDtos = formateurService.findAll();
        Assertions.assertFalse(formateurDtos.isEmpty());
        int expectedSize = formateurRepository.findAll().size();
        Assertions.assertEquals(expectedSize, formateurDtos.size());


    }





}