package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.*;
import tn.esprit.tunisair.entity.*;
import tn.esprit.tunisair.repository.MaterielRepository;
import tn.esprit.tunisair.service.MaterielServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MaterielServiceTests {

    @Mock
    private MaterielRepository materielRepository;

    @InjectMocks
    private MaterielServiceImpl materielService;

    @Test
    void testSaveMateriel() {

        SpecialiteeDTO specialitee = new SpecialiteeDTO();

        UserprofilDTO userprofilDTO = new UserprofilDTO();

        FormateurDto formateurDto = new FormateurDto();
        formateurDto.setSpecialiteedto(specialitee);
        FormationDTO formationDTO = new FormationDTO();
        formationDTO.setFormateurDto(formateurDto);
        formationDTO.setUserprofildto(userprofilDTO);
        SessionDTO sessionDTO = new SessionDTO();
SalleDTO salleDTO = new SalleDTO();
sessionDTO.setSalleDTO(salleDTO);
sessionDTO.setFormateurDto(formateurDto);
        sessionDTO.setFormationDTO(formationDTO);
        // Créez un MaterielDTO avec des valeurs appropriées
        MaterielDTO materielDTO = new MaterielDTO();
        materielDTO.setNom("Ordinateur");
        materielDTO.setCaracteristique("Puissant");
        materielDTO.setStatut(true);
        materielDTO.setSessionDTO(sessionDTO);
        // Convertissez le DTO en entité
        Materiel materiel = MaterielDTO.toentity(materielDTO);

        // Utilisez doReturn().when() pour configurer le stub dans le mock
        doReturn(materiel).when(materielRepository).save(Mockito.any(Materiel.class));

        // Appelez la méthode du service
        MaterielDTO savedMaterielDTO = materielService.save(materielDTO);

        // Vérifiez si les valeurs sont conformes aux attentes
        assertEquals(materielDTO.getNom(), savedMaterielDTO.getNom());
        assertEquals(materielDTO.getCaracteristique(), savedMaterielDTO.getCaracteristique());


        verify(materielRepository, times(1)).save(Mockito.any(Materiel.class));
    }



    @Test
    void testDeleteMateriel() {
        Long materielId = 1L;
        materielService.delete(materielId);
        verify(materielRepository, times(1)).deleteById(materielId);
    }

    @Test
    void testRecherch() {
        Long reclamationId = 1L;
        Materiel reclamation = new Materiel();
        reclamation.setId(reclamationId);
        Session session = new Session();
        reclamation.setSession(session);
        Optional<Materiel> optionalReclamation = Optional.of(reclamation);
        when(materielRepository.findById(reclamationId)).thenReturn(optionalReclamation);
        MaterielDTO foundReclamationDTO = materielService.recherch(reclamationId);
        assertEquals(reclamation.getId(), foundReclamationDTO.getId());
        verify(materielRepository, times(1)).findById(reclamationId);
    }

    @Test
    void testFindAllMateriell() {

        Materiel materiel1 = new Materiel();
        Materiel materiel2 = new Materiel();
        materiel1.setSession(new Session());
        materiel2.setSession(new Session());
        List<Materiel> stagiaireList = new ArrayList<>();
        stagiaireList.add(materiel1);
        stagiaireList.add(materiel2);
        when(materielRepository.findAll()).thenReturn(stagiaireList);
        List<MaterielDTO> foundStagiaireDTOs = materielService.findAllreclamation();
        assertEquals(stagiaireList.size(), foundStagiaireDTOs.size());
    }
}
