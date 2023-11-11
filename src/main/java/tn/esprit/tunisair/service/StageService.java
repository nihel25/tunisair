package tn.esprit.tunisair.service;

import tn.esprit.tunisair.dto.StageDTO;
import tn.esprit.tunisair.entity.Stage;

import java.util.List;

public interface StageService {

    StageDTO save(StageDTO stageDTO);
    StageDTO recherch(Long id);

    public void delete(Long id);
    public Stage addsatge(Stage s);
    List<StageDTO> findAllstage();

}
