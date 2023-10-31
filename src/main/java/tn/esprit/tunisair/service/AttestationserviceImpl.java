package tn.esprit.tunisair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.dto.AttestationDTO;
import tn.esprit.tunisair.repository.AttestationRepository;
import tn.esprit.tunisair.entity.Attestation;
import tn.esprit.tunisair.validations.ObjectsValidator;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AttestationserviceImpl implements AttestationService{

@Autowired
    AttestationRepository attestationRepository;



    private final ObjectsValidator<AttestationDTO> objectsValidator;

    public AttestationserviceImpl(ObjectsValidator<AttestationDTO> objectsValidator) {
        this.objectsValidator = objectsValidator;
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
        objectsValidator.validate(attestationDTO);

        Attestation attestation = AttestationDTO.toentity(attestationDTO);

        Attestation saveattestation= attestationRepository.save(attestation);

        return AttestationDTO.fromentity(saveattestation);
    }

    @Override
    public void delete(Long id) {
        Attestation attestation = attestationRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
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
