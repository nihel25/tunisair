package tn.esprit.tunisair.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.dto.DemandeclientDTO;
import tn.esprit.tunisair.entity.Demandeclient;
import tn.esprit.tunisair.repository.DemandeclientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DemandeformationclientServiceImpl implements DemandeformationclientService{
    @Autowired
    DemandeclientRepository demandeclientRepository;





@Autowired
UserService userService;








@Override
    public DemandeclientDTO add(DemandeclientDTO demandeclient) {




        Demandeclient demande=demandeclient.toEntity(demandeclient);


        demandeclientRepository.save(demande);
        DemandeclientDTO savedemandeclient=demandeclient.fromEntity(demande);
        return savedemandeclient;
    }

    @Override
    public void delete(Long id) {
        demandeclientRepository.deleteById(id);
    }

    @Override
    public DemandeclientDTO recherch(Long id) {

        Optional<Demandeclient> optionaldemande =demandeclientRepository.findById(id);
        if (optionaldemande.isPresent()) {
            Demandeclient demande=optionaldemande.get();
            return DemandeclientDTO.fromEntity(demande);
        }
        else
        {
            return null;
        }
    }




    @Override
    public List<DemandeclientDTO> findAlldemandeformation() {
        return demandeclientRepository.findAll()
                .stream()
                .map(DemandeclientDTO::fromEntity)
                .collect(Collectors.toList());
    }
    }

