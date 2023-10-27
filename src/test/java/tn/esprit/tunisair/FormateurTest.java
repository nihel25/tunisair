package tn.esprit.tunisair;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.tunisair.DTO.FormateurDto;
import tn.esprit.tunisair.DTO.SpecialiteeDTO;
import tn.esprit.tunisair.Repository.FormateurRepository;
import tn.esprit.tunisair.Service.FormateurServiceImpl;
import tn.esprit.tunisair.entity.Formateur;
import tn.esprit.tunisair.entity.Specialitee;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FormateurTest {


    @Mock
    FormateurRepository formateurRepository;

    @InjectMocks
    FormateurServiceImpl formateurService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testSave() {

        FormateurDto formateurDto = new FormateurDto();
        formateurDto.setId(50L);
        formateurDto.setNom("riahi");
        formateurDto.setPrenom("maram");
        SpecialiteeDTO specialiteeDTO = new SpecialiteeDTO();
        formateurDto.setSpecialiteedto(specialiteeDTO);
        when(formateurRepository.save(any(Formateur.class))).thenAnswer(invocation -> {
            Formateur savedStage = invocation.getArgument(0);
            savedStage.setId(50L);
            return savedStage;
        });

        FormateurDto savedStageDTO = formateurService.save(formateurDto);
        verify(formateurRepository, times(1)).save(any(Formateur.class));
        assertNotNull(savedStageDTO);
        assertEquals(formateurDto.getNom(), savedStageDTO.getNom());
        assertEquals(formateurDto.getPrenom(), savedStageDTO.getPrenom());
        assertNotNull(savedStageDTO.getId());
    }




    @Test
    public void testRecherch() {


        Formateur formateur = new Formateur();

        formateur.setSpecialitee(new Specialitee());
        formateur.setId(1L);
        formateur.setNom("riahi");
        formateur.setPrenom("amin");


        when(formateurRepository.findById(1L)).thenReturn(Optional.of(formateur));
        FormateurDto formateurDto = formateurService.recherch(1L);

        assertEquals(formateur.getId(), formateurDto.getId());
        assertEquals(formateur.getNom(), formateurDto.getNom());
        assertEquals(formateur.getPrenom(), formateurDto.getPrenom());




    }





























}
