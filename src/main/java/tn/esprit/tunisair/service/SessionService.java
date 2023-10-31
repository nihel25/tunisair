package tn.esprit.tunisair.service;

import tn.esprit.tunisair.dto.SessionDTO;
import tn.esprit.tunisair.entity.Personnel;
import tn.esprit.tunisair.entity.Session;

import java.util.List;

public interface SessionService {

    void delete(Long id);

    List<SessionDTO> findAllSession();

    public List<Personnel> getAllPersonnel();

    public void savee(Session session);
    SessionDTO recherch(Long id);
}
