package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.controller.AvisController;
import tn.esprit.tunisair.dto.AvisDto;
import tn.esprit.tunisair.service.AvisService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AvisControllerTests {

    @Mock
    private AvisService avisService;

    @InjectMocks
    private AvisController avisController;

    @Test
    void testAddAvis() {
        AvisDto avisDto = new AvisDto();
        avisDto.setText("Ceci est un avis de test");

        when(avisService.save(avisDto)).thenReturn(avisDto);

        ResponseEntity<AvisDto> responseEntity = avisController.addavis(avisDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(avisDto, responseEntity.getBody());
        verify(avisService, times(1)).save(avisDto);
    }

    @Test
    void testRecherchAvis() {
        Long avisId = 1L;
        AvisDto avisDto = new AvisDto();
        avisDto.setId(avisId);
        avisDto.setText("Ceci est un avis de test");

        when(avisService.recherch(avisId)).thenReturn(avisDto);

        AvisDto result = avisController.recherch(avisId);

        assertEquals(avisDto, result);
        verify(avisService, times(1)).recherch(avisId);
    }

    @Test
    void testListeAvis() {
        List<AvisDto> avisDtoList = new ArrayList<>();

        when(avisService.findAll()).thenReturn(avisDtoList);

        List<AvisDto> result = avisController.liste();

        assertEquals(avisDtoList, result);
        verify(avisService, times(1)).findAll();
    }
}
