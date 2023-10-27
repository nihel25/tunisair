package tn.esprit.tunisair.DTO;


import lombok.*;
import tn.esprit.tunisair.entity.Personnel;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonnelDTO {


    private Long id;
    private String nom;
    private String prenom;
    private String fonction;
    private String email;
private String cin;


    public static PersonnelDTO fromentity(Personnel personnel) {
        return PersonnelDTO.builder()
                .id(personnel.getId())
                .cin(personnel.getCin())
                .nom(personnel.getNom())
                .prenom(personnel.getPrenom())
                .fonction(personnel.getFonction())
                .email(personnel.getEmail())
                .build();
    }









    public static Personnel toentity(PersonnelDTO dto){

        return Personnel.builder()
                .id(dto.getId())

                .cin(dto.getCin())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .fonction(dto.getFonction())
                .email(dto.getEmail())


                .build();

    }


}
