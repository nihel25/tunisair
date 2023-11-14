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

import static org.junit.jupiter.api.Assertions.assertEquals;
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




}
