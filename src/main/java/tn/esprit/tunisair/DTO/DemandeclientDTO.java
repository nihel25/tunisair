package tn.esprit.tunisair.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import tn.esprit.tunisair.entity.Demandeclient;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DemandeclientDTO {

    private Long id;

    @Email(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",message="it should be an email ")
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
               // .userDTO(UserDTO.fromEntity(demandeclient.getUser()))
                .formationdto(FormationDTO.fromEntity(demandeclient.getFormation()))
                .build();
    }

    public static Demandeclient toEntity(DemandeclientDTO dto){
        return Demandeclient.builder()
                .id(dto.getId())
                .email(dto.getEmail())

                .dateCreation(dto.getDateCreation())
                .valid(dto.getValid())
               //  .user(UserDTO.toEntity(dto.getUserDTO()))
                .formation(FormationDTO.toEntity(dto.getFormationdto()))
                .build();
    }
}
