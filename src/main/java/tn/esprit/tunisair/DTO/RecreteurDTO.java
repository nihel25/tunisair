package tn.esprit.tunisair.DTO;
//


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tn.esprit.tunisair.AuthRequestResponse.RegistrationRequest;
import tn.esprit.tunisair.entity.Recruteur;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RecreteurDTO extends RegistrationRequest {
    private String specialite;


    public static RecreteurDTO fromEntity(Recruteur request) {
        return RecreteurDTO.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .password(request.getPassword())
                .adresse(request.getAdresse())
                .telephone(request.getTelephone())
                .cin(request.getCin())
                .prenom(request.getPrenom())
                .specialite(request.getSpecialite())
                .build();


    }


    public static Recruteur toentity(RecreteurDTO request) {

        return Recruteur.builder()

                .fullname(request.getFullname())
                .email(request.getEmail())
                .password(request.getPassword())
                .adresse(request.getAdresse())
                .telephone(request.getTelephone())
                .prenom(request.getPrenom())
                .cin(request.getCin())
                .specialite(request.getSpecialite())
                .build();
    }


}





