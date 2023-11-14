package tn.esprit.tunisair.service;

import tn.esprit.tunisair.dto.StageDTO;

import java.util.List;

public interface StageService {

    StageDTO save(StageDTO stageDTO);
    StageDTO recherch(Long id);

    public void delete(Long id);

    List<StageDTO> findAllstage();

}
