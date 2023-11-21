package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.EncadreurDTO;
import tn.esprit.tunisair.entity.Encadreur;
import tn.esprit.tunisair.repository.EncadreurRepository;
import tn.esprit.tunisair.service.EncadreurServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)


public class EncadreurServiceImpTests {

    @Mock
    private EncadreurRepository encadreurRepository;

    @InjectMocks
    private EncadreurServiceImp encadreurService;

    @Test
    void testSaveEncadrreur() {
        // Configurer le comportement du repository mock
        when(encadreurRepository.save(Mockito.any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Créer un objet EncadreurDTO
        EncadreurDTO encadreurDTO = new EncadreurDTO();
        encadreurDTO.setNom("Dupont");

        // Appeler la méthode save du service
        EncadreurDTO savedEncadreurDTO = encadreurService.save(encadreurDTO);

        // Vérifier si les valeurs sont conformes aux attentes
        assertEquals(encadreurDTO.getNom(), savedEncadreurDTO.getNom());

        // Vérifier si la méthode save du repository a été appelée
        verify(encadreurRepository, times(1)).save(Mockito.any());
    }

    @Test
    void testRecherchEncadreur() {
        // ID de l'encadreur à rechercher
        Long encadreurId = 1L;

        // Créer un objet Encadreur
        Encadreur encadreur = new Encadreur();
        encadreur.setId(encadreurId);
        encadreur.setNom("Dupont");

        // Créer un Optional<Encadreur>
        Optional<Encadreur> optionalEncadreur = Optional.of(encadreur);

        // Configurer le comportement du repository mock
        when(encadreurRepository.findById(encadreurId)).thenReturn(optionalEncadreur);

        // Appeler la méthode recherch du service
        EncadreurDTO foundEncadreurDTO = encadreurService.recherch(encadreurId);

        // Vérifier si les valeurs sont conformes aux attentes
        assertEquals(encadreur.getId(), foundEncadreurDTO.getId());
        assertEquals(encadreur.getNom(), foundEncadreurDTO.getNom());

        // Vérifier si la méthode findById du repository a été appelée
        verify(encadreurRepository, times(1)).findById(encadreurId);
    }

    @Test
    void testDeleteEncadreur() {
        // ID de l'encadreur à supprimer
        Long encadreurId = 1L;

        // Appeler la méthode delete du service
        encadreurService.delete(encadreurId);

        // Vérifier si la méthode deleteById du repository a été appelée
        verify(encadreurRepository, times(1)).deleteById(encadreurId);
    }

    @Test
    void testFindAllEncadreur() {
        // Créer deux encadreurs
        Encadreur encadreur1 = new Encadreur();
        Encadreur encadreur2 = new Encadreur();
        List<Encadreur> encadreurList = new ArrayList<>();
        encadreurList.add(encadreur1);
        encadreurList.add(encadreur2);

        // Configurer le comportement du repository mock
        when(encadreurRepository.findAll()).thenReturn(encadreurList);

        // Appeler la méthode du service
        List<EncadreurDTO> foundEncadreurDTOs = encadreurService.findAll();

        // Vérifier si le nombre d'éléments retournés correspond au nombre d'encadreurs créés
        assertEquals(encadreurList.size(), foundEncadreurDTOs.size());
    }

    // Ajoutez d'autres tests si nécessaire
}
