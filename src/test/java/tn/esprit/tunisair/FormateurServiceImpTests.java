package tn.esprit.tunisair;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

@ExtendWith(MockitoExtension.class)
public class FormateurServiceImpTests {

    @Mock
    private FormateurRepository formateurRepository;

    @Mock
    private FormationRepository formationRepository;

    @Mock
    private SpecialiteeRepository specialiteeRepository;

    @InjectMocks
    private FormateurServiceImpl formateurService;

    @Test
    void testSaveFormateur() {
        // Créez un FormateurDto avec des valeurs appropriées
        FormateurDto formateurDto = new FormateurDto();
        // Initialisez les propriétés du formateurDto selon les besoins

        // Créez un Formateur avec des valeurs appropriées
        Formateur formateur = FormateurDto.toentity(formateurDto);

        // Utilisez doReturn().when() pour configurer le stub dans le mock
        doReturn(formateur).when(formateurRepository).save(any(Formateur.class));

        // Appelez la méthode du service
        FormateurDto savedFormateurDto = formateurService.save(formateurDto);

        // Vérifiez si les valeurs sont conformes aux attentes
        assertEquals(formateurDto.getNom(), savedFormateurDto.getNom());
        assertEquals(formateurDto.getPrenom(), savedFormateurDto.getPrenom());

        // Vérifiez si la méthode save du repository a été appelée
        verify(formateurRepository, times(1)).save(any(Formateur.class));
    }

    @Test
    void testRecherchFormateur() {
        // ID du formateur à rechercher
        Long formateurId = 1L;

        // Créer un objet Formateur avec des valeurs appropriées
        Formateur formateur = new Formateur();
        formateur.setId(formateurId);
        formateur.setNom("Doe");
        formateur.setPrenom("John");

        // Créer un Optional<Formateur>
        Optional<Formateur> optionalFormateur = Optional.of(formateur);

        // Configurer le comportement du repository mock
        when(formateurRepository.findById(formateurId)).thenReturn(optionalFormateur);

        // Appeler la méthode recherch du service
        FormateurDto foundFormateurDto = formateurService.recherch(formateurId);

        // Vérifier si les valeurs sont conformes aux attentes
        assertEquals(formateur.getId(), foundFormateurDto.getId());
        assertEquals(formateur.getNom(), foundFormateurDto.getNom());
        assertEquals(formateur.getPrenom(), foundFormateurDto.getPrenom());

        // Vérifier si la méthode findById du repository a été appelée
        verify(formateurRepository, times(1)).findById(formateurId);
    }

    @Test
    void testDeleteFormateur() {
        // ID du formateur à supprimer
        Long formateurId = 1L;

        // Appeler la méthode delete du service
        formateurService.delete(formateurId);

        // Vérifier si la méthode deleteById du repository a été appelée
        verify(formateurRepository, times(1)).deleteById(formateurId);
    }

    @Test
    void testFindAllFormateur() {
        // Créer deux formateurs
        Formateur formateur1 = new Formateur();
        Formateur formateur2 = new Formateur();
        List<Formateur> formateurList = new ArrayList<>();
        formateurList.add(formateur1);
        formateurList.add(formateur2);

        // Configurer le comportement du repository mock
        when(formateurRepository.findAll()).thenReturn(formateurList);

        // Appeler la méthode du service
        List<FormateurDto> foundFormateurDTOs = formateurService.findAll();

        // Vérifier si le nombre d'éléments retournés correspond au nombre de formateurs créés
        assertEquals(formateurList.size(), foundFormateurDTOs.size());
    }
}

