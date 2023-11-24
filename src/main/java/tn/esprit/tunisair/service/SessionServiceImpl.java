package tn.esprit.tunisair.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.dto.SessionDTO;
import tn.esprit.tunisair.entity.Personnel;
import tn.esprit.tunisair.entity.Session;
import tn.esprit.tunisair.repository.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {


    private final SessionRepository sessionRepository;



@Autowired
PersonnelRepository personnelRepository;


    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository
    ) {

        this.sessionRepository = sessionRepository;

    }

    @Override
    public void delete(Long id) {

        sessionRepository.deleteById(id);
    }


    @Override
    //
    public SessionDTO recherch(Long id) {
        Optional<Session> optionalsession =sessionRepository.findById(id);
        if (optionalsession.isPresent()) {
            Session session=optionalsession.get();
            return SessionDTO.fromentity(session);
        }
        else
        {
            return null;
        }}


    @Override
    public List<SessionDTO> findAllSession() {
        return sessionRepository.findAll()
                .stream()
                .map(SessionDTO::fromentity)
                .collect(Collectors.toList());
    }


    @Override
    public void savee(Session session) {

        sessionRepository.save(session);
    }

    @Override
    public List<Personnel> getAllPersonnel() {
        return personnelRepository.findAll();
    }



    }

