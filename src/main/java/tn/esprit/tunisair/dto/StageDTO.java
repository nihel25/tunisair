package tn.esprit.tunisair.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import tn.esprit.tunisair.entity.Stage;

import javax.validation.constraints.NotNull;
import java.util.Date;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StageDTO {
    private Long id;

    @NotNull(message="it should be not null")
    private String typeStage;

    @NotNull(message="it should be not null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFinStage;
    @NotNull(message="it should be not null")
    @JsonFormat(pattern = "yyyy-MM-dd")

    private Date dateDebutStage;
    @NotNull(message="it should be not null")
    private Long duree;



    @NotNull(message="it should be not null")
    private String nomService;
private  UserDTO userdto;
private EncadreurDTO encadreurDTO;
    public static StageDTO fromEntity(Stage stage){

        return StageDTO.builder()
                .id(stage.getId())

                .typeStage(stage.getTypeStage())
                .dateFinStage(stage.getDateFinStage())
                .dateDebutStage(stage.getDateDebutStage())
                .duree(stage.getDuree())
                .encadreurDTO(EncadreurDTO.fromEntity(stage.getEncadreur()))
                .nomService(stage.getNomService())
           // .userdto(UserDTO.fromEntity(stage.getUser()))
                .build();
    }


    public static Stage toEntity(StageDTO dto) {
        return Stage.builder()

                .id(dto.getId())

                .typeStage(dto.getTypeStage())
                .dateFinStage(dto.getDateFinStage())
                .dateDebutStage(dto.getDateDebutStage())
                .duree(dto.getDuree())

                .nomService(dto.getNomService())
                .encadreur(EncadreurDTO.toEntity(dto.getEncadreurDTO()))

                .build();
    }
























}