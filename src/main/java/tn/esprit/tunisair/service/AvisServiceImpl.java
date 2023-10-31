package tn.esprit.tunisair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.dto.AvisDto;
import tn.esprit.tunisair.repository.AvisRepository;
import tn.esprit.tunisair.entity.Avis;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AvisServiceImpl implements AvisService{

    @Autowired
    AvisRepository avisRepository;
    @Override
    public AvisDto save(AvisDto avisDto) {
        Avis avis = AvisDto.toentity(avisDto);

        Avis saveavis= avisRepository.save(avis);

        return AvisDto.fromentity(saveavis);
    }
    @Override
    public AvisDto recherch(Long id) {
        Optional<Avis> optionalcertificat =avisRepository.findById(id);
        if (optionalcertificat.isPresent()) {
            Avis certificat=optionalcertificat.get();
            return AvisDto.fromentity(certificat);
        }
        else
        {
            return null;
        }
    }


    @Override
    public List<AvisDto> findAll() {
        return avisRepository.findAll()
                .stream()
                .map(AvisDto::fromentity)
                .collect(Collectors.toList());
    }
}
