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
    private FormateurRepository formateurRepositoryy;

    @Mock
    private FormationRepository formationRepository;

    @Mock
    private SpecialiteeRepository specialiteeRepository;

    @InjectMocks
    private FormateurServiceImpl formateurService;

    @Test
    void testSaveFormateur() {
        FormateurDto formateurDto = new FormateurDto();
        Formateur formateur = FormateurDto.toentity(formateurDto);
        doReturn(formateur).when(formateurRepositoryy).save(any(Formateur.class));
        FormateurDto savedFormateurDto = formateurService.save(formateurDto);
        assertEquals(formateurDto.getNom(), savedFormateurDto.getNom());
        assertEquals(formateurDto.getPrenom(), savedFormateurDto.getPrenom());
        verify(formateurRepositoryy, times(1)).save(any(Formateur.class));
    }

    @Test
    void testRecherchFormateur() {

        Long formateurId = 1L;
        Formateur formateur = new Formateur();
        formateur.setId(formateurId);
        formateur.setNom("Doe");
        formateur.setPrenom("John");
        Optional<Formateur> optionalFormateur = Optional.of(formateur);
        when(formateurRepositoryy.findById(formateurId)).thenReturn(optionalFormateur);
        FormateurDto foundFormateurDto = formateurService.recherch(formateurId);
        assertEquals(formateur.getId(), foundFormateurDto.getId());
        assertEquals(formateur.getNom(), foundFormateurDto.getNom());
        assertEquals(formateur.getPrenom(), foundFormateurDto.getPrenom());
        verify(formateurRepositoryy, times(1)).findById(formateurId);
    }

    @Test
    void testDeleteFormateur() {

        Long formateurId = 1L;
        formateurService.delete(formateurId);
        verify(formateurRepositoryy, times(1)).deleteById(formateurId);
    }

    @Test
    void testFindAllFormateur() {

        Formateur formateur1 = new Formateur();
        Formateur formateur2 = new Formateur();
        List<Formateur> formateurList = new ArrayList<>();
        formateurList.add(formateur1);
        formateurList.add(formateur2);
        when(formateurRepositoryy.findAll()).thenReturn(formateurList);
        List<FormateurDto> foundFormateurDTOs = formateurService.findAll();
        assertEquals(formateurList.size(), foundFormateurDTOs.size());
    }
}

