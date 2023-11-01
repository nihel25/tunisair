package tn.esprit.tunisair.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import tn.esprit.tunisair.entity.Demandeformation;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DemandeFormationDTO {
    private Long id;


    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateCreation = new Date(System.currentTimeMillis());
    private List<PersonnelDTO> personnelList;
    private Long nbrpersonnelle;
    private UserDTO userDTO;
    private String valid;

    private FormationDTO formationdto;
    public static DemandeFormationDTO fromEntity(Demandeformation demandes){
        return DemandeFormationDTO.builder()
                .id(demandes.getId())
                .nbrpersonnelle(demandes.getNbrpersonnelle())

                .dateCreation(demandes.getDateCreation())
                .valid(demandes.getValid())

                .formationdto(FormationDTO.fromEntity(demandes.getFormation()))

                .build();
    }



    public static Demandeformation toEntity(DemandeFormationDTO dto){
        return Demandeformation.builder()
                .id(dto.getId())

                .nbrpersonnelle(dto.getNbrpersonnelle())

                .dateCreation(dto.getDateCreation())
                .valid(dto.getValid())
               .formation(FormationDTO.toEntity(dto.getFormationdto()))

        .build();
    }
}
