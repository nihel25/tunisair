package tn.esprit.tunisair.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import tn.esprit.tunisair.entity.Formateur;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FormateurDto {


    private Long id;
    @NotNull(message="it should be not null")
    private String nom;
    @NotNull(message="it should be not null")
    private String prenom;
    @NotNull(message="it should be not null")

    private String email;



    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date debut;

    private Boolean disponible;
    private  SpecialiteeDTO specialiteedto;

    public static FormateurDto fromentity(Formateur formateur){
        return FormateurDto.builder()
              .id(formateur.getId())
                .nom(formateur.getNom())
                .prenom(formateur.getPrenom())
                .email(formateur.getEmail())
                .disponible(formateur.getDisponible())
                .debut(formateur.getDebut())
                .fin(formateur.getFin())
             .specialiteedto(SpecialiteeDTO.fromEntity(formateur.getSpecialitee()))

                .build();
    }







    private boolean isFormateurDisponible(FormateurDto formateurDto) {
        return formateurDto.isDisponible();  // Renvoie true si la salle est disponible (statut est true)
    }
    public boolean isDisponible() {
        return disponible;
    }










    public void setdisponible(boolean disponible) {
        this.disponible = disponible;
    }







    public static Formateur toentity(FormateurDto dto){

        return Formateur.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .email(dto.getEmail())
             .specialitee(SpecialiteeDTO.toentity(dto.getSpecialiteedto()))
                .disponible(dto.getDisponible())
                .fin(dto.getFin())
                .debut(dto.getDebut())
                .build();
    }



}
