package tn.esprit.tunisair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.dto.ReclamationDTO;
import tn.esprit.tunisair.entity.Reclamation;
import tn.esprit.tunisair.repository.FormationRepository;
import tn.esprit.tunisair.repository.ReclamationRepository;
import tn.esprit.tunisair.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ReclamationServiceImpl implements ReclamationService {
@Autowired
FormationRepository formationRepository;
@Autowired
ReclamationRepository reclamationRepository;






    private final UserRepository userRepository;


    public ReclamationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


















@Override
public ReclamationDTO save(ReclamationDTO reclamationDTO) {
    Reclamation reclamation=ReclamationDTO.toentity(reclamationDTO);
    reclamationRepository.save(reclamation);
    return ReclamationDTO.fromEntity(reclamation);

}
    @Override
    //
    public ReclamationDTO recherch(Long id) {
        Optional<Reclamation> optionalReclamation =reclamationRepository.findById(id);
        if (optionalReclamation.isPresent()) {
            Reclamation reclamation=optionalReclamation.get();
            return ReclamationDTO.fromEntity(reclamation);
        }
        else
        {
            return null;
        }}





    @Override
    public List<ReclamationDTO> findAllreclamation() {
        return reclamationRepository.findAll()
                .stream()
                .map(ReclamationDTO::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) {

        reclamationRepository.deleteById(id);
    }




}
