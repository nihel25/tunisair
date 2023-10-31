package tn.esprit.tunisair.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.DTO.ReclamationDTO;
import tn.esprit.tunisair.Repository.FormationRepository;
import tn.esprit.tunisair.Repository.ReclamationRepository;
import tn.esprit.tunisair.Repository.UserRepository;
import tn.esprit.tunisair.entity.Reclamation;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ReclamationServiceImpl implements ReclamationService {
@Autowired
FormationRepository formationRepository;
@Autowired
ReclamationRepository reclamationRepository;

@Autowired
    UserRepository userRepository;





















@Override
public ReclamationDTO save(ReclamationDTO reclamationDTO) {
    Reclamation reclamation=reclamationDTO.toentity(reclamationDTO);
    reclamationRepository.save(reclamation);
    ReclamationDTO encadreurDTOsaved=reclamationDTO.fromEntity(reclamation);
    return encadreurDTOsaved;
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
        Reclamation reclamation = reclamationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id + " not found"));
        reclamationRepository.deleteById(id);
    }




}
