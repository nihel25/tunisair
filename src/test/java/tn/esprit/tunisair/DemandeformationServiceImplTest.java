package tn.esprit.tunisair;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tunisair.dto.DemandeFormationDTO;
import tn.esprit.tunisair.entity.Demandeformation;
import tn.esprit.tunisair.repository.DemandeformationRepository;
import tn.esprit.tunisair.service.DemandeformationServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemandeformationServiceImplTest {

    @Mock
    private DemandeformationRepository demandeformationRepository;



    @InjectMocks
    private DemandeformationServiceImpl demandeformationService;



    @Test
    @Order(1)
    void testSaveDemandeFormation() {
        // Créez une demande de formation avec des valeurs appropriées
        Demandeformation demandeFormation = new Demandeformation();
        demandeFormation.setId(1L);

        // Définissez le comportement du repository mock lorsqu'il est appelé
        when(demandeformationRepository.save(demandeFormation)).thenReturn(demandeFormation);

        // Appelez la méthode du service
        demandeformationService.saveDemandeFormation(demandeFormation);

        // Vérifiez si la méthode save du repository a été appelée
        verify(demandeformationRepository, times(1)).save(demandeFormation);
    }

    @Test
    @Order(2)
    void testFindAllDemandes() {
        // Créez une liste de demandes de formation
        List<Demandeformation> demandes = new ArrayList<>();
        Demandeformation demande1 = new Demandeformation();
        Demandeformation demande2 = new Demandeformation();
        demandes.add(demande1);
        demandes.add(demande2);

        // Définissez le comportement du repository mock lorsqu'il est appelé
        when(demandeformationRepository.findAll()).thenReturn(demandes);

        // Appelez la méthode du service
        List<DemandeFormationDTO> foundDemandeDTOs = demandeformationService.findAllDemandes();

        // Vérifiez si le nombre d'éléments retournés correspond au nombre de demandes créées
        assertEquals(demandes.size(), foundDemandeDTOs.size());
    }

    @Test
    @Order(3)
    void testRecherch() {
        // ID de la demande à rechercher
        Long demandeId = 1L;

        // Créez une demande de formation avec des valeurs appropriées
        Demandeformation demande = new Demandeformation();
        demande.setId(demandeId);

        // Créez un Optional<Demandeformation>
        Optional<Demandeformation> optionalDemande = Optional.of(demande);

        // Définissez le comportement du repository mock lorsqu'il est appelé
        when(demandeformationRepository.findById(demandeId)).thenReturn(optionalDemande);

        // Appelez la méthode recherch du service
        DemandeFormationDTO foundDemandeDTO = demandeformationService.recherch(demandeId);

        // Vérifiez si les valeurs sont conformes aux attentes
        assertEquals(demande.getId(), foundDemandeDTO.getId());

        // Vérifiez si la méthode findById du repository a été appelée
        verify(demandeformationRepository, times(1)).findById(demandeId);
    }


    @Test
    @Order(4)
    void testDelete() {
        // ID de la demande à supprimer
        Long demandeId = 1L;

        // Appelez la méthode delete du service
        demandeformationService.delete(demandeId);

        // Vérifiez si la méthode deleteById du repository a été appelée
        verify(demandeformationRepository, times(1)).deleteById(demandeId);
    }


}

