package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.DemandeFormationDTO;
import tn.esprit.tunisair.dto.FormateurDto;
import tn.esprit.tunisair.dto.FormationDTO;
import tn.esprit.tunisair.dto.UserprofilDTO;
import tn.esprit.tunisair.entity.*;
import tn.esprit.tunisair.repository.DemandeformationRepository;
import tn.esprit.tunisair.service.DemandeformationServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;




@ExtendWith(MockitoExtension.class)
 class DemandeformationServiceTests {

    @Mock
    private DemandeformationRepository demandeformationRepository;



    @InjectMocks
    private DemandeformationServiceImpl demandeformationService;

    @Test
    void testSaveDemandeFormation() {
        Demandeformation demandeFormation = new Demandeformation();
        demandeFormation.setId(1L);
        demandeFormation.setValid("en attente");
        doReturn(demandeFormation).when(demandeformationRepository).save(Mockito.any(Demandeformation.class));
        demandeformationService.saveDemandeFormation(demandeFormation);
        verify(demandeformationRepository, times(1)).save(Mockito.any(Demandeformation.class));
    }



    @Test
    void testRecherch() {
        Long demandeId = 1L;
        Formation formation = new Formation();
        formation.setFormateur(new Formateur());
        formation.setUserProfile(new UserProfile());
        formation.setFormateur(new Formateur());
        Demandeformation demande = new Demandeformation();
        demande.setId(demandeId);
demande.setFormation(formation);
        Optional<Demandeformation> optionalDemande = Optional.of(demande);
        when(demandeformationRepository.findById(demandeId)).thenReturn(optionalDemande);
        DemandeFormationDTO foundDemandeDTO = demandeformationService.recherch(demandeId);
        assertEquals(demande.getId(), foundDemandeDTO.getId());
        verify(demandeformationRepository, times(1)).findById(demandeId);
    }



    @Test
    void testDelete() {

        Long demandeId = 1L;

        demandeformationService.delete(demandeId);

        verify(demandeformationRepository, times(1)).deleteById(demandeId);
    }



    @Test
    void testFindAllDemande() {

        Formation formation1 = new Formation();
        formation1.setUserProfile(new UserProfile());
        formation1.setId(1L);
        Demandeformation reclamation1 = new Demandeformation();
        reclamation1.setId(1L);
        reclamation1.setNbrpersonnelle(5L);
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


        List<DemandeFormationDTO> foundReclamationDTOs = demandeformationService.findAllDemandes();

    }




    @Test
    void testAddDemandeFormation() {

        DemandeFormationDTO demandeFormationDTO = new DemandeFormationDTO();
        demandeFormationDTO.setId(1L);
        UserprofilDTO userprofilDTO = new UserprofilDTO();

        FormateurDto formateurDto = new FormateurDto();
        FormationDTO formationDTO = new FormationDTO();
        formationDTO.setFormateurDto(formateurDto);
        formationDTO.setUserprofildto(userprofilDTO);
        demandeFormationDTO.setFormationdto(formationDTO);
        when(demandeformationRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        DemandeFormationDTO addedDemandeFormationDTO = demandeformationService.add(demandeFormationDTO);

        assertNotNull(addedDemandeFormationDTO);
        assertEquals(demandeFormationDTO.getId(), addedDemandeFormationDTO.getId());

        verify(demandeformationRepository, times(1)).save(any());
    }
}
