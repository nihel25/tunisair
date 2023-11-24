package tn.esprit.tunisair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.dto.SalleDTO;
import tn.esprit.tunisair.entity.Salle;
import tn.esprit.tunisair.repository.SalleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SalleServiceImpl implements SalleService{


   private final SalleRepository salleRepository;



    @Autowired
    public SalleServiceImpl(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }



    @Override
    public SalleDTO save(SalleDTO salleDTO) {



        Salle salle=SalleDTO.toentity(salleDTO);
        salleRepository.save(salle);
      return SalleDTO.fromEntity(salle);

    }




@Override
    //
    public SalleDTO recherch(Long id) {


        Optional<Salle> optionalsalle =salleRepository.findById(id);
        if (optionalsalle.isPresent()) {
            Salle salle=optionalsalle.get();
            return SalleDTO.fromEntity(salle);
        }
        else
        {
            return null;
        }}





















    @Override
    public void delete(Long id) {

        salleRepository.deleteById(id);
    }



    @Override
    public List<SalleDTO> findAllSalle() {
        return salleRepository.findAll()
                .stream()
                .map(SalleDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
