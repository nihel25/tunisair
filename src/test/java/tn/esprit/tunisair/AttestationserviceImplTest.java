package tn.esprit.tunisair;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.*;
import tn.esprit.tunisair.entity.Attestation;
import tn.esprit.tunisair.repository.AttestationRepository;
import tn.esprit.tunisair.service.AttestationserviceImpl;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

public class AttestationserviceImplTest {

    @Mock
    private AttestationRepository attestationRepository;

    @InjectMocks
    private AttestationserviceImpl attestationService;

    @Test

    void testSaveAttestation() {
        // Créez une AttestationDTO simulée
        AttestationDTO attestationDTO = new AttestationDTO();
        attestationDTO.setDatenew(new Date());

        // Créez un PersonnelDTO simulé
        PersonnelDTO personnelDTO = new PersonnelDTO();
        personnelDTO.setId(1L);
        personnelDTO.setNom("John");
        personnelDTO.setPrenom("Doe");
        attestationDTO.setPersonnelDTO(personnelDTO);
        FormateurDto formateurDto = new FormateurDto();
        UserprofilDTO userprofilDTO = new UserprofilDTO();
        SpecialiteeDTO specialiteeDTO = new SpecialiteeDTO();
        formateurDto.setSpecialiteedto(specialiteeDTO);
        // Créez un FormationDTO simulé
        FormationDTO formationDTO = new FormationDTO();
        formationDTO.setId(2L);
        formationDTO.setFormationtype("Formation XYZ");
        formationDTO.setFormateurDto(formateurDto);
        formationDTO.setUserprofildto(userprofilDTO);
        attestationDTO.setFormationdto(formationDTO);

        // Créez une Attestation simulée (entité)
        Attestation attestationEntity = AttestationDTO.toentity(attestationDTO);

        // Simulez la sauvegarde dans le repository
        when(attestationRepository.save(Mockito.any(Attestation.class))).thenReturn(attestationEntity);

        // Appelez la méthode du service pour sauvegarder l'attestation
        AttestationDTO savedAttestationDTO = attestationService.save(attestationDTO);

        // Vérifiez si les données sont correctes
        assertEquals(attestationDTO.getDatenew(), savedAttestationDTO.getDatenew());
        assertEquals(attestationDTO.getPersonnelDTO().getNom(), savedAttestationDTO.getPersonnelDTO().getNom());
        assertEquals(attestationDTO.getFormationdto().getFormationtype(), savedAttestationDTO.getFormationdto().getFormationtype());
        // Continuez avec les autres assertions selon les propriétés de votre entité Attestation
        // Assurez-vous également de vérifier si la méthode save du repository a été appelée
        verify(attestationRepository, times(1)).save(Mockito.any(Attestation.class));
    }
    @Test
    void testDeleteStagee() {
        // ID du stage à supprimer
        Long id = 1L;

        // Appeler la méthode delete du service
        attestationService.delete(id);

        // Vérifier si la méthode deleteById du repository a été appelée
        verify(attestationRepository, times(1)).deleteById(id);
    }

}

