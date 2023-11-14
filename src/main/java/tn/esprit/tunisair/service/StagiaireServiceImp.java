package tn.esprit.tunisair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.dto.StagiaireDTO;
import tn.esprit.tunisair.entity.Stagiaire;
import tn.esprit.tunisair.repository.StageRepository;
import tn.esprit.tunisair.repository.StagiaireRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StagiaireServiceImp implements StagiaireService{
    @Autowired
    private StagiaireRepository stagiaireRepository;
@Autowired
private StageRepository stageRepository;







    @Override
    public StagiaireDTO save(StagiaireDTO stagiaireDTO) {

        Stagiaire stagiaire=StagiaireDTO.toEntity(stagiaireDTO);
        stagiaireRepository.save(stagiaire);
        return  StagiaireDTO.fromEntity(stagiaire);


    }





    @Override
    //
    public StagiaireDTO recherch(Long id) {
        Optional<Stagiaire> optionalstagair =stagiaireRepository.findById(id);
        if (optionalstagair.isPresent()) {
            Stagiaire stagiaire=optionalstagair.get();
            return StagiaireDTO.fromEntity(stagiaire);
        }
        else
        {
            return null;
        }}
















    @Override
    public void delete(Long id) {


        stagiaireRepository.deleteById(id);

    }





    @Override
    public List<StagiaireDTO> findAll() {
        return stagiaireRepository.findAll()
                .stream()
                .map(StagiaireDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
