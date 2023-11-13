package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.DemandeFormationDTO;
import tn.esprit.tunisair.entity.Demandeformation;
import tn.esprit.tunisair.entity.Formateur;
import tn.esprit.tunisair.entity.Formation;
import tn.esprit.tunisair.entity.UserProfile;
import tn.esprit.tunisair.repository.DemandeformationRepository;
import tn.esprit.tunisair.service.DemandeformationServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;




@ExtendWith(MockitoExtension.class)
public class DemandeformationServiceTests {

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
}
