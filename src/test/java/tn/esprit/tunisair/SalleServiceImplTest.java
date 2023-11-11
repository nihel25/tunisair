package tn.esprit.tunisair;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tunisair.dto.SalleDTO;
import tn.esprit.tunisair.entity.Salle;
import tn.esprit.tunisair.repository.SalleRepository;
import tn.esprit.tunisair.service.SalleServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SalleServiceImplTest {

    @Mock
    private SalleRepository salleRepository;

    @InjectMocks
    private SalleServiceImpl salleService;

    @Test
    @Order(1)
    void testSaveSalle() {
        // Créer des données de test
        SalleDTO salleDTO = new SalleDTO(); // Initialisez avec des valeurs appropriées

        salleDTO.setNomsalle("maria");
        salleDTO.setNombreplace(300L);
        Salle salle = SalleDTO.toentity(salleDTO);


        when(salleRepository.save(salle)).thenReturn(salle);


        SalleDTO savedSalleDTO = salleService.save(salleDTO);

        // Vérifier les résultats

        assertEquals(salleDTO.getNomsalle(), savedSalleDTO.getNomsalle());
        assertEquals(salleDTO.getNombreplace(), savedSalleDTO.getNombreplace());

    }

    @Test
    @Order(2)
    void testRecherch() {
        // Créer des données de test
        Long salleId = 1L;
        Salle salle = new Salle();
      //  salle.setId(salleId);
        salle.setNomsalle("jerba");
        salle.setNombreplace(123L);
        SalleDTO expectedSalleDTO = SalleDTO.fromEntity(salle);

        // Définir le comportement simulé du repository
        when(salleRepository.findById(salleId)).thenReturn(Optional.of(salle));

        // Appeler la méthode du service
        SalleDTO foundSalleDTO = salleService.recherch(salleId);

        // Vérifier les résultats

       // assertEquals(expectedSalleDTO.getId(), foundSalleDTO.getId());
        assertEquals(expectedSalleDTO.getNomsalle(), foundSalleDTO.getNomsalle());
        assertEquals(expectedSalleDTO.getNombreplace(), foundSalleDTO.getNombreplace());
    }

    @Test
    @Order(3)
    void testDelete() {
        // Créer des données de test
        Long salleId = 1L;

        // Appeler la méthode du service
        salleService.delete(salleId);

        // Vérifier que la méthode deleteById du repository a été appelée une fois avec le bon argument
        verify(salleRepository, times(1)).deleteById(salleId);
    }

    @Test
    @Order(4)
    void testFindAllSalle() {
        // Créer des données de test
        Salle salle1 = new Salle();
        Salle salle2 = new Salle();
        List<Salle> salleList = new ArrayList<>();
        salleList.add(salle1);
        salleList.add(salle2);

        // Définir le comportement simulé du repository
        when(salleRepository.findAll()).thenReturn(salleList);

        // Appeler la méthode du service
        List<SalleDTO> foundSalleDTOs = salleService.findAllSalle();

        // Vérifier les résultats
        assertEquals(salleList.size(), foundSalleDTOs.size());
        // Ajoutez d'autres vérifications en fonction de votre logique métier et de la conversion DTO.
    }
}
