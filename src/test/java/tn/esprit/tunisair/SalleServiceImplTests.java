package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.SalleDTO;
import tn.esprit.tunisair.entity.Salle;
import tn.esprit.tunisair.repository.SalleRepository;
import tn.esprit.tunisair.service.SalleServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SalleServiceImplTests {

    @Mock
    private SalleRepository salleRepository;

    @InjectMocks
    private SalleServiceImpl salleService;

    @Test
    void testSaveSalle() {
        // Configurer le comportement du repository mock
        when(salleRepository.save(Mockito.any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Créer un objet SalleDTO
        SalleDTO salleDTO = new SalleDTO();
        salleDTO.setNomsalle("maria");
        salleDTO.setNombreplace(300L);

        // Appeler la méthode save du service
        SalleDTO savedSalleDTO = salleService.save(salleDTO);

        // Vérifier si les valeurs sont conformes aux attentes
        assertEquals(salleDTO.getNomsalle(), savedSalleDTO.getNomsalle());
        assertEquals(salleDTO.getNombreplace(), savedSalleDTO.getNombreplace());

        // Vérifier si la méthode save du repository a été appelée
        verify(salleRepository, times(1)).save(Mockito.any());
    }

    @Test
    void testRecherch() {
        // ID de la salle à rechercher
        Long salleId = 1L;

        // Créer un objet Salle
        Salle salle = new Salle();
        salle.setId(salleId);
        salle.setNomsalle("jerba");
        salle.setNombreplace(123L);

        // Créer un Optional<Salle>
        Optional<Salle> optionalSalle = Optional.of(salle);

        // Configurer le comportement du repository mock
        when(salleRepository.findById(salleId)).thenReturn(optionalSalle);

        // Appeler la méthode recherch du service
        SalleDTO foundSalleDTO = salleService.recherch(salleId);

        // Vérifier si les valeurs sont conformes aux attentes
        assertEquals(salle.getId(), foundSalleDTO.getId());
        assertEquals(salle.getNomsalle(), foundSalleDTO.getNomsalle());
        assertEquals(salle.getNombreplace(), foundSalleDTO.getNombreplace());

        // Vérifier si la méthode findById du repository a été appelée
        verify(salleRepository, times(1)).findById(salleId);
    }

    // Ajoutez d'autres tests si nécessaire
}
