package tn.esprit.tunisair.dto;


import lombok.*;
import tn.esprit.tunisair.entity.Materiel;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MaterielDTO {


    private Long id;
    @NotNull
    private String nom;
    @NotNull
    private String caracteristique;
    @NotNull
    private boolean statut;
    @NotNull
    public SessionDTO sessionDTO;

    public static MaterielDTO fromentity(Materiel materiel){
        return MaterielDTO.builder()
                .id(materiel.getId())
                .nom(materiel.getNom())
                .caracteristique(materiel.getCaracteristique())

             //  .sessionDTO(SessionDTO.fromentity(materiel.getSession()))

                .build();
    }
    public static Materiel toentity(MaterielDTO materielDTO){

        return Materiel.builder()
                .id(materielDTO.getId())
                .nom(materielDTO.getNom())
                .caracteristique(materielDTO.getCaracteristique())
             //   .session(SessionDTO.toentity(materielDTO.getSessionDTO()))

                .build();
    }
}
