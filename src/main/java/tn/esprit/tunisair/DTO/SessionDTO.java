package tn.esprit.tunisair.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import tn.esprit.tunisair.entity.Formation;
import tn.esprit.tunisair.entity.Session;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionDTO {
 private Long id;
 @NotNull
 @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;
    @NotNull
   private String reference;
    public SalleDTO getSalleDTO() {
        return salleDTO;
    }




private FormationDTO formationDTO;
public SalleDTO salleDTO;

public UserDTO userDTO;
private FormateurDto formateurDto;
    private List<PersonnelDTO> personnelList;
    public static SessionDTO fromentity(Session session){
        Formation formation = session.getFormation();
        if (formation != null) {
            return SessionDTO.builder()
                    .id(session.getId())
                    .dateFin(session.getDateFin())
                    .dateDebut(session.getDateDebut())
                    .reference(session.getReference())
                    .formationDTO(FormationDTO.fromEntity(formation))
                    .salleDTO(SalleDTO.fromEntity(session.getSalle()))
                    .formateurDto(FormateurDto.fromentity(session.getFormateur()))
                    //.userDTO(UserDTO.fromEntity(session.getUser()))
                    .build();
        } else {
            // Gérer le cas où l'objet formation est null (selon votre logique métier)
            return null; // Ou renvoyer un objet SessionDTO par défaut, ou une autre action à votre convenance
        }
    }

    public static Session toentity(SessionDTO session){
        return Session.builder()
                .id(session.getId())
                .dateFin(session.getDateFin())
                .dateDebut(session.getDateDebut())
                .reference(session.getReference())
               .formation(FormationDTO.toEntity(session.getFormationDTO()))
               .salle(SalleDTO.toentity(session.getSalleDTO()))
                .formateur(FormateurDto.toentity(session.getFormateurDto()))
                //.user(UserDTO.toEntity(session.getUserDTO()))
                .build();
    }


















}
