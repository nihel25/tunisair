package tn.esprit.tunisair.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import tn.esprit.tunisair.entity.Formation;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FormationDTO {
private  Long id;

    private String formationtype;
    private double remise;
    private String image ;

    @NotNull

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateformation;
    @NotNull
    private double  prix;


    @Column(unique = true)
    private String ref;
    @NotNull

private FormateurDto formateurDto;
    @Column(length = 2000)
    private String description;
private UserDTO userDTO;
private userprofilDTO userprofildto;
    public static FormationDTO fromEntity(Formation formation){

        return FormationDTO.builder()
                .id(formation.getId())
                .ref(formation.getRef())
                .dateformation(formation.getDateformation())
                .prix(formation.getPrix())
                .formationtype(formation.getFormationtype())
              .formateurDto(FormateurDto.fromentity(formation.getFormateur()))


                .description(formation.getDescription())
                .remise(formation.getRemise())
                .image(formation.getImage())
                .userprofildto(userprofilDTO.fromEntity(formation.getUserProfile()))

                .build();
    }
    public static Formation toEntity(FormationDTO dto){
        return Formation.builder()
                .id(dto.getId())
                .ref(dto.getRef())
                .dateformation(dto.getDateformation())
                .prix(dto.getPrix())
                .formationtype(dto.getFormationtype())
               .formateur(FormateurDto.toentity(dto.getFormateurDto()))

                .description(dto.getDescription())

                .remise(dto.getRemise())
                .image(dto.getImage())
                .userProfile(userprofilDTO.toEntity(dto.getUserprofildto()))
                .build();
    }

    public FormationDTO(Long id, String formationType, float prix) {
        this.id = id;
        this.formationtype = formationType;
        this.prix = prix;
    }
}
