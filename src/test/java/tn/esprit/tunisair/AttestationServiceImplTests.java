package tn.esprit.tunisair;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tunisair.dto.*;
import tn.esprit.tunisair.entity.*;
import tn.esprit.tunisair.repository.AttestationRepository;
import tn.esprit.tunisair.service.AttestationserviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AttestationServiceImplTests {

    @Mock
    private AttestationRepository attestationRepository;

    @InjectMocks
    private AttestationserviceImpl attestationService;

    @Test
    void testSaveAttestation() {
        // Créez un objet AttestationDTO avec des valeurs appropriées
        AttestationDTO attestationDTO = new AttestationDTO();
        PersonnelDTO personnelDTO = new PersonnelDTO();
        FormateurDto formateurDto = new FormateurDto();
        UserprofilDTO userprofilDTO = new UserprofilDTO();
        FormationDTO formationDTO= new FormationDTO();
        formationDTO.setFormateurDto(formateurDto);
        formationDTO.setUserprofildto(userprofilDTO);
        attestationDTO.setPersonnelDTO(personnelDTO);
        attestationDTO.setFormationdto(formationDTO);
        Attestation attestation = AttestationDTO.toentity(attestationDTO);
        doReturn(attestation).when(attestationRepository).save(Mockito.any(Attestation.class));
        AttestationDTO savedAttestationDTO = attestationService.save(attestationDTO);
        verify(attestationRepository, times(1)).save(Mockito.any(Attestation.class));
    }

    @Test
    void testRecherchAttestation() {

        Long attestationId = 1L;
PersonnelDTO personnelDTO = new PersonnelDTO();
        FormationDTO formationDTO= new FormationDTO();
        FormateurDto formateurDto = new FormateurDto();
        UserprofilDTO userprofilDTO = new UserprofilDTO();
        formationDTO.setFormateurDto(formateurDto);
        formationDTO.setUserprofildto(userprofilDTO);
        AttestationDTO attestationDTO = new AttestationDTO();
        attestationDTO.setId(attestationId);
        attestationDTO.setPersonnelDTO(personnelDTO);
        attestationDTO.setFormationdto(formationDTO);
        Attestation attestation = AttestationDTO.toentity(attestationDTO);
        Optional<Attestation> optionalAttestation = Optional.of(attestation);
        when(attestationRepository.findById(attestationId)).thenReturn(optionalAttestation);
        AttestationDTO foundAttestationDTO = attestationService.recherch(attestationId);
        verify(attestationRepository, times(1)).findById(attestationId);
    }

    @Test
    void testDeleteAttestation() {

        Long attestationId = 1L;
        attestationService.delete(attestationId);
        verify(attestationRepository, times(1)).deleteById(attestationId);
    }

    @Test
    void testFindAllAttestation() {

        Formation formation = new Formation();
Personnel personnelDTO = new Personnel();
        Formateur formateurDto = new Formateur();
        UserProfile userprofilDTO = new UserProfile();
        formation.setFormateur(formateurDto);
        formation.setUserProfile(userprofilDTO);
        Attestation attestation1 = new Attestation();
        Attestation attestation2 = new Attestation();
        attestation1.setFormation(formation);
        attestation1.setPersonnel(personnelDTO);
        attestation2.setPersonnel(personnelDTO);
        attestation2.setFormation(formation);
        List<Attestation> attestationList = new ArrayList<>();
        attestationList.add(attestation1);
        attestationList.add(attestation2);
        when(attestationRepository.findAll()).thenReturn(attestationList);
        List<AttestationDTO> foundAttestationDTOs = attestationService.findAll();
        assertEquals(attestationList.size(), foundAttestationDTOs.size());
    }
}
