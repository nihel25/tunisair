package tn.esprit.tunisair.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.DTO.SalleDTO;
import tn.esprit.tunisair.Repository.SalleRepository;
import tn.esprit.tunisair.entity.Salle;
import tn.esprit.tunisair.validations.ObjectsValidator;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SalleServiceImpl implements SalleService{

    @Autowired
    SalleRepository salleRepository;
    @Override
    public void ajoutersalle(Salle s) {
        salleRepository.save(s);
    }

    private final ObjectsValidator<SalleDTO> objectsValidator;

    public SalleServiceImpl(ObjectsValidator<SalleDTO> objectsValidator) {
        this.objectsValidator = objectsValidator;
    }





    @Override
    public SalleDTO save(SalleDTO salleDTO) {



        Salle salle=salleDTO.toentity(salleDTO);
        salleRepository.save(salle);
        SalleDTO encadreurDTOsaved=salleDTO.fromEntity(salle);
        return encadreurDTOsaved;
    }
@Override
    //
    public SalleDTO recherch(Long id) {


        Optional<Salle> optionalEncadreur =salleRepository.findById(id);
        if (optionalEncadreur.isPresent()) {
            Salle salle=optionalEncadreur.get();
            return SalleDTO.fromEntity(salle);
        }
        else
        {
            return null;
        }}





















    @Override
    public void delete(Long id) {
        Salle salle = salleRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
        salleRepository.deleteById(id);
    }

    @Override
    public void findid(Long id) {
         salleRepository.findById(id);
    }

    @Override
    public Salle retrievesallebyid(Long id) {
        Salle salle = salleRepository.findById(id).orElse(null);

        return salle;
    }

    @Override
    public List<SalleDTO> findAllSalle() {
        return salleRepository.findAll()
                .stream()
                .map(SalleDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
