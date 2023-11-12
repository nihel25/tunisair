package tn.esprit.tunisair;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tunisair.dto.FormateurDto;
import tn.esprit.tunisair.entity.Formateur;
import tn.esprit.tunisair.repository.FormateurRepository;
import tn.esprit.tunisair.repository.FormationRepository;
import tn.esprit.tunisair.repository.SpecialiteeRepository;
import tn.esprit.tunisair.service.FormateurServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FormateurServiceImplTest {

    @Mock
    private FormateurRepository formateurRepository;

    @Mock
    private FormationRepository formationRepository;

    @Mock
    private SpecialiteeRepository specialiteeRepository;

    @InjectMocks
    private FormateurServiceImpl formateurService;

    @Test
    @Order(1)
    void testSaveFormateur() {
        // Créez un FormateurDto avec des valeurs appropriées
        FormateurDto formateurDto = new FormateurDto();
        formateurDto.setNom("John");
        formateurDto.setPrenom("Doe");

        // Convertissez le DTO en entité
        Formateur formateur = FormateurDto.toentity(formateurDto);

        // Définissez le comportement du repository mock lorsqu'il est appelé
        when(formateurRepository.save(formateur)).thenReturn(formateur);

        // Appelez la méthode du service
        FormateurDto savedFormateurDTO = formateurService.save(formateurDto);

        // Vérifiez si les valeurs sont conformes aux attentes
        assertEquals(formateurDto.getNom(), savedFormateurDTO.getNom());
        assertEquals(formateurDto.getPrenom(), savedFormateurDTO.getPrenom());
    }

    @Test
    @Order(2)
    void testRecherchFormateur() {
        // ID du formateur à rechercher
        Long formateurId = 1L;

        // Créez un Formateur avec des valeurs appropriées
        Formateur formateur = new Formateur();
        formateur.setId(formateurId);
        formateur.setNom("John");
        formateur.setPrenom("Doe");

        // Convertissez l'entité en DTO
        FormateurDto expectedFormateurDTO = FormateurDto.fromentity(formateur);

        // Définissez le comportement du repository mock lorsqu'il est appelé
        when(formateurRepository.findById(formateurId)).thenReturn(Optional.of(formateur));

        // Appelez la méthode du service
        FormateurDto foundFormateurDTO = formateurService.recherch(formateurId);

        // Vérifiez si les valeurs sont conformes aux attentes
        assertEquals(expectedFormateurDTO.getId(), foundFormateurDTO.getId());
        assertEquals(expectedFormateurDTO.getNom(), foundFormateurDTO.getNom());
        assertEquals(expectedFormateurDTO.getPrenom(), foundFormateurDTO.getPrenom());
    }

    @Test
    @Order(3)
    void testDeleteFormateur() {
        // ID du formateur à supprimer
        Long formateurId = 1L;

        // Appelez la méthode de suppression dans le service
        formateurService.delete(formateurId);

        // Vérifiez que la méthode deleteById du repository a été appelée avec le bon ID
        verify(formateurRepository, times(1)).deleteById(formateurId);
    }

    @Test
    @Order(4)
    void testFindAllFormateur() {
        // Créez deux formateurs
        Formateur formateur1 = new Formateur();
        Formateur formateur2 = new Formateur();
        List<Formateur> formateurList = new ArrayList<>();
        formateurList.add(formateur1);
        formateurList.add(formateur2);

        // Définissez le comportement du repository mock lorsqu'il est appelé
        when(formateurRepository.findAll()).thenReturn(formateurList);

        // Appelez la méthode du service
        List<FormateurDto> foundFormateurDTOs = formateurService.findAll();

        // Vérifiez si le nombre d'éléments retournés correspond au nombre de formateurs créés
        assertEquals(formateurList.size(), foundFormateurDTOs.size());
    }
}
