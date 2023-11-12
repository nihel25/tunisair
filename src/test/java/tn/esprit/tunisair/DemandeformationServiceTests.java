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
        // Créer une demande de formation avec des valeurs appropriées
        Demandeformation demandeFormation = new Demandeformation();
        demandeFormation.setId(1L);
        demandeFormation.setValid("en attente");

        // Configurer le comportement du repository mock
        doReturn(demandeFormation).when(demandeformationRepository).save(Mockito.any(Demandeformation.class));

        // Appeler la méthode du service
        demandeformationService.saveDemandeFormation(demandeFormation);

        // Vérifier si la méthode save du repository a été appelée
        verify(demandeformationRepository, times(1)).save(Mockito.any(Demandeformation.class));
    }



    @Test
    void testRecherch() {
        // ID de la demande à rechercher
        Long demandeId = 1L;
        Formation formation = new Formation();

        // Assurez-vous que chaque stagiaire a un stage non null
        formation.setFormateur(new Formateur());
        formation.setUserProfile(new UserProfile());
        formation.setFormateur(new Formateur());

        // Créer une demande de formation avec des valeurs appropriées
        Demandeformation demande = new Demandeformation();
        demande.setId(demandeId);
demande.setFormation(formation);
        // Créer un Optional<Demandeformation>
        Optional<Demandeformation> optionalDemande = Optional.of(demande);

        // Configurer le comportement du repository mock
        when(demandeformationRepository.findById(demandeId)).thenReturn(optionalDemande);

        // Appeler la méthode recherch du service
        DemandeFormationDTO foundDemandeDTO = demandeformationService.recherch(demandeId);

        // Vérifier si les valeurs sont conformes aux attentes
        assertEquals(demande.getId(), foundDemandeDTO.getId());

        // Vérifier si la méthode findById du repository a été appelée
        verify(demandeformationRepository, times(1)).findById(demandeId);
    }



    @Test
    void testDelete() {

        Long demandeId = 1L;

        demandeformationService.delete(demandeId);

        verify(demandeformationRepository, times(1)).deleteById(demandeId);
    }
}
