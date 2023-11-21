package tn.esprit.tunisair;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.MaterielController;
import tn.esprit.tunisair.dto.MaterielDTO;
import tn.esprit.tunisair.service.MaterielService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MaterielControllerTests {

    @Mock
    private MaterielService materielService;

    @InjectMocks
    private MaterielController materielController;

    @Test
    void testAddMateriel() {
        // Créer un exemple de MaterielDTO
        MaterielDTO materielDTO = new MaterielDTO();
        materielDTO.setNom("Ordnateur");


        // Configurer le comportement du service mock
        when(materielService.save(Mockito.any())).thenReturn(materielDTO);

        // Appeler la méthode addmateriel du contrôleur
        ResponseEntity<MaterielDTO> result = materielController.addmateriel(materielDTO);

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(materielDTO, result.getBody());

        // Vérifier si la méthode save du service a été appelée
        verify(materielService, times(1)).save(Mockito.any());
    }

    @Test
    void testRecherch() {
        // Créer un exemple de MaterielDTO
        MaterielDTO materielDTO = new MaterielDTO();
        materielDTO.setId(1L);
        materielDTO.setNom("Ordinateur");

        // Initialisez les autres propriétés selon les besoins

        // Configurer le comportement du service mock
        when(materielService.recherch(1L)).thenReturn(materielDTO);

        // Appeler la méthode recherch du contrôleur
        MaterielDTO result = materielController.recherch(1L);

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(materielDTO, result);

        // Vérifier si la méthode recherch du service a été appelée
        verify(materielService, times(1)).recherch(1L);
    }

    @Test
    void testDelete() {
        // Appeler la méthode delete du contrôleur
        materielController.delete(1L);

        // Vérifier si la méthode delete du service a été appelée
        verify(materielService, times(1)).delete(1L);
    }

    @Test
    void testListe() {
        // Créer une liste simulée de MaterielDTO
        List<MaterielDTO> materielDTOList = new ArrayList<>();
        // Ajouter des éléments à la liste selon les besoins

        // Configurer le comportement du service mock
        when(materielService.findAllreclamation()).thenReturn(materielDTOList);

        // Appeler la méthode liste du contrôleur
        List<MaterielDTO> result = materielController.liste();

        // Vérifier si les résultats sont conformes aux attentes
        assertEquals(materielDTOList, result);

        // Vérifier si la méthode findAllreclamation du service a été appelée
        verify(materielService, times(1)).findAllreclamation();
    }
}
