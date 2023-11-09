package tn.esprit.tunisair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.entity.Certificat;
import tn.esprit.tunisair.dto.CertificatDTO;
import tn.esprit.tunisair.repository.CertificatRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CertificatServiceImpl implements CertificatService{
@Autowired
CertificatRepository certificatRepository;



    @Override
    public CertificatDTO recherch(Long id) {
        Optional<Certificat> optionalcertificat =certificatRepository.findById(id);
        if (optionalcertificat.isPresent()) {
            Certificat certificat=optionalcertificat.get();
            return CertificatDTO.fromentity(certificat);
        }
        else
        {
            return null;
        }
    }

    @Override
    public CertificatDTO save(CertificatDTO certificatDTO) {


        Certificat certificat = CertificatDTO.toentity(certificatDTO);

        Certificat savecertificat= certificatRepository.save(certificat);

        return CertificatDTO.fromentity(savecertificat);
    }

    @Override
    public void delete(Long id) {

        certificatRepository.deleteById(id);
    }

    @Override
    public List<CertificatDTO> findAll() {
        return certificatRepository.findAll()
                .stream()
                .map(CertificatDTO::fromentity)
                .collect(Collectors.toList());
    }
}
