package tn.esprit.tunisair.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import tn.esprit.tunisair.entity.Reclamation;

import java.util.Date;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReclamationDTO {
    private Long id;



    private String typeReclamation;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date datereclamation = new Date(System.currentTimeMillis());
    private FormationDTO formationdto;
private UserDTO userDTO;
    public static ReclamationDTO fromEntity(Reclamation reclamation){
        return ReclamationDTO.builder()
                .id(reclamation.getId())
                .typeReclamation(reclamation.getTypeReclamation())
                .datereclamation(reclamation.getDatereclamation())
               .formationdto(FormationDTO.fromEntity(reclamation.getFormation()))


                        .build();

    }


    public static Reclamation toentity(ReclamationDTO reclamationDTO){
        return Reclamation.builder()
                .id(reclamationDTO.getId())
                .typeReclamation(reclamationDTO.getTypeReclamation())
                .datereclamation(reclamationDTO.getDatereclamation())
               .formation(FormationDTO.toEntity(reclamationDTO.getFormationdto()))


                .build();

    }





}
