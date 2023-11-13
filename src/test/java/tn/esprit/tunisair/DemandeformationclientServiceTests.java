package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.DemandeclientDTO;
import tn.esprit.tunisair.dto.FormateurDto;
import tn.esprit.tunisair.dto.FormationDTO;
import tn.esprit.tunisair.dto.UserprofilDTO;
import tn.esprit.tunisair.entity.Demandeclient;
import tn.esprit.tunisair.entity.Formateur;
import tn.esprit.tunisair.entity.Formation;
import tn.esprit.tunisair.entity.UserProfile;
import tn.esprit.tunisair.repository.DemandeclientRepository;
import tn.esprit.tunisair.service.DemandeformationclientServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DemandeformationclientServiceTests {

    @Mock
    private DemandeclientRepository demandeclientRepository;

    @InjectMocks
    private DemandeformationclientServiceImpl demandeformationclientService;

    @Test
    void testAddDemandeclient() {
        FormationDTO formationDTO = new FormationDTO();
        UserprofilDTO userprofilDTO = new UserprofilDTO();
        FormateurDto formateurDto = new FormateurDto();
        formationDTO.setFormateurDto(formateurDto);
        formationDTO.setUserprofildto(userprofilDTO);
        DemandeclientDTO demandeclientDTO = new DemandeclientDTO();
        demandeclientDTO.setId(1L);
        demandeclientDTO.setFormationdto(formationDTO);
        when(demandeclientRepository.save(Mockito.any(Demandeclient.class))).thenReturn(new Demandeclient());
        DemandeclientDTO addedDemandeclientDTO = demandeformationclientService.add(demandeclientDTO);
        assertEquals(demandeclientDTO.getId(), addedDemandeclientDTO.getId());
        verify(demandeclientRepository, times(1)).save(Mockito.any(Demandeclient.class));
    }

    @Test
    void testRecherchDemandeclient() {

        Long demandeclientId = 1L;
        Formation formation = new Formation();
        formation.setFormateur(new Formateur());
        formation.setUserProfile(new UserProfile());
        formation.setFormateur(new Formateur());
        Demandeclient demandeclient = new Demandeclient();
        demandeclient.setId(demandeclientId);
        demandeclient.setFormation(formation);
        Optional<Demandeclient> optionalDemandeclient = Optional.of(demandeclient);
        when(demandeclientRepository.findById(demandeclientId)).thenReturn(optionalDemandeclient);
        DemandeclientDTO foundDemandeclientDTO = demandeformationclientService.recherch(demandeclientId);
        assertEquals(demandeclient.getId(), foundDemandeclientDTO.getId());
        verify(demandeclientRepository, times(1)).findById(demandeclientId);
    }

    @Test
    void testDeleteDemandeclient() {
        Long demandeclientId = 1L;
        demandeformationclientService.delete(demandeclientId);
        verify(demandeclientRepository, times(1)).deleteById(demandeclientId);
    }
}

