package  tn.esprit.tunisair.AuthRequestResponse;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tn.esprit.tunisair.entity.User;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RegistrationRequest {


    private String fullname;
    private String telephone;

    private String prenom;
    private String adresse;
    private String password;

    private String email;
private String cin;







    public static User toEntity(RegistrationRequest request) {
        return User.builder()
                .fullname(request.getFullname())
                .email(request.getEmail())
                .adresse(request.getAdresse())
                .telephone(request.getTelephone())
                .password(request.getPassword())
                .cin(request.getCin())
                .prenom(request.getPrenom())
                .build();
    }

}