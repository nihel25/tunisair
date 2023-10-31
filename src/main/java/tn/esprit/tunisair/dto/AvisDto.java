package tn.esprit.tunisair.dto;

import lombok.*;
import tn.esprit.tunisair.entity.Avis;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvisDto {

    private Long id;
    private int nombreEtoiles;

    private UserDTO userDTO;

    private String text;
    public static AvisDto fromentity(Avis avis){


        return AvisDto.builder()
                .id(avis.getId())
                .text(avis.getText())

                .nombreEtoiles(avis.getNombreEtoiles())
                .userDTO(UserDTO.fromEntity(avis.getUser()))
                .build();
    }


    public static Avis toentity(AvisDto dto){


        return Avis.builder()
                .id(dto.getId())
                .text(dto.getText())
                .nombreEtoiles(dto.getNombreEtoiles())

                .user(UserDTO.toEntity(dto.getUserDTO()))
                .build();

    }
}
