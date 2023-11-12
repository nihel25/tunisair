package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.PersonnelDTO;
import tn.esprit.tunisair.entity.Personnel;
import tn.esprit.tunisair.repository.PersonnelRepository;
import tn.esprit.tunisair.service.PersonnelleServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonnelleServiceImplTests {

    @Mock
    private PersonnelRepository personnelRepository;

    @InjectMocks
    private PersonnelleServiceImpl personnelleService;

    @Test
    void testRecherchPersonnel() {
        // ID du personnel à rechercher
        Long personnelId = 1L;

        // Créer un objet Personnel
        Personnel personnel = new Personnel();
        personnel.setId(personnelId);
        personnel.setNom("NomPersonnel");
        personnel.setPrenom("PrenomPersonnel");

        // Créer un Optional<Personnel>
        Optional<Personnel> optionalPersonnel = Optional.of(personnel);

        // Configurer le comportement du repository mock
        when(personnelRepository.findById(personnelId)).thenReturn(optionalPersonnel);

        // Appeler la méthode recherch du service
        PersonnelDTO foundPersonnelDTO = personnelleService.recherch(personnelId);

        // Vérifier si les valeurs sont conformes aux attentes
        assertEquals(personnel.getId(), foundPersonnelDTO.getId());
        assertEquals(personnel.getNom(), foundPersonnelDTO.getNom());
        assertEquals(personnel.getPrenom(), foundPersonnelDTO.getPrenom());

        // Vérifier si la méthode findById du repository a été appelée
        verify(personnelRepository, times(1)).findById(personnelId);
    }

    @Test
    void testDeletePersonnel() {
        // ID du personnel à supprimer
        Long personnelId = 1L;

        // Appeler la méthode delete du service
        personnelleService.delete(personnelId);

        // Vérifier si la méthode deleteById du repository a été appelée
        verify(personnelRepository, times(1)).deleteById(personnelId);
    }

    @Test
    void testSavePersonnel() {
        // Configurer le comportement du repository mock
        when(personnelRepository.save(Mockito.any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Créer un objet PersonnelDTO
        PersonnelDTO personnelDTO = new PersonnelDTO();
        personnelDTO.setNom("NomPersonnel");
        personnelDTO.setPrenom("PrenomPersonnel");

        // Appeler la méthode save du service
        PersonnelDTO savedPersonnelDTO = personnelleService.save(personnelDTO);

        // Vérifier si les valeurs sont conformes aux attentes
        assertEquals(personnelDTO.getNom(), savedPersonnelDTO.getNom());
        assertEquals(personnelDTO.getPrenom(), savedPersonnelDTO.getPrenom());

        // Vérifier si la méthode save du repository a été appelée
        verify(personnelRepository, times(1)).save(Mockito.any());
    }

    @Test
    void testFindAllPersonnelle() {
        // Créer deux encadreurs
        Personnel personnel1 = new Personnel();
        Personnel personnel2 = new Personnel();
        List<Personnel> encadreurList = new ArrayList<>();
        encadreurList.add(personnel1);
        encadreurList.add(personnel2);

        // Configurer le comportement du repository mock
        when(personnelRepository.findAll()).thenReturn(encadreurList);

        // Appeler la méthode du service
        List<Personnel> foundEncadreurDTOs = personnelleService.getAllPersonnel();

        // Vérifier si le nombre d'éléments retournés correspond au nombre d'encadreurs créés
        assertEquals(encadreurList.size(), foundEncadreurDTOs.size());
    }
}
