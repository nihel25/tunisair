package tn.esprit.tunisair;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.tunisair.DTO.FormateurDto;
import tn.esprit.tunisair.DTO.SpecialiteeDTO;
import tn.esprit.tunisair.Repository.FormateurRepository;
import tn.esprit.tunisair.Service.FormateurService;
import tn.esprit.tunisair.entity.Formateur;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FormateurServiceImpIntegrationTest {




    @Autowired
    private FormateurRepository formateurRepository;

    @Autowired
    FormateurService formateurService;

    @Test
    public void testSauvegarderFormateur() {
        // Créez un objet FormateurDto pour le test
        FormateurDto formateurDto = new FormateurDto();
        SpecialiteeDTO specialiteeDTO =new SpecialiteeDTO();
        specialiteeDTO.setId(14L);
        formateurDto.setSpecialiteedto(specialiteeDTO);
        formateurDto.setNom("John");
        formateurDto.setPrenom("Doe");
        formateurDto.setEmail("johndoe@example.com");
        formateurDto.setdisponible(true);
        formateurDto.setDebut(new Date());
        formateurDto.setFin(new Date());
        FormateurDto savedFormateur = formateurService.save(formateurDto);
        Formateur formateur = formateurRepository.findById(savedFormateur.getId()).orElse(null);
        assertNotNull(formateur);
        assertEquals("John", formateur.getNom());
        assertEquals("Doe", formateur.getPrenom());
        assertEquals("johndoe@example.com", formateur.getEmail());
        assertEquals(true,formateur.getDisponible()); // Vérifiez si le formateur est disponible
    }











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

































