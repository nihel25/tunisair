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
 class MaterielControllerTests {

    @Mock
    private MaterielService materielService;

    @InjectMocks
    private MaterielController materielController;

    @Test
    void testAddMateriel() {

        MaterielDTO materielDTO = new MaterielDTO();
        materielDTO.setNom("Ordnateur");



        when(materielService.save(Mockito.any())).thenReturn(materielDTO);


        ResponseEntity<MaterielDTO> result = materielController.addmateriel(materielDTO);


        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(materielDTO, result.getBody());


        verify(materielService, times(1)).save(Mockito.any());
    }

    @Test
    void testRecherch() {

        MaterielDTO materielDTO = new MaterielDTO();
        materielDTO.setId(1L);
        materielDTO.setNom("Ordinateur");


        when(materielService.recherch(1L)).thenReturn(materielDTO);


        MaterielDTO result = materielController.recherch(1L);


        assertEquals(materielDTO, result);


        verify(materielService, times(1)).recherch(1L);
    }

    @Test
    void testDelete() {

        materielController.delete(1L);


        verify(materielService, times(1)).delete(1L);
    }

    @Test
    void testListe() {

        List<MaterielDTO> materielDTOList = new ArrayList<>();
        when(materielService.findAllreclamation()).thenReturn(materielDTOList);


        List<MaterielDTO> result = materielController.liste();


        assertEquals(materielDTOList, result);


        verify(materielService, times(1)).findAllreclamation();
    }
}
