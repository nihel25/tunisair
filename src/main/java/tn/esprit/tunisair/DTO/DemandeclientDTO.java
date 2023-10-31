package tn.esprit.tunisair.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import tn.esprit.tunisair.entity.Demandeclient;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DemandeclientDTO {

    private Long id;


    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateCreation = new Date(System.currentTimeMillis());
    @NotNull
    private String valid;
    @NotNull

private FormationDTO formationdto;


    private UserDTO userDTO;

    public static DemandeclientDTO fromEntity(Demandeclient demandeclient){
        return DemandeclientDTO.builder()
                .id(demandeclient.getId())
                .email(demandeclient.getEmail())

                .dateCreation(demandeclient.getDateCreation())
                .valid(demandeclient.getValid())

                .formationdto(FormationDTO.fromEntity(demandeclient.getFormation()))
                .build();
    }

    public static Demandeclient toEntity(DemandeclientDTO dto){
        return Demandeclient.builder()
                .id(dto.getId())
                .email(dto.getEmail())

                .dateCreation(dto.getDateCreation())
                .valid(dto.getValid())

                .formation(FormationDTO.toEntity(dto.getFormationdto()))
                .build();
    }
}
