package tn.esprit.tunisair;



import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tunisair.dto.MaterielDTO;
import tn.esprit.tunisair.entity.Materiel;
import tn.esprit.tunisair.repository.MaterielRepository;
import tn.esprit.tunisair.service.MaterielServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class MaterielServiceImplTest {

    @Mock
    private MaterielRepository materielRepository;

    @InjectMocks
    private MaterielServiceImpl materielService;

    @Test
    void testFindAllMateriel() {
        // Créer des données de test
        Materiel materiel1 = new Materiel(); // Initialisez avec des valeurs appropriées
        Materiel materiel2 = new Materiel(); // Initialisez avec des valeurs appropriées
        List<Materiel> materielList = new ArrayList<>();
        materielList.add(materiel1);
        materielList.add(materiel2);

        // Définir le comportement simulé du repository
        when(materielRepository.findAll()).thenReturn(materielList);

        // Appeler la méthode du service
        List<MaterielDTO> materielDTOList = materielService.findAllreclamation();

        // Vérifier les résultats
        assertEquals(materielList.size(), materielDTOList.size());
        // Ajoutez d'autres vérifications en fonction de votre logique métier et de la conversion DTO.
    }

    @Test
    void testSaveMateriel() {
        // Créer des données de test
        MaterielDTO materielDTO = MaterielDTO.builder()
                .nom("NomMaterielTest1")
                .caracteristique("CaracteristiqueTest2")
               // .session(/* Instanciez une session appropriée ici */)
                .build();

        Materiel materiel = MaterielDTO.toentity(materielDTO);

        // Définir le comportement simulé du repository
        when(materielRepository.save(materiel)).thenReturn(materiel);

        // Appeler la méthode du service
        MaterielDTO savedMaterielDTO = materielService.save(materielDTO);

        // Vérifier les résultats
//        assertEquals(materielDTO, savedMaterielDTO);
        // Ajoutez d'autres vérifications en fonction de votre logique métier et de la conversion DTO.
    }


    @Test
    void testRecherchMateriel() {
        // Créer des données de test
        Long materielId = 1L;
        Materiel materiel = new Materiel(); // Initialisez avec des valeurs appropriées

        // Définir le comportement simulé du repository
        when(materielRepository.findById(materielId)).thenReturn(Optional.of(materiel));

        // Appeler la méthode du service
        MaterielDTO foundMaterielDTO = materielService.recherch(materielId);

        // Vérifier les résultats
      //  assertEquals(MaterielDTO.fromentity(materiel), foundMaterielDTO);
        // Ajoutez d'autres vérifications en fonction de votre logique métier et de la conversion DTO.
    }

    @Test
    void testDeleteMateriel() {
        // Créer des données de test
        Long materielId = 1L;

        // Appeler la méthode du service
        materielService.delete(materielId);

        // Vérifier que la méthode deleteById du repository a été appelée une fois avec le bon paramètre
        verify(materielRepository, times(1)).deleteById(materielId);
    }
}
