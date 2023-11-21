package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.*;
import tn.esprit.tunisair.entity.Formation;
import tn.esprit.tunisair.entity.Reclamation;
import tn.esprit.tunisair.entity.UserProfile;
import tn.esprit.tunisair.repository.ReclamationRepository;
import tn.esprit.tunisair.service.ReclamationServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReclamationServiceTests {


@Mock
    ReclamationRepository repository;


@InjectMocks
    ReclamationServiceImpl reclamationService;




    @Test
    void testSaveReclamation() {

        SpecialiteeDTO specialitee = new SpecialiteeDTO();
        UserprofilDTO userprofilDTO = new UserprofilDTO();
        FormateurDto formateurDto = new FormateurDto();
        formateurDto.setSpecialiteedto(specialitee);
        FormationDTO formationDTO = new FormationDTO();
        formationDTO.setFormateurDto(formateurDto);
        formationDTO.setUserprofildto(userprofilDTO);
        ReclamationDTO reclamationDTO = new ReclamationDTO();
        reclamationDTO.setTypeReclamation("mauvaise formation");
        reclamationDTO.setFormationdto(formationDTO);
        Reclamation reclamation = ReclamationDTO.toentity(reclamationDTO);
        doReturn(reclamation).when(repository).save(Mockito.any(Reclamation.class));
        ReclamationDTO savedMaterielDTO = reclamationService.save(reclamationDTO);
        assertEquals(reclamationDTO.getTypeReclamation(), savedMaterielDTO.getTypeReclamation());
        verify(repository, times(1)).save(Mockito.any(Reclamation.class));
    }










    @Test
    void testDeleteReclamation() {
        Long idrec = 1L;
        reclamationService.delete(idrec);
        verify(repository, times(1)).deleteById(idrec);
    }









    @Test
    void testRecherch() {
        Long reclamationId = 1L;
        Reclamation reclamation = new Reclamation();
        reclamation.setId(reclamationId);
        Formation formation = new Formation();
        UserProfile userprofilDTO = new UserProfile();
        formation.setId(2L);
        formation.setUserProfile(userprofilDTO);
        reclamation.setFormation(formation);
        Optional<Reclamation> optionalReclamation = Optional.of(reclamation);
        when(repository.findById(reclamationId)).thenReturn(optionalReclamation);
        ReclamationDTO foundReclamationDTO = reclamationService.recherch(reclamationId);
        assertEquals(reclamation.getId(), foundReclamationDTO.getId());
        verify(repository, times(1)).findById(reclamationId);
    }



    @Test
    void testFindAllReclamation() {

        Formation formation1 = new Formation();
        formation1.setUserProfile(new UserProfile());
        formation1.setId(1L);
        Reclamation reclamation1 = new Reclamation();
        reclamation1.setId(1L);
        reclamation1.setTypeReclamation("Type 1");
        reclamation1.setDatereclamation(new Date());
        reclamation1.setFormation(formation1);
        Formation formation2 = new Formation();
        formation2.setId(2L);
        formation2.setUserProfile(new UserProfile());
        Reclamation reclamation2 = new Reclamation();
        reclamation2.setId(2L);
        reclamation2.setTypeReclamation("Type 2");
        reclamation2.setDatereclamation(new Date());
        reclamation2.setFormation(formation2);
        List<Reclamation> reclamationList = Arrays.asList(reclamation1, reclamation2);
        when(repository.findAll()).thenReturn(reclamationList);
        List<ReclamationDTO> foundReclamationDTOs = reclamationService.findAllreclamation();
        assertEquals(reclamationList.size(), foundReclamationDTOs.size());
    }




}
