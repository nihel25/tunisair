package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.AvisDto;
import tn.esprit.tunisair.dto.UserDTO;
import tn.esprit.tunisair.entity.Avis;
import tn.esprit.tunisair.repository.AvisRepository;
import tn.esprit.tunisair.service.AvisServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AvisServiceTests {

    @Mock
    private AvisRepository avisRepository;

    @InjectMocks
    private AvisServiceImpl avisService;

    @Test
    void testSaveAvis() {
        // Créez un AvisDto avec des valeurs appropriées
        AvisDto avisDto = new AvisDto();
        UserDTO userDTO = new UserDTO();
        avisDto.setUserDTO(userDTO);
        avisDto.setText("Très bon service");


        // Convertissez le DTO en entité
        Avis avis = AvisDto.toentity(avisDto);

        // Utilisez doReturn().when() pour configurer le stub dans le mock
        doReturn(avis).when(avisRepository).save(Mockito.any(Avis.class));

        // Appelez la méthode du service
        AvisDto savedAvisDTO = avisService.save(avisDto);

        // Vérifiez si les valeurs sont conformes aux attentes
        assertEquals(avisDto.getText(), savedAvisDTO.getText());

        // Vérifiez si la méthode save du repository a été appelée
        verify(avisRepository, times(1)).save(Mockito.any(Avis.class));
    }




}
