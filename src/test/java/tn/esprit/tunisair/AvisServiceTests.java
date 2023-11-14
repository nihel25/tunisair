package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.ComponentScan;
import tn.esprit.tunisair.dto.AvisDto;
import tn.esprit.tunisair.dto.UserDTO;
import tn.esprit.tunisair.entity.Avis;
import tn.esprit.tunisair.repository.AvisRepository;
import tn.esprit.tunisair.service.AvisServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ComponentScan(basePackages = "tn.esprit.tunisair.service")
@ExtendWith(MockitoExtension.class)
public class AvisServiceTests {

    @Mock
    private AvisRepository avisRepository;

    @InjectMocks
    private AvisServiceImpl avisService;

    @Test
    void testSaveAvis() {

        AvisDto avisDto = new AvisDto();
        UserDTO userDTO = new UserDTO();
        avisDto.setUserDTO(userDTO);
        avisDto.setText("Très bon service");
        Avis avis = AvisDto.toentity(avisDto);

        doReturn(avis).when(avisRepository).save(Mockito.any(Avis.class));

        AvisDto savedAvisDTO = avisService.save(avisDto);

        assertEquals(avisDto.getText(), savedAvisDTO.getText());

        verify(avisRepository, times(1)).save(Mockito.any(Avis.class));
    }

    @Test
    void testRecherchAvis() {
        // ID de l'avis à rechercher
        Long avisId = 1L;
UserDTO userDTO = new UserDTO();
        // Créer un exemple d'AvisDto
        AvisDto avisDto = new AvisDto();
        avisDto.setId(avisId);
        avisDto.setUserDTO(userDTO);
        // Initialisez les propriétés selon les besoins

        // Configurer le comportement du repository mock
        when(avisRepository.findById(avisId)).thenReturn(Optional.of(AvisDto.toentity(avisDto)));

        // Appeler la méthode recherch du service
        AvisDto result = avisService.recherch(avisId);

        // Vérifier si les résultats sont conformes aux attentes
        assertNotNull(result);
        assertEquals(avisDto.getId(), result.getId());

        // Vérifier si la méthode findById du repository a été appelée
        verify(avisRepository, times(1)).findById(avisId);
    }

    @Test
    void testFindAllAvis() {
        // Créer une liste d'exemple d'AvisDto
        List<AvisDto> avisDtoList = new ArrayList<>();
        // Ajouter des éléments à la liste selon les besoins

        // Configurer le comportement du repository mock
        when(avisRepository.findAll()).thenReturn(
                avisDtoList.stream().map(AvisDto::toentity).collect(Collectors.toList())
        );

        // Appeler la méthode findAll du service
        List<AvisDto> result = avisService.findAll();

        // Vérifier si les résultats sont conformes aux attentes
        assertNotNull(result);
        assertEquals(avisDtoList.size(), result.size());

        // Vérifier si la méthode findAll du repository a été appelée
        verify(avisRepository, times(1)).findAll();
    }


}
