package tn.esprit.tunisair;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.tunisair.dto.FormateurDto;
import tn.esprit.tunisair.dto.FormationDTO;
import tn.esprit.tunisair.dto.SpecialiteeDTO;
import tn.esprit.tunisair.dto.UserprofilDTO;
import tn.esprit.tunisair.entity.Formateur;
import tn.esprit.tunisair.entity.Formation;
import tn.esprit.tunisair.entity.Specialitee;
import tn.esprit.tunisair.entity.UserProfile;
import tn.esprit.tunisair.repository.FormationRepository;
import tn.esprit.tunisair.service.FormationServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FormationTest {
@Mock
FormationRepository formationRepository;



@InjectMocks
private FormationServiceImpl formationService;
   // @Autowired
   @Before
   public void setUp() {
       MockitoAnnotations.initMocks(this);
   }

    @Test
    @Order(0)
    public void testSave() {

        FormationDTO formationDTO = new FormationDTO();
        formationDTO.setPrix(3000);

        formationDTO.setRemise(3900);
        formationDTO.setRef("FFE");

        FormateurDto formateurDto = new FormateurDto();
        SpecialiteeDTO specialiteeDTO = new SpecialiteeDTO();
        specialiteeDTO.setTypes("word");
        formateurDto.setSpecialiteedto(specialiteeDTO);

        formationDTO.setFormateurDto(formateurDto);
        UserprofilDTO userprofilDTOs =  new UserprofilDTO();

        formationDTO.setUserprofildto(userprofilDTOs);


        when(formationRepository.save(any(Formation.class))).thenAnswer(invocation -> {
            Formation savedStage = invocation.getArgument(0);
            savedStage.setId(2L);
            return savedStage;
        });
        FormationDTO savedStageDTO = formationService.save(formationDTO);
        verify(formationRepository, times(1)).save(any(Formation.class));
        assertNotNull(savedStageDTO);
        assertEquals(formationDTO.getPrix(), savedStageDTO.getPrix());
        assertEquals(formationDTO.getRef(), savedStageDTO.getRef());
        assertEquals(formationDTO.getRemise(), savedStageDTO.getRemise());
        assertNotNull(savedStageDTO.getId());
    }













    @Test
    @Order(1)
    public void testRecherch() {


        Formation formation = new Formation();
        formation.setId(9L);
        formation.setFormationtype("Word");
        Formateur formateur = new Formateur();
        formateur.setSpecialitee(new Specialitee());

        formation.setFormateur(formateur);
        UserProfile userProfile = new UserProfile();

        formation.setUserProfile(userProfile);
        when(formationRepository.findById(9L)).thenReturn(Optional.of(formation));
        FormationDTO foundStageDTO = formationService.recherch(9L);

        assertEquals(formation.getId(), foundStageDTO.getId());
        assertEquals(formation.getFormationtype(), foundStageDTO.getFormationtype());

        // Vérifier l'encadreurDTO
        assertNotNull(foundStageDTO.getFormateurDto());


    }
























}
