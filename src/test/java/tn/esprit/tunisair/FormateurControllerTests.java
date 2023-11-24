package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.FormateurController;
import tn.esprit.tunisair.dto.FormateurDto;
import tn.esprit.tunisair.service.FormateurService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class FormateurControllerTests {

    @Mock
    private FormateurService formateurService;

    @InjectMocks
    private FormateurController formateurController;

    @Test
    void testAddFormateur() {

        FormateurDto formateurDto = new FormateurDto();
        formateurDto.setNom("Nom du formateur");
        when(formateurService.save(formateurDto)).thenReturn(formateurDto);
        ResponseEntity<FormateurDto> responseEntity = formateurController.addformateur(formateurDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(formateurDto, responseEntity.getBody());
        verify(formateurService, times(1)).save(formateurDto);
    }

    @Test
    void testRecherchFormateeur() {

        Long formateurId = 1L;


        FormateurDto formateurDto = new FormateurDto();
        formateurDto.setId(formateurId);
        formateurDto.setNom("Nom du formateur");
        when(formateurService.recherch(formateurId)).thenReturn(formateurDto);
        FormateurDto result = formateurController.recherch(formateurId);
        assertEquals(formateurDto, result);
        verify(formateurService, times(1)).recherch(formateurId);
    }

    @Test
    void testDeleteFormateur() {

        Long formateurId = 1L;
        formateurController.delete(formateurId);
        verify(formateurService, times(1)).delete(formateurId);
    }

    @Test
    void testListeFormateur() {

        List<FormateurDto> formateurList = new ArrayList<>();
        when(formateurService.findAll()).thenReturn(formateurList);
        List<FormateurDto> result = formateurController.liste();
        assertEquals(formateurList, result);
        verify(formateurService, times(1)).findAll();
    }
}
