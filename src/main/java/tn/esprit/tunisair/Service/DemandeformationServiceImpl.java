package tn.esprit.tunisair.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tunisair.DTO.DemandeFormationDTO;
import tn.esprit.tunisair.DTO.FormationDTO;
import tn.esprit.tunisair.DTO.PersonnelDTO;
import tn.esprit.tunisair.Repository.DemandeformationRepository;
import tn.esprit.tunisair.Repository.PersonnelRepository;
import tn.esprit.tunisair.Repository.UserRepository;
import tn.esprit.tunisair.entity.Demandeformation;
import tn.esprit.tunisair.entity.Personnel;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeformationServiceImpl implements DemandeformationService{


    @Autowired
    DemandeformationRepository demandeformationRepository;
@Autowired
    UserRepository userRepository;

    @Autowired
    private PersonnelRepository personnelRepository;




    @Override
    public void saveDemandeFormation(Demandeformation demandeFormation) {
        demandeFormation.setValid("en attente");

        demandeformationRepository.save(demandeFormation);
    }
    @Override
    public List<DemandeFormationDTO> findAllDemandes() {
        List<Demandeformation> demandes = demandeformationRepository.findAll();
        List<DemandeFormationDTO> demandeDTOs = new ArrayList<>();

        for (Demandeformation demande : demandes) {
            DemandeFormationDTO demandeDTO = new DemandeFormationDTO();
            demandeDTO.setId(demande.getId());
            demandeDTO.setValid(demande.getValid());
            demandeDTO.setNbrpersonnelle(demande.getNbrpersonnelle());


            // Récupérez formationdto à partir de l'entité Demandeformation
            FormationDTO formationDTO = FormationDTO.fromEntity(demande.getFormation());
            demandeDTO.setFormationdto(formationDTO);

            // Définir d'autres propriétés de la demande

            List<PersonnelDTO> personnelDTOList = new ArrayList<>();
            for (Personnel personnel : demande.getPersonnel()) {
                PersonnelDTO personnelDTO = new PersonnelDTO();
                personnelDTO.setId(personnel.getId());
                personnelDTO.setNom(personnel.getNom());
                personnelDTO.setPrenom(personnel.getPrenom());
                personnelDTOList.add(personnelDTO);
            }
            demandeDTO.setPersonnelList(personnelDTOList);

            demandeDTOs.add(demandeDTO);
        }

        return demandeDTOs;
    }

    @Override
    public DemandeFormationDTO recherch(Long id) {

        Optional<Demandeformation> optionaldemande =demandeformationRepository.findById(id);
        if (optionaldemande.isPresent()) {
            Demandeformation demande=optionaldemande.get();
            return DemandeFormationDTO.fromEntity(demande);
        }
        else
        {
            return null;
        }
    }



    @Override
    public DemandeFormationDTO add(DemandeFormationDTO f) {



        Demandeformation demande=f.toEntity(f);


        demandeformationRepository.save(demande);
        DemandeFormationDTO DTOsaved=f.fromEntity(demande);
        return DTOsaved;
    }

    @Override
    public void delete(Long id) {
        Demandeformation demandeformation = demandeformationRepository.findById(id).orElseThrow(()->new EntityNotFoundException(id+" not found"));
        demandeformationRepository.deleteById(id);
    }


    @Override
    public List<Personnel> getAllPersonnel() {
        return personnelRepository.findAll();
    }



}
