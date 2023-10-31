package tn.esprit.tunisair.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.dto.SpecialiteeDTO;
import tn.esprit.tunisair.repository.specialiteeRepository;
import tn.esprit.tunisair.entity.Specialitee;
import tn.esprit.tunisair.validations.ObjectsValidator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OptionServiceImpl implements OptionService{


    @Autowired
    specialiteeRepository specialiteeRepository;

    private final ObjectsValidator<SpecialiteeDTO> objectsValidator;

    public OptionServiceImpl(ObjectsValidator<SpecialiteeDTO> objectsValidator) {
        this.objectsValidator = objectsValidator;
    }


    @Override
    public SpecialiteeDTO ajouterOption(SpecialiteeDTO p) {

        objectsValidator.validate(p);
        Specialitee participant = SpecialiteeDTO.toentity(p);





        Specialitee saved= specialiteeRepository.save(participant);

        return SpecialiteeDTO.fromEntity(saved);

    }

    @Override
    public List<SpecialiteeDTO> findAllspecialite() {
        return specialiteeRepository.findAll()
                .stream()
                .map(SpecialiteeDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
