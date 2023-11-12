package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.repository.ReclamationRepository;
import tn.esprit.tunisair.service.ReclamationServiceImpl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
public class ReclamationServiceTests {


@Mock
    ReclamationRepository repository;


@InjectMocks
    ReclamationServiceImpl reclamationService;












    @Test
    void testDeleteReclamation() {
        // ID du stage à supprimer
        Long idrec = 1L;

        // Appeler la méthode delete du service
        reclamationService.delete(idrec);

        // Vérifier si la méthode deleteById du repository a été appelée
        verify(repository, times(1)).deleteById(idrec);
    }
}
