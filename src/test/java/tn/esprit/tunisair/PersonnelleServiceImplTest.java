package tn.esprit.tunisair;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.tunisair.dto.PersonnelDTO;
import tn.esprit.tunisair.entity.Personnel;
import tn.esprit.tunisair.repository.PersonnelRepository;
import tn.esprit.tunisair.service.PersonnelleServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonnelleServiceImplTest {

    @Mock
    private PersonnelRepository personnelRepository;

    @InjectMocks
    private PersonnelleServiceImpl personnelleService;

    @Test
    @Order(1)
    void testChargerDonneesCSV() {
        // Simulez le fichier CSV
        String csvData = "John;Doe;12345;john@example.com;Developer";
        MultipartFile mockFile = createMockMultipartFile("test.csv", csvData);

        // Appelez la méthode pour charger les données CSV
        personnelleService.chargerDonneesCSV(mockFile);

        // Vérifiez que le repository a été appelé pour sauvegarder le personnel
        verify(personnelRepository, times(1)).save(any(Personnel.class));
    }

    @Test
    @Order(2)
    void testRecherch() {
        // ID du personnel à rechercher
        Long personnelId = 1L;

        // Créez un personnel simulé
        Personnel personnel = new Personnel();
        personnel.setId(personnelId);
        personnel.setNom("John");
        personnel.setPrenom("Doe");
        personnel.setCin("12345");
        personnel.setEmail("john@example.com");
        personnel.setFonction("Developer");

        // Créez une DTO attendue
        PersonnelDTO expectedPersonnelDTO = PersonnelDTO.fromentity(personnel);

        // Simulez la recherche par ID
        when(personnelRepository.findById(personnelId)).thenReturn(Optional.of(personnel));

        // Appelez la méthode de service pour rechercher le personnel
        PersonnelDTO foundPersonnelDTO = personnelleService.recherch(personnelId);

        // Vérifiez que les données sont correctes
        assertEquals(expectedPersonnelDTO.getId(), foundPersonnelDTO.getId());
        assertEquals(expectedPersonnelDTO.getNom(), foundPersonnelDTO.getNom());
        assertEquals(expectedPersonnelDTO.getPrenom(), foundPersonnelDTO.getPrenom());
        assertEquals(expectedPersonnelDTO.getCin(), foundPersonnelDTO.getCin());
        assertEquals(expectedPersonnelDTO.getEmail(), foundPersonnelDTO.getEmail());
        assertEquals(expectedPersonnelDTO.getFonction(), foundPersonnelDTO.getFonction());
    }

    @Test
    @Order(3)
    void testDelete() {
        // ID du personnel à supprimer
        Long personnelId = 1L;

        // Appelez la méthode de suppression dans le service
        personnelleService.delete(personnelId);

        // Vérifiez que la méthode deleteById du repository a été appelée avec le bon ID
        verify(personnelRepository, times(1)).deleteById(personnelId);
    }

    @Test
    @Order(4)
    void testSave() {
        // Créez une DTO de personnel simulée
        PersonnelDTO personnelDTO = new PersonnelDTO();
        personnelDTO.setNom("John");
        personnelDTO.setPrenom("Doe");
        personnelDTO.setCin("12345");
        personnelDTO.setEmail("john@example.com");
        personnelDTO.setFonction("Developer");

        // Créez un personnel simulé à partir de la DTO
        Personnel simulatedPersonnel = PersonnelDTO.toentity(personnelDTO);

        // Simulez la sauvegarde dans le repository
        when(personnelRepository.save(simulatedPersonnel)).thenReturn(simulatedPersonnel);

        // Appelez la méthode de service pour sauvegarder le personnel
        PersonnelDTO savedPersonnelDTO = personnelleService.save(personnelDTO);

        // Vérifiez que les données sont correctes
        assertEquals(personnelDTO.getNom(), savedPersonnelDTO.getNom());
        assertEquals(personnelDTO.getPrenom(), savedPersonnelDTO.getPrenom());
        assertEquals(personnelDTO.getCin(), savedPersonnelDTO.getCin());
        assertEquals(personnelDTO.getEmail(), savedPersonnelDTO.getEmail());
        assertEquals(personnelDTO.getFonction(), savedPersonnelDTO.getFonction());
    }

    // Méthode utilitaire pour créer un MultipartFile simulé à partir de données CSV
    private MultipartFile createMockMultipartFile(String fileName, String content) {
        return new MockMultipartFile(fileName, fileName, "text/csv", content.getBytes());
    }
}

