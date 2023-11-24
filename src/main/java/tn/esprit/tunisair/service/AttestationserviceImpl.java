package tn.esprit.tunisair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.dto.AttestationDTO;
import tn.esprit.tunisair.entity.Attestation;
import tn.esprit.tunisair.repository.AttestationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AttestationserviceImpl implements AttestationService{




    private final AttestationRepository attestationRepository;


    @Autowired
    public AttestationserviceImpl(AttestationRepository attestationRepository) {
        this.attestationRepository = attestationRepository;
    }

    @Override
    public AttestationDTO recherch(Long id) {
        Optional<Attestation> optionalcertificat =attestationRepository.findById(id);
        if (optionalcertificat.isPresent()) {
            Attestation attestation=optionalcertificat.get();
            return AttestationDTO.fromentity(attestation);
        }
        else
        {
            return null;
        }
    }

    @Override
    public AttestationDTO save(AttestationDTO attestationDTO) {

        Attestation attestation = AttestationDTO.toentity(attestationDTO);

        Attestation saveattestation= attestationRepository.save(attestation);

        return AttestationDTO.fromentity(saveattestation);
    }

    @Override
    public void delete(Long id) {

        attestationRepository.deleteById(id);
    }




    @Override
    public List<AttestationDTO> findAll() {
        return attestationRepository.findAll()
                .stream()
                .map(AttestationDTO::fromentity)
                .collect(Collectors.toList());
    }
}
