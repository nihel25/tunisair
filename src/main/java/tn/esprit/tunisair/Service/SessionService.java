package tn.esprit.tunisair.Service;

import tn.esprit.tunisair.DTO.SessionDTO;
import tn.esprit.tunisair.entity.Personnel;
import tn.esprit.tunisair.entity.Session;

import java.util.Date;
import java.util.List;

public interface SessionService {

    void delete(Long id);

    List<SessionDTO> findAllSession();
    List<SessionDTO> findbydate(Date start , Date end);
    public List<Personnel> getAllPersonnel();

    public void savee(Session session);
    SessionDTO recherch(Long id);
}
