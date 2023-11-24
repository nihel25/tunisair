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


 class EncadreurServiceImpTests {

    @Mock
    private EncadreurRepository encadreurRepository;

    @InjectMocks
    private EncadreurServiceImp encadreurService;

    @Test
    void testSaveEncadrreur() {

        when(encadreurRepository.save(Mockito.any())).thenAnswer(invocation -> invocation.getArgument(0));

        EncadreurDTO encadreurDTO = new EncadreurDTO();
        encadreurDTO.setNom("Dupont");
        EncadreurDTO savedEncadreurDTO = encadreurService.save(encadreurDTO);
        assertEquals(encadreurDTO.getNom(), savedEncadreurDTO.getNom());
        verify(encadreurRepository, times(1)).save(Mockito.any());
    }

    @Test
    void testRecherchEncadreur() {

        Long encadreurId = 1L;
        Encadreur encadreur = new Encadreur();
        encadreur.setId(encadreurId);
        encadreur.setNom("Dupont");

        Optional<Encadreur> optionalEncadreur = Optional.of(encadreur);
        when(encadreurRepository.findById(encadreurId)).thenReturn(optionalEncadreur);

        EncadreurDTO foundEncadreurDTO = encadreurService.recherch(encadreurId);
        assertEquals(encadreur.getId(), foundEncadreurDTO.getId());
        assertEquals(encadreur.getNom(), foundEncadreurDTO.getNom());
        verify(encadreurRepository, times(1)).findById(encadreurId);
    }

    @Test
    void testDeleteEncadreur() {

        Long encadreurId = 1L;


        encadreurService.delete(encadreurId);

        verify(encadreurRepository, times(1)).deleteById(encadreurId);
    }

    @Test
    void testFindAllEncadreur() {

        Encadreur encadreur1 = new Encadreur();
        Encadreur encadreur2 = new Encadreur();
        List<Encadreur> encadreurList = new ArrayList<>();
        encadreurList.add(encadreur1);
        encadreurList.add(encadreur2);

        when(encadreurRepository.findAll()).thenReturn(encadreurList);

        // Appeler la méthode du service
        List<EncadreurDTO> foundEncadreurDTOs = encadreurService.findAll();


        assertEquals(encadreurList.size(), foundEncadreurDTOs.size());
    }


}
