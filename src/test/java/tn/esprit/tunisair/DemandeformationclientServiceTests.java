package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.*;
import tn.esprit.tunisair.entity.*;
import tn.esprit.tunisair.repository.DemandeclientRepository;
import tn.esprit.tunisair.service.DemandeformationclientServiceImpl;

import java.util.Date;
import java.util.List;
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



    @Test
    void testFindAllDemande() {

        Formation formation1 = new Formation();
        formation1.setUserProfile(new UserProfile());
        formation1.setId(1L);
        Demandeclient reclamation1 = new Demandeclient();
        reclamation1.setId(1L);

        reclamation1.setDateCreation(new Date());
        reclamation1.setFormation(formation1);
        Formation formation2 = new Formation();
        formation2.setId(2L);
        formation2.setUserProfile(new UserProfile());
        Reclamation reclamation2 = new Reclamation();
        reclamation2.setId(2L);
        reclamation2.setTypeReclamation("Type 2");
        reclamation2.setDatereclamation(new Date());
        reclamation2.setFormation(formation2);


        List<DemandeclientDTO> foundReclamationDTOs = demandeformationclientService.findAlldemandeformation();

    }
}

