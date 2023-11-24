package tn.esprit.tunisair.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.dto.MaterielDTO;
import tn.esprit.tunisair.entity.Materiel;
import tn.esprit.tunisair.repository.MaterielRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaterielServiceImpl implements MaterielService{



 private final   MaterielRepository materielRepository;





    @Autowired
    public MaterielServiceImpl(MaterielRepository materielRepository) {
        this.materielRepository = materielRepository;
    }






    @Override
    public List<MaterielDTO> findAllreclamation() {
        return materielRepository.findAll()
                .stream()
                .map(MaterielDTO::fromentity)
                .collect(Collectors.toList());
    }

    @Override
    public MaterielDTO save(MaterielDTO materielDTO) {


        Materiel materiel=MaterielDTO.toentity(materielDTO);
        materielRepository.save(materiel);
        return MaterielDTO.fromentity(materiel);


    }

    @Override
    public MaterielDTO recherch(Long id) {
        Optional<Materiel> optionalmateriel =materielRepository.findById(id);
        if (optionalmateriel.isPresent()) {
            Materiel materiel=optionalmateriel.get();
            return MaterielDTO.fromentity(materiel);
        }
        else
        {
            return null;
        }}


    @Override
    public void delete(Long id) {

        materielRepository.deleteById(id);
    }
}
