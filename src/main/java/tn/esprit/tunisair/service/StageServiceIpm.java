package tn.esprit.tunisair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.dto.StageDTO;
import tn.esprit.tunisair.repository.StageRepository;
import tn.esprit.tunisair.repository.UserRepository;
import tn.esprit.tunisair.entity.Stage;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StageServiceIpm implements StageService{
    @Autowired
    private StageRepository stageRepository;

   @Autowired
    UserRepository userRepository;







    @Override
    public StageDTO save(StageDTO stagedto) {

        Stage stage=stagedto.toEntity(stagedto);
        stageRepository.save(stage);
        StageDTO savestage=stagedto.fromEntity(stage);
        return savestage;

    }





    @Override
    public StageDTO recherch(Long id) {
        Optional<Stage> optionalstagair =stageRepository.findById(id);
        if (optionalstagair.isPresent()) {
            Stage stage=optionalstagair.get();
            return StageDTO.fromEntity(stage);
        }
        else
        {
            return null;
    }}



    @Override
    public List<StageDTO> findAllstage() {
        return stageRepository.findAll()
                .stream()
                .map(StageDTO::fromEntity)
                .collect(Collectors.toList());
    }










    @Override
    public void delete(Long id) {
        Stage stage = stageRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
        stageRepository.deleteById(id);
    }
























}








