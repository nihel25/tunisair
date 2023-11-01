package tn.esprit.tunisair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.dto.DemandeFormationDTO;
import tn.esprit.tunisair.dto.FormationDTO;
import tn.esprit.tunisair.dto.PersonnelDTO;
import tn.esprit.tunisair.service.DemandeformationServiceImpl;
import tn.esprit.tunisair.entity.Demandeformation;
import tn.esprit.tunisair.entity.Personnel;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/DemandeFormation")
@CrossOrigin(origins = "http://localhost:4200")
public class DemandeformationController {


    @Autowired
    private DemandeformationServiceImpl demandeFormationService;


    @Secured("COORDINATEURENTREPRISE")

    @PostMapping("/demande")
    public ResponseEntity<String> envoyerDemandeFormation(@RequestBody DemandeFormationDTO demandeFormationDTO) {
        Demandeformation demandeFormation = new Demandeformation();
        demandeFormation.setDateCreation(demandeFormationDTO.getDateCreation());

        demandeFormation.setNbrpersonnelle(demandeFormationDTO.getNbrpersonnelle());


        FormationDTO formationdto = demandeFormationDTO.getFormationdto();
        if (formationdto != null) {
            demandeFormation.setFormation(FormationDTO.toEntity(formationdto));
        }


        List<PersonnelDTO> personnelDTOList = demandeFormationDTO.getPersonnelList();
        List<Personnel> personnelList = new ArrayList<>();

        for (PersonnelDTO personnelDTO : personnelDTOList) {
            Personnel personnel = new Personnel();
            personnel.setId(personnelDTO.getId());
            personnel.setNom(personnelDTO.getNom());

            personnelList.add(personnel);
        }

        demandeFormation.setPersonnel(personnelList);
        if (demandeFormationDTO.getId() != null) {

            demandeFormation.setId(demandeFormationDTO.getId());
            demandeFormationService.add(demandeFormationDTO);
            return null;
        } else {

            demandeFormationService.saveDemandeFormation(demandeFormation);
            return null;
        }
    }


    @Secured({"COORDINATEURFORMATION", "COORDINATEURENTREPRISE"})
    @GetMapping("/personnel")
    public ResponseEntity<List<Personnel>> getAllPersonnel() {
        List<Personnel> personnelList = demandeFormationService.getAllPersonnel();
        return ResponseEntity.ok(personnelList);
    }

    //
    @Secured("COORDINATEURFORMATION")
    @DeleteMapping("/supprimer/{id}")
    public void delete(@PathVariable Long id) {

        demandeFormationService.delete(id);
    }


    @Secured("COORDINATEURFORMATION")
    @GetMapping("/alldemande")
    public List<DemandeFormationDTO> getAllDemandeFormations() {

        return demandeFormationService.findAllDemandes();
    }

    @Secured("COORDINATEURFORMATION")
    @GetMapping("/recherher/{id}")
    public DemandeFormationDTO recherch(@PathVariable Long id) {

        return demandeFormationService.recherch(id);
    }


}
