package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.dto.FormateurDto;
import tn.esprit.tunisair.dto.FormationDTO;
import tn.esprit.tunisair.dto.SpecialiteeDTO;
import tn.esprit.tunisair.dto.UserprofilDTO;
import tn.esprit.tunisair.entity.Formateur;
import tn.esprit.tunisair.entity.Formation;
import tn.esprit.tunisair.entity.UserProfile;
import tn.esprit.tunisair.repository.FormationRepository;
import tn.esprit.tunisair.repository.UserRepository;
import tn.esprit.tunisair.service.FormationServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class FormationServiceImplTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FormationRepository formationRepository;




    @InjectMocks
    private FormationServiceImpl formationService;

    @Test
    void testSaveFormation() {

        FormationDTO formationDTO = new FormationDTO();

        UserprofilDTO userprofilDTO = new UserprofilDTO();

        SpecialiteeDTO specialiteeDTO = new SpecialiteeDTO();
        FormateurDto formateurDto = new FormateurDto();
        formateurDto.setSpecialiteedto(specialiteeDTO);
        formationDTO.setFormateurDto(formateurDto);
        formationDTO.setUserprofildto(userprofilDTO);

        Formation formation = FormationDTO.toEntity(formationDTO);


        doReturn(formation).when(formationRepository).save(Mockito.any(Formation.class));

        FormationDTO savedFormationDTO = formationService.save(formationDTO);
        assertEquals(formationDTO.getPrix(), savedFormationDTO.getPrix());
        verify(formationRepository, times(1)).save(Mockito.any(Formation.class));
    }

    @Test
    void testFindById() {

        Long formationId = 1L;
        UserprofilDTO userprofilDTO = new UserprofilDTO();
        SpecialiteeDTO specialiteeDTO = new SpecialiteeDTO();
        FormateurDto formateurDto = new FormateurDto();
        formateurDto.setSpecialiteedto(specialiteeDTO);
        FormationDTO formationDTO = new FormationDTO();
        formationDTO.setFormateurDto(formateurDto);
        formationDTO.setUserprofildto(userprofilDTO);
        Formation formation = FormationDTO.toEntity(formationDTO);
        Optional<Formation> optionalFormation = Optional.of(formation);
        when(formationRepository.findById(formationId)).thenReturn(optionalFormation);
        ResponseEntity<Formation> foundFormationResponse = formationService.findbyId(formationId);
        assertEquals(200, foundFormationResponse.getStatusCodeValue());
        verify(formationRepository, times(1)).findById(formationId);
    }
    @Test
    void testDelete() {

        Long id = 1L;
        formationService.delete(id);
        verify(formationRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindAllFormation() {

        Formation formation1 = new Formation();
        Formation formation2 = new Formation();
        formation1.setFormateur(new Formateur());
        formation1.setUserProfile(new UserProfile());
        formation2.setFormateur(new Formateur());
        formation2.setUserProfile(new UserProfile());
        List<Formation> stagiaireList = new ArrayList<>();
        stagiaireList.add(formation1);
        stagiaireList.add(formation2);
        when(formationRepository.findAll()).thenReturn(stagiaireList);
        List<FormationDTO> foundStagiaireDTOs = formationService.findAllFormation();
        assertEquals(stagiaireList.size(), foundStagiaireDTOs.size());
    }






}
