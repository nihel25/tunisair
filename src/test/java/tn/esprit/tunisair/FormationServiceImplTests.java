package tn.esprit.tunisair;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tn.esprit.tunisair.dto.FormateurDto;
import tn.esprit.tunisair.dto.FormationDTO;
import tn.esprit.tunisair.dto.SpecialiteeDTO;
import tn.esprit.tunisair.dto.UserprofilDTO;
import tn.esprit.tunisair.entity.Formateur;
import tn.esprit.tunisair.entity.Formation;
import tn.esprit.tunisair.entity.UserProfile;
import tn.esprit.tunisair.repository.FormateurRepository;
import tn.esprit.tunisair.repository.FormationRepository;
import tn.esprit.tunisair.repository.UserRepository;
import tn.esprit.tunisair.service.FormationServiceImpl;
import tn.esprit.tunisair.service.ImageStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FormationServiceImplTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FormationRepository formationRepository;

    @Mock
    private FormateurRepository formateurRepository;

    @Mock
    private ImageStorage imageStorage;

    @InjectMocks
    private FormationServiceImpl formationService;

    @Test
    void testSaveFormation() {
        // Créez un objet FormationDTO avec des valeurs appropriées
        FormationDTO formationDTO = new FormationDTO();

        UserprofilDTO userprofilDTO = new UserprofilDTO();
        // Initialisez les propriétés du formationDTO selon les besoins
        SpecialiteeDTO specialiteeDTO = new SpecialiteeDTO();
        FormateurDto formateurDto = new FormateurDto();
        formateurDto.setSpecialiteedto(specialiteeDTO);
        formationDTO.setFormateurDto(formateurDto);
        formationDTO.setUserprofildto(userprofilDTO);
        // Convertissez le DTO en entité
        Formation formation = FormationDTO.toEntity(formationDTO);

        // Utilisez doReturn().when() pour configurer le stub dans le mock
        doReturn(formation).when(formationRepository).save(Mockito.any(Formation.class));

        // Appelez la méthode du service
        FormationDTO savedFormationDTO = formationService.save(formationDTO);

        // Vérifiez si les valeurs sont conformes aux attentes
        assertEquals(formationDTO.getPrix(), savedFormationDTO.getPrix());
        // Ajoutez d'autres assertions en fonction des propriétés de votre DTO

        // Vérifiez si la méthode save du repository a été appelée
        verify(formationRepository, times(1)).save(Mockito.any(Formation.class));
    }

    @Test
    void testFindById() {
        // ID de la formation à rechercher
        Long formationId = 1L;
        UserprofilDTO userprofilDTO = new UserprofilDTO();
        // Initialisez les propriétés du formationDTO selon les besoins
        SpecialiteeDTO specialiteeDTO = new SpecialiteeDTO();
        FormateurDto formateurDto = new FormateurDto();
        formateurDto.setSpecialiteedto(specialiteeDTO);

        // Créer un objet FormationDTO avec des valeurs appropriées
        FormationDTO formationDTO = new FormationDTO();
        formationDTO.setFormateurDto(formateurDto);
        formationDTO.setUserprofildto(userprofilDTO);
        // Initialisez les propriétés du formationDTO selon les besoins

        // Convertir FormationDTO en Formation
        Formation formation = FormationDTO.toEntity(formationDTO);

        // Créer un Optional<Formation>
        Optional<Formation> optionalFormation = Optional.of(formation);

        // Configurer le comportement du repository mock
        when(formationRepository.findById(formationId)).thenReturn(optionalFormation);

        // Appeler la méthode findbyId du service
        ResponseEntity<Formation> foundFormationResponse = formationService.findbyId(formationId);

        // Vérifier si la réponse est conforme aux attentes
        assertEquals(200, foundFormationResponse.getStatusCodeValue());

        // Vérifier si la méthode findById du repository a été appelée
        verify(formationRepository, times(1)).findById(formationId);
    }
    @Test
    void testDelete() {
        // ID du stage à supprimer
        Long id = 1L;

        // Appeler la méthode delete du service
        formationService.delete(id);

        // Vérifier si la méthode deleteById du repository a été appelée
        verify(formationRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindAllFormation() {

        Formation formation1 = new Formation();
        Formation formation2 = new Formation();
        formation1.setFormateur(new Formateur());
        formation1.setUserProfile(new UserProfile());
        formation2.setFormateur(new Formateur());
        formation2.setUserProfile(new UserProfile());
        List<Formation> stagiaireList = new ArrayList<>();
        stagiaireList.add(formation1);
        stagiaireList.add(formation2);
        when(formationRepository.findAll()).thenReturn(stagiaireList);
        List<FormationDTO> foundStagiaireDTOs = formationService.findAllFormation();
        assertEquals(stagiaireList.size(), foundStagiaireDTOs.size());
    }






}
