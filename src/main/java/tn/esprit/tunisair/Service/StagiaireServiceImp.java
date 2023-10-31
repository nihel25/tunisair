package tn.esprit.tunisair.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.DTO.StagiaireDTO;
import tn.esprit.tunisair.Repository.StageRepository;
import tn.esprit.tunisair.Repository.StagiaireRepository;
import tn.esprit.tunisair.entity.Stagiaire;

import javax.persistence.EntityNotFoundException;
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

        Stagiaire stagiaire=stagiaireDTO.toEntity(stagiaireDTO);
        stagiaireRepository.save(stagiaire);
        StagiaireDTO DTOsaved=stagiaireDTO.fromEntity(stagiaire);
        return DTOsaved;

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

        Stagiaire st = stagiaireRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
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
