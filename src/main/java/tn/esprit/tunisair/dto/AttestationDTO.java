package tn.esprit.tunisair.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import tn.esprit.tunisair.entity.Attestation;


import javax.validation.constraints.NotNull;
import java.util.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttestationDTO {





    private Long id;


    @NotNull
private PersonnelDTO personnelDTO;

    @NotNull

    private FormationDTO formationdto;




    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date datenew = new Date(System.currentTimeMillis());
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;



    public static AttestationDTO fromentity(Attestation attestation){


        return AttestationDTO.builder()
                .id(attestation.getId())
                .datenew(attestation.getDatenew())

                .dateDebut(attestation.getDateDebut())
                .dateFin(attestation.getDateFin())
                .formationdto(FormationDTO.fromEntity(attestation.getFormation()))
                .personnelDTO(PersonnelDTO.fromentity(attestation.getPersonnel()))
                .build();
    }


    public static Attestation toentity(AttestationDTO dto){


        return Attestation.builder()
                .id(dto.getId())
                .datenew(dto.getDatenew())

                .dateDebut(dto.getDateDebut())
                .dateFin(dto.getDateFin())
                .personnel(PersonnelDTO.toentity(dto.getPersonnelDTO()))
                .formation(FormationDTO.toEntity(dto.getFormationdto()))
                .build();

    }

}
