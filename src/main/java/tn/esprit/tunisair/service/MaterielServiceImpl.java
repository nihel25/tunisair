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


    @Autowired
    MaterielRepository materielRepository;












    @Override
    public List<MaterielDTO> findAllreclamation() {
        return materielRepository.findAll()
                .stream()
                .map(MaterielDTO::fromentity)
                .collect(Collectors.toList());
    }

    @Override
    public MaterielDTO save(MaterielDTO materielDTO) {


        Materiel materiel=materielDTO.toentity(materielDTO);
        materielRepository.save(materiel);
        MaterielDTO materielsave=materielDTO.fromentity(materiel);
        return materielsave;

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
