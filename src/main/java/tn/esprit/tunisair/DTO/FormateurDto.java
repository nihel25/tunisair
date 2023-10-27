package tn.esprit.tunisair.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import tn.esprit.tunisair.entity.Formateur;

import javax.validation.constraints.Email;
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
    @Email(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",message="it should be an email ")
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
