package tn.esprit.tunisair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.dto.StageDTO;
import tn.esprit.tunisair.entity.Stage;
import tn.esprit.tunisair.repository.StageRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StageServiceIpm implements StageService{

    private final StageRepository stageRepository;

    @Autowired
    public StageServiceIpm(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }







    @Override
    public StageDTO save(StageDTO stagedto) {

        Stage stage= StageDTO.toEntity(stagedto);
        stageRepository.save(stage);
       return StageDTO.fromEntity(stage);


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

        stageRepository.deleteById(id);
    }
























}








