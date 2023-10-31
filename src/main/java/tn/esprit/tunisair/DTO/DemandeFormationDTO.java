package tn.esprit.tunisair.DTO;


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
    public static DemandeFormationDTO fromEntity(Demandeformation demandeformation){
        return DemandeFormationDTO.builder()
                .id(demandeformation.getId())
                .nbrpersonnelle(demandeformation.getNbrpersonnelle())

                .dateCreation(demandeformation.getDateCreation())
                .valid(demandeformation.getValid())

                .formationdto(FormationDTO.fromEntity(demandeformation.getFormation()))

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
