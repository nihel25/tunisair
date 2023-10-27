package tn.esprit.tunisair.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.DTO.*;
import tn.esprit.tunisair.Service.FormateurService;
import tn.esprit.tunisair.Service.SalleService;
import tn.esprit.tunisair.Service.SessionService;
import tn.esprit.tunisair.entity.Personnel;
import tn.esprit.tunisair.entity.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/session")
@CrossOrigin(origins = "http://localhost:4200")
public class SessionController {


    @Autowired
    SessionService sessionService;
@Autowired
    SalleService salleService;
    @Autowired
    FormateurService  formateurService;


     @Secured("coordinateurformation")
    @PostMapping("/ajoutersession")
    public ResponseEntity<String> addsession(@RequestBody SessionDTO sessionDTO) {
        Session session = new Session();

        session.setDateDebut(sessionDTO.getDateDebut());
        session.setDateFin(sessionDTO.getDateFin());
        session.setReference(sessionDTO.getReference());
        session.setSalle(SalleDTO.toentity(sessionDTO.getSalleDTO()));
         session.setFormateur(FormateurDto.toentity(sessionDTO.getFormateurDto()));
         FormateurDto formateurDto = sessionDTO.getFormateurDto();





         SalleDTO salleDTO = sessionDTO.getSalleDTO();
         if (!isSalleDisponible(salleDTO)) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                     .body("Cette salle n'est pas disponible pour une réservation.");
         }


         salleDTO.setDateDebut(sessionDTO.getDateDebut());
         salleDTO.setDateFin(sessionDTO.getDateFin());
         salleDTO.setStatut(false);
         salleService.save(salleDTO);
         if (!isFormateurDisponible(formateurDto)) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                     .body("Formateur n'est pas disponible pour une réservation.");
         }


         formateurDto.setDebut(sessionDTO.getDateDebut());
         formateurDto.setFin(sessionDTO.getDateFin());
         formateurDto.setdisponible(false);
         formateurService.save(formateurDto);






        session.setFormation(FormationDTO.toEntity(sessionDTO.getFormationDTO()));


        List<PersonnelDTO> personnelDTOList = sessionDTO.getPersonnelList();
        List<Personnel> personnelList = new ArrayList<>();

        for (PersonnelDTO personnelDTO : personnelDTOList) {
            Personnel personnel = new Personnel();
            personnel.setId(personnelDTO.getId());
            personnel.setNom(personnelDTO.getNom());
            personnel.setPrenom(personnelDTO.getPrenom());
            personnelList.add(personnel);
        }

        session.setPersonnel(personnelList);

        if (sessionDTO.getId() != null) {
            // Si l'ID est présent dans sessionDTO, il s'agit d'une mise à jour
            session.setId(sessionDTO.getId());
            sessionService.savee(session);
            return null;
        } else {
            // Sinon, il s'agit d'un nouvel ajout
            sessionService.savee(session);


            return null;
        }
    }


    private boolean isSalleDisponible(SalleDTO salleDTO) {

        return salleDTO.isStatut();
    }



    private boolean isFormateurDisponible(FormateurDto formateurDto) {

        return formateurDto.isDisponible();
    }




   @Secured("coordinateurformation")
    @GetMapping("/personnel")
    public ResponseEntity<List<Personnel>> getAllPersonnel() {
        List<Personnel> personnelList = sessionService.getAllPersonnel();
        return ResponseEntity.ok(personnelList);
    }
















    @Secured("coordinateurformation")
    @GetMapping("/recherher/{id}")
    public SessionDTO recherch(@PathVariable Long id) {

        return sessionService.recherch(id);
    }



   @Secured("coordinateurformation")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id ) {
        sessionService.delete(id);
    }


   @Secured("coordinateurformation")
    @GetMapping("/listesession")
    public List<SessionDTO> findAll() {
        return sessionService.findAllSession();
    }

    @Secured("coordinateurformation")
    @GetMapping("/listesessionbydate")
    public List<SessionDTO> findAllformationpagination(Date start, Date end) {
        return sessionService.findbydate(start,end);
    }













}