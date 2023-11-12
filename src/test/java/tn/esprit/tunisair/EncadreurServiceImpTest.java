package tn.esprit.tunisair;



import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tunisair.dto.EncadreurDTO;
import tn.esprit.tunisair.entity.Encadreur;
import tn.esprit.tunisair.repository.EncadreurRepository;
import tn.esprit.tunisair.service.EncadreurServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EncadreurServiceImpTest {

    @Mock
    private EncadreurRepository encadreurRepository;

    @InjectMocks
    private EncadreurServiceImp encadreurService;

    @Test
    @Order(1)
    void testSaveEncadreur() {
        // Créez un EncadreurDTO avec des valeurs appropriées
        EncadreurDTO encadreurDTO = new EncadreurDTO();
        encadreurDTO.setNom("Dupont");
        encadreurDTO.setPrenom("Jean");

        // Convertissez le DTO en entité
        Encadreur encadreur = EncadreurDTO.toEntity(encadreurDTO);

        // Définissez le comportement du repository mock lorsqu'il est appelé
        when(encadreurRepository.save(encadreur)).thenReturn(encadreur);

        // Appelez la méthode du service
        EncadreurDTO savedEncadreurDTO = encadreurService.save(encadreurDTO);

        // Vérifiez si les valeurs sont conformes aux attentes
        assertEquals(encadreurDTO.getNom(), savedEncadreurDTO.getNom());
        assertEquals(encadreurDTO.getPrenom(), savedEncadreurDTO.getPrenom());
    }

    @Test
    @Order(2)
    void testRecherchEncadreur() {
        // ID de l'encadreur à rechercher
        Long encadreurId = 1L;

        // Créez un Encadreur avec des valeurs appropriées
        Encadreur encadreur = new Encadreur();
        encadreur.setId(encadreurId);
        encadreur.setNom("Martin");
        encadreur.setPrenom("Paul");

        // Convertissez l'entité en DTO
        EncadreurDTO expectedEncadreurDTO = EncadreurDTO.fromEntity(encadreur);

        // Définissez le comportement du repository mock lorsqu'il est appelé
        when(encadreurRepository.findById(encadreurId)).thenReturn(Optional.of(encadreur));

        // Appelez la méthode du service
        EncadreurDTO foundEncadreurDTO = encadreurService.recherch(encadreurId);

        // Vérifiez si les valeurs sont conformes aux attentes
        assertEquals(expectedEncadreurDTO.getId(), foundEncadreurDTO.getId());
        assertEquals(expectedEncadreurDTO.getNom(), foundEncadreurDTO.getNom());
        assertEquals(expectedEncadreurDTO.getPrenom(), foundEncadreurDTO.getPrenom());
    }

    @Test
    @Order(3)
    void testDeleteEncadreur() {
        // ID de l'encadreur à supprimer
        Long encadreurId = 1L;

        // Appelez la méthode de suppression dans le service
        encadreurService.delete(encadreurId);

        // Vérifiez que la méthode deleteById du repository a été appelée avec le bon ID
        verify(encadreurRepository, times(1)).deleteById(encadreurId);
    }

    @Test
    @Order(4)
    void testFindAllEncadreur() {
        // Créez deux encadreurs
        Encadreur encadreur1 = new Encadreur();
        Encadreur encadreur2 = new Encadreur();
        List<Encadreur> encadreurList = new ArrayList<>();
        encadreurList.add(encadreur1);
        encadreurList.add(encadreur2);

        // Définissez le comportement du repository mock lorsqu'il est appelé
        when(encadreurRepository.findAll()).thenReturn(encadreurList);

        // Appelez la méthode du service
        List<EncadreurDTO> foundEncadreurDTOs = encadreurService.findAll();

        // Vérifiez si le nombre d'éléments retournés correspond au nombre d'encadreurs créés
        assertEquals(encadreurList.size(), foundEncadreurDTOs.size());
    }
}
