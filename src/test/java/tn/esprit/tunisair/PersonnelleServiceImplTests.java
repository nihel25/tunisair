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

        Long personnelId = 1L;
        Personnel personnel = new Personnel();
        personnel.setId(personnelId);
        personnel.setNom("NomPersonnel");
        personnel.setPrenom("PrenomPersonnel");
        Optional<Personnel> optionalPersonnel = Optional.of(personnel);
        when(personnelRepository.findById(personnelId)).thenReturn(optionalPersonnel);
        PersonnelDTO foundPersonnelDTO = personnelleService.recherch(personnelId);
        assertEquals(personnel.getId(), foundPersonnelDTO.getId());
        assertEquals(personnel.getNom(), foundPersonnelDTO.getNom());
        assertEquals(personnel.getPrenom(), foundPersonnelDTO.getPrenom());
        verify(personnelRepository, times(1)).findById(personnelId);
    }

    @Test
    void testDeletePersonnel() {

        Long personnelId = 1L;
        personnelleService.delete(personnelId);
        verify(personnelRepository, times(1)).deleteById(personnelId);
    }

    @Test
    void testSavePersonnel() {

        when(personnelRepository.save(Mockito.any())).thenAnswer(invocation -> invocation.getArgument(0));
        PersonnelDTO personnelDTO = new PersonnelDTO();
        personnelDTO.setNom("NomPersonnel");
        personnelDTO.setPrenom("PrenomPersonnel");
        PersonnelDTO savedPersonnelDTO = personnelleService.save(personnelDTO);
        assertEquals(personnelDTO.getNom(), savedPersonnelDTO.getNom());
        assertEquals(personnelDTO.getPrenom(), savedPersonnelDTO.getPrenom());
        verify(personnelRepository, times(1)).save(Mockito.any());
    }

    @Test
    void testFindAllPersonnelle() {

        Personnel personnel1 = new Personnel();
        Personnel personnel2 = new Personnel();
        List<Personnel> encadreurList = new ArrayList<>();
        encadreurList.add(personnel1);
        encadreurList.add(personnel2);
        when(personnelRepository.findAll()).thenReturn(encadreurList);
        List<Personnel> foundEncadreurDTOs = personnelleService.getAllPersonnel();
        assertEquals(encadreurList.size(), foundEncadreurDTOs.size());
    }
}
