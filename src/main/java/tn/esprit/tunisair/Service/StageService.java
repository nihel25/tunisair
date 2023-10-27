package tn.esprit.tunisair.Service;

import tn.esprit.tunisair.DTO.StageDTO;

import java.util.List;

public interface StageService {

    StageDTO save(StageDTO stageDTO);
    StageDTO recherch(Long id);

    public void delete(Long id);

    List<StageDTO> findAllstage();

}
