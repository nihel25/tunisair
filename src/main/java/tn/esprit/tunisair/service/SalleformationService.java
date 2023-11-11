package tn.esprit.tunisair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.entity.Salleformation;
import tn.esprit.tunisair.repository.SalleformationRepository;

@Service
public class SalleformationService {

@Autowired
SalleformationRepository salleformationRepository;
    public Salleformation add (Salleformation ce){
        return salleformationRepository.save(ce);
    }
}
