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









}
