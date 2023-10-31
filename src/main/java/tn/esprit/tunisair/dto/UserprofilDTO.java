package tn.esprit.tunisair.dto;


import lombok.*;
import tn.esprit.tunisair.entity.UserProfile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserprofilDTO {


    private Long id;  // Identifiant unique du profil

    private String age;
    private String niveauEtudes;
    private String heuresDisponiblesSemaine;
    private String montantBudget;
    private String nombrePersonnesGroupe;
    private String formatFormation;
    private String engagementsProfessionnels;


    public static UserProfile toEntity(UserprofilDTO dto) {
        return UserProfile.builder()


                .id(dto.getId())


                .age(dto.getAge())
                .niveauEtudes(dto.getNiveauEtudes())
                .heuresDisponiblesSemaine(dto.getHeuresDisponiblesSemaine())
                .montantBudget(dto.getMontantBudget())
                .nombrePersonnesGroupe(dto.getNombrePersonnesGroupe())
                .formatFormation(dto.getFormatFormation())
                .engagementsProfessionnels(dto.getEngagementsProfessionnels())

                .build();
    }








    public static UserprofilDTO fromEntity(UserProfile dto){

        return UserprofilDTO.builder()
                .id(dto.getId())


                .age(dto.getAge())
                .niveauEtudes(dto.getNiveauEtudes())
                .heuresDisponiblesSemaine(dto.getHeuresDisponiblesSemaine())
                .montantBudget(dto.getMontantBudget())
                .nombrePersonnesGroupe(dto.getNombrePersonnesGroupe())
                .formatFormation(dto.getFormatFormation())
                .engagementsProfessionnels(dto.getEngagementsProfessionnels())

                .build();
    }

}
