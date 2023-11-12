package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.*;
import tn.esprit.tunisair.entity.Materiel;
import tn.esprit.tunisair.repository.MaterielRepository;
import tn.esprit.tunisair.service.MaterielServiceImpl;

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


        // Vérifiez si la méthode save du repository a été appelée
        verify(materielRepository, times(1)).save(Mockito.any(Materiel.class));
    }



    @Test
    void testDeleteMateriel() {
        // ID du matériel à supprimer
        Long materielId = 1L;

        // Appeler la méthode delete du service
        materielService.delete(materielId);

        // Vérifier si la méthode deleteById du repository a été appelée
        verify(materielRepository, times(1)).deleteById(materielId);
    }


}
