package tn.esprit.tunisair;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.tunisair.DTO.PersonnelDTO;
import tn.esprit.tunisair.Repository.PersonnelRepository;
import tn.esprit.tunisair.Service.PersonnelleServiceImpl;
import tn.esprit.tunisair.entity.Personnel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonnelServiceIntegrationTest {


    @Autowired
    PersonnelRepository personnelRepository;
    @Autowired
    PersonnelleServiceImpl personnelleService;


    @Test
    public void testSauvegarderpersonnel() {
        // Créez un objet PersonnelDTO pour le test
        PersonnelDTO personnelDTO = new PersonnelDTO();
        personnelDTO.setNom("John");
        personnelDTO.setPrenom("Doe");
        personnelDTO.setEmail("testintegration@example.com");
        personnelDTO.setCin("14526398");
        personnelDTO.setFonction("rh");
        PersonnelDTO savedPersonnel = personnelleService.save(personnelDTO);
        Personnel personnel = personnelRepository.findById(savedPersonnel.getId()).orElse(null);
        assertNotNull(personnel);
        assertEquals("John", personnel.getNom());
        assertEquals("Doe", personnel.getPrenom());
        assertEquals("testintegration@example.com", personnel.getEmail());
        assertEquals("14526398", personnel.getCin());

    }


    @Test
    public void testRecherchepersonnel() {
//
        Personnel personnel = new Personnel();
        personnel.setId(1L);
        personnel.setNom("ayachi");
        personnel.setPrenom("asma");
        personnelRepository.findById(1L);

//
        PersonnelDTO personnelDTO = personnelleService.recherch(personnel.getId());

//
        Assertions.assertNotNull(personnelDTO);
        Assertions.assertEquals("ayachi", personnelDTO.getNom());
        Assertions.assertEquals("asma", personnelDTO.getPrenom());
    }
}

//
//
//
//
//
//    @Test
//    public void testFindFormateur() {
//
//
//        List<FormateurDto> formateurDtos = formateurService.findAll();
//        Assertions.assertFalse(formateurDtos.isEmpty());
//        int expectedSize = formateurRepository.findAll().size();
//        Assertions.assertEquals(expectedSize, formateurDtos.size());
//
//
//    }
//
//
//
//
//}
//